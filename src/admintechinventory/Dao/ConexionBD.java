package admintechinventory.Dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionBD {
    static String url = "jdbc:mysql://localhost/techinventory";
    static String user = "root";
    static String password_BD = "1234";
    
    public static Connection getConnection(){
        Connection connection = null;
        try {
            return DriverManager.getConnection(url, user, password_BD);     
        } catch (SQLException e) {
            System.out.println("Error connecting to DB: " + e.getMessage());
            System.out.println("lol");
            return null;
        
        }
    }
}
