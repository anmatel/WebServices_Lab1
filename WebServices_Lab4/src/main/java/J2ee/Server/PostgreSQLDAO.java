package J2ee.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    private Connection connection;

    PostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Standalone.Server.Person> getPersons(String inputName, String inputSurname, Date inputDate, String inputSex) {
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
        if (inputSex.equals("female") || inputSex.equals("male")) {
            condition.append("and sex='").append(inputSex).append("'");
        }

        if (condition != null && !condition.toString().equals("")) {
            condition.delete(0, 3);
            condition.insert(0, " where ");
        }

        List<Standalone.Server.Person> persons = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons".concat(condition.toString()).concat(";"));
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String sex = rs.getString("sex");
                Standalone.Server.Person person = new Standalone.Server.Person(name, surname, dateOfBirth, sex);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}
