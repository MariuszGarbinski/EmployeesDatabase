package employeeswsinf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mariusz Garbiński (29304)
 */
public class DbConnection {
    
    public Connection Connect(){
        
        try{
            String url = "jdbc:mysql://localhost:3306/java?useSSL=false";      
            String username = "root";  
            String password = "Sound1423Garden";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);

            return conn;
            
        }catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null,ex);
        }
    return null;
    }
}