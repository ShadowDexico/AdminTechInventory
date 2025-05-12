package admintechinventory.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
    
    static String url = "jdbc:mysql://localhost/techinventory";
    static String user = "root";
    static String password_BD = "";
    
    public static Connection getConnection(){
        Connection connection = null;
        try {
            return DriverManager.getConnection(url, user, password_BD);     
        } catch (SQLException e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
            return null;
        }
    }
    
}
