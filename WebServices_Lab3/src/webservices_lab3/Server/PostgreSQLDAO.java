package webservices_lab3.Server;

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
import webservices_lab3.Errors.IllegalEnteredDataException;
import webservices_lab3.Errors.PersonServiceFault;

public class PostgreSQLDAO {

    public List<Person> getPersons(Person p) {
        StringBuilder condition = new StringBuilder();

        if (p.getName() != "" && p.getName() != null) {
            condition.append("and name='").append(p.getName()).append("'");
        }
        if (p.getSurname() != "" && p.getSurname() != null) {
            condition.append("and surname='").append(p.getSurname()).append("'");
        }
        if (p.getDateOfBirth() != null) {
            condition.append("and dateOfBirth='").append(p.getDateOfBirth().toString()).append("'");
        }
        if (p.getSex() == "male" || p.getSex() == "female") {
            condition.append("and sex='").append(p.getSex()).append("'");
        }

        if (condition != null && !condition.toString().equals("")) {
            condition.delete(0, 3);
            condition.insert(0, "where ");
        }

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

    public int createPerson(Person p) throws IllegalEnteredDataException {
        {
            ArrayList<String> keys = new ArrayList<String>();
            ArrayList<String> values = new ArrayList<String>();
            if (!p.getName().isEmpty()) {
                keys.add("name");
                values.add("?");
            } else {
                PersonServiceFault fault = PersonServiceFault.defaultInstance("Name");
                throw new IllegalEnteredDataException(fault.getMessage(), fault);
            }
            if (!p.getSurname().isEmpty()) {
                keys.add("surname");
                values.add("?");
            } else {
                PersonServiceFault fault = PersonServiceFault.defaultInstance("Surname");
                throw new IllegalEnteredDataException(fault.getMessage(), fault);
            }
            if (p.getDateOfBirth() != null) {
                keys.add("DateOfBirth");
                values.add("?");
            } else {
                PersonServiceFault fault = PersonServiceFault.defaultInstance("Date of birth");
                throw new IllegalEnteredDataException(fault.getMessage(), fault);
            }
            if (!p.getSex().isEmpty()) {
                keys.add("sex");
                values.add("?");
            } else {
                PersonServiceFault fault = PersonServiceFault.defaultInstance("Sex");
                throw new IllegalEnteredDataException(fault.getMessage(), fault);
            }

            String query = "INSERT INTO \"Persons\"(" + String.join(", ", keys) + ") VALUES (" + String.join(", ", values) + ")";
            System.out.println(query);

            Integer id = -1;
            try (Connection connection = ConnectionUtil.getConnection()) {

                PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int index = 1;
                if (!p.getName().isEmpty()) {
                    stmt.setString(index, p.getName());
                    ++index;
                }
                if (!p.getSurname().isEmpty()) {
                    stmt.setString(index, p.getSurname());
                    ++index;
                }
                if (p.getDateOfBirth() != null) {
                    stmt.setDate(index, (java.sql.Date) p.getDateOfBirth());
                    ++index;
                }
                if (!p.getSex().isEmpty()) {
                    stmt.setString(index, p.getSex());
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
    }

    public String updatePerson(Integer id, Person p) throws IllegalEnteredDataException {
        if (id == null) {
            PersonServiceFault fault = PersonServiceFault.defaultInstance("Id");
            throw new IllegalEnteredDataException(fault.getMessage(), fault);
        }
        ArrayList<String> keys = new ArrayList<String>();
        if (!p.getName().isEmpty()) {
            keys.add("name=?");
        }
        if (!p.getSurname().isEmpty()) {
            keys.add("surname=?");
        }
        if (p.getDateOfBirth() != null) {
            keys.add("dateOfBirth=?");
        }
        if (!p.getSex().isEmpty()) {
            keys.add("sex=?");
        }

        String query = "UPDATE \"Persons\" SET " + String.join(", ", keys) + " WHERE id=?";
        System.out.println(query);

        String status;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(query);

            int index = 1;
            if (!p.getName().isEmpty()) {
                stmt.setString(index, p.getName());
                ++index;
            }
            if (!p.getSurname().isEmpty()) {
                stmt.setString(index, p.getSurname());
                ++index;
            }
            if (p.getDateOfBirth() != null) {
                stmt.setDate(index, (java.sql.Date) p.getDateOfBirth());
                ++index;
            }
            if (!p.getSex().isEmpty()) {
                stmt.setString(index, p.getSex());
                ++index;
            }
            stmt.setInt(index, id);

            int count_row = stmt.executeUpdate();
            status = (count_row > 0) ? ("Updated " + count_row + " row") : "No updated row";
        } catch (SQLException ex) {
            status = "Error:" + ex.toString();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public String deletePerson(Integer id) throws IllegalEnteredDataException {
        if (id == null) {
            PersonServiceFault fault = PersonServiceFault.defaultInstance("Id");
            throw new IllegalEnteredDataException(fault.getMessage(), fault);
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

    private void ThrowNullException(String personParameter) throws IllegalEnteredDataException {
        PersonServiceFault fault = PersonServiceFault.defaultInstance(personParameter);
    }
}
