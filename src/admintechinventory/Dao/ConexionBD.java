package admintechinventory.Dao;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionBD {
    static String url = "jdbc:mysql//localhost:3306/techinventory";
    static String user = "root";
    static String password_BD = "1234";
    
    public static Connection conection_BD(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password_BD);
            System.out.println("se conecto en esa monda");
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return connection;
    }
}
