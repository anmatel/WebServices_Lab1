package webservices_lab1;

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
 
    public List<Person> getPersons(Person p) {
        StringBuilder condition = new StringBuilder();
        
        if (p.getName()!="" && p.getName()!=null)
            condition.append("and name='"+p.getName()+"'");
        if (p.getSurname()!="" && p.getSurname()!=null)
            condition.append("and surname='"+p.getSurname()+"'");
        if (p.getDateOfBirth()!=null)
            condition.append("and dateOfBirth='"+p.getDateOfBirth().toString()+"'");
        if (p.getSex()=="male" || p.getSex()=="female")
            condition.append("and sex='"+p.getSex()+"'");
        
        if (condition!=null && !condition.toString().equals(""))
        {
            condition.delete(0, 3);
            condition.insert(0, "where ");
        }
        
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection())
        {
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
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}
