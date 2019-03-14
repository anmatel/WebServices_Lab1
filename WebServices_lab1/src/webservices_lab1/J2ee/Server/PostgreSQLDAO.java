package webservices_lab1.J2ee.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger; 
import webservices_lab1.Server.ConnectionUtil;
 
public class PostgreSQLDAO { 
    private Connection connection;
    private Statement statement;
    
    public PostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }
    
    public List<webservices_lab1.Server.Person> getPersons(webservices_lab1.J2ee.Server.Person p) throws SQLException {
        StringBuilder condition = new StringBuilder();
        
        if (p.getName()!="" && p.getName()!=null)
            condition.append("and name='").append(p.getName()).append("'");
        if (p.getSurname()!="" && p.getSurname()!=null)
            condition.append("and surname='").append(p.getSurname()).append("'");
        if (p.getDateOfBirth()!=null)
            condition.append("and dateOfBirth='").append(p.getDateOfBirth().toString()).append("'");
        if (p.getSex()=="male" || p.getSex()=="female")
            condition.append("and sex='").append(p.getSex()).append("'");
        
        if (condition!=null && !condition.toString().equals(""))
        {
            condition.delete(0, 3);
            condition.insert(0, "where ");
        }
        
        List<webservices_lab1.Server.Person> persons = new ArrayList<>();
        
        try 
        {
            statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from persons".concat(condition.toString()).concat(";"));
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Date dateOfBirth = rs.getDate("date_of_birth");
                String sex = rs.getString("sex");
                webservices_lab1.Server.Person person = new webservices_lab1.Server.Person(name, surname, dateOfBirth, sex);
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
