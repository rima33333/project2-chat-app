package phase1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
   
    
    
    public static Connection getConnection() throws SQLException  {
        Connection con=null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginformytvideo", "root", "");
            } catch (ClassNotFoundException ex) {
                throw new SQLException("MySQL JDBC driver not found", ex);
            }
             
       
        return con;
    }
    
   
}
