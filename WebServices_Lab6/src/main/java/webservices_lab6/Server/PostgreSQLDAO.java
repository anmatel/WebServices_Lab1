package webservices_lab6.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import webservices_lab6.Errors.*;

public class PostgreSQLDAO {

    public List<Person> getPersons(String inputName, String inputSurname, Date inputDate, String inputSex) throws IllegalPersonSexValueException, IllegalEnteredDataException {
        StringBuilder condition = new StringBuilder();

        if (!inputName.isEmpty() && inputName != null) {
            condition.append("and name='").append(inputName).append("'");
        }
        if (!inputSurname.isEmpty() && inputSurname != null) {
            condition.append("and surname='").append(inputSurname).append("'");
        }
        if (inputDate != null) {
            condition.append("and dateOfBirth='").append(inputDate.toString()).append("'");
        }
        if (inputSex != null && (inputSex.equals("female") || inputSex.equals("male"))) {
            condition.append("and sex='").append(inputSex).append("'");
        } 
        else 
            throw IllegalPersonSexValueException.DEFAULT_INSTANCE;
        
        if (condition != null && !condition.toString().equals("")) {
            condition.delete(0, 3);
            condition.insert(0, " where ");
        }
        else
            throw IllegalEnteredDataException.DEFAULT_INSTANCE;

        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons".concat(condition.toString()).concat(";"));
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String sex = rs.getString("sex");
                Person person = new Person(name, surname, dateOfBirth, sex);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }

    public int createPerson(String inputName, String inputSurname, Date inputDate, String inputSex) 
            throws IllegalEnteredDataException, IllegalPersonSexValueException{
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        if (!inputName.isEmpty()) {
            keys.add("name");
            values.add("?");
        }
        else 
            throw IllegalEnteredDataException.DEFAULT_INSTANCE;
        if (!inputSurname.isEmpty()) {
            keys.add("surname");
            values.add("?");
        }
        else 
            throw IllegalEnteredDataException.DEFAULT_INSTANCE;
        if (inputDate != null) {
            keys.add("DateOfBirth");
            values.add("?");
        }
        else 
            throw IllegalEnteredDataException.DEFAULT_INSTANCE;
        if (inputSex!=null && (inputSex.equals("male") || inputSex.equals("female"))) {
            keys.add("sex");
            values.add("?");
        }
        else 
            throw IllegalPersonSexValueException.DEFAULT_INSTANCE;

        String query = "INSERT INTO \"Persons\"(" + String.join(", ", keys) + ") VALUES (" + String.join(", ", values) + ")";
        System.out.println(query);

        Integer id = -1;
        try (Connection connection = ConnectionUtil.getConnection()) {

            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            if (!inputName.isEmpty()) {
                stmt.setString(index, inputName);
                ++index;
            }
            if (!inputSurname.isEmpty()) {
                stmt.setString(index, inputSurname);
                ++index;
            }
            if (inputDate != null) {
                stmt.setDate(index, (java.sql.Date) inputDate);
                ++index;
            }
            if (!inputSex.isEmpty()) {
                stmt.setString(index, inputSex);
                ++index;
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
                System.out.println("Insert row with id = " + id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String updatePerson(Integer id, String inputName, String inputSurname, Date inputDate, String inputSex) throws IllegalEnteredDataException, IllegalPersonSexValueException {
        if (id == null) {
            return "Wrong query: Id is empty";
        }

        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        if (!inputName.isEmpty()) {
            keys.add("name");
            values.add("?");
        }
        if (!inputSurname.isEmpty()) {
            keys.add("surname");
            values.add("?");
        }
        if (inputDate != null) {
            keys.add("DateOfBirth");
            values.add("?");
        }
        if (inputSex!=null && (inputSex.equals("male") || inputSex.equals("female"))) {
            keys.add("sex");
            values.add("?");
        }
        else 
            throw IllegalPersonSexValueException.DEFAULT_INSTANCE;

        String query = "UPDATE \"Persons\" SET " + String.join(", ", keys) + " WHERE id=?";
        System.out.println(query);

        String status;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            int index = 1;
            if (!inputName.isEmpty()) {
                stmt.setString(index, inputName);
                ++index;
            }
            if (!inputSurname.isEmpty()) {
                stmt.setString(index, inputSurname);
                ++index;
            }
            if (inputDate != null) {
                stmt.setDate(index, (java.sql.Date) inputDate);
                ++index;
            }
            if (!inputSex.isEmpty()) {
                stmt.setString(index, inputSex);
                ++index;
            }
            stmt.setInt(index, id);
            
            if(stmt.getParameterMetaData().getParameterCount()==0)
                throw IllegalEnteredDataException.DEFAULT_INSTANCE;

            int count_row = stmt.executeUpdate();
            status = (count_row > 0) ? ("Updated " + count_row + " row") : "No updated row";
        } catch (SQLException ex) {
            status = "Error:" + ex.toString();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public String deletePerson(Integer id) {
        if (id == null) {
            return "Wrong query: Id is empty";
        }

        String status;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE from \"BeautyProducts\" where id=?");
            stmt.setInt(1, id);
            int count_row = stmt.executeUpdate();
            status = (count_row > 0) ? ("Deleted " + count_row + " row") : "No deleted row";
        } catch (SQLException ex) {
            status = "Error:" + ex.toString();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
}
