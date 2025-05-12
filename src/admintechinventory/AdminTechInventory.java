package admintechinventory;

import admintechinventory.Dao.ConexionBD;
import admintechinventory.Views.Login.JfrmLoginUser;
import java.sql.Connection;
import admintechinventory.Controllers.Login.LoginController;

public class AdminTechInventory {
    public static void main(String args[]) {
        Connection connection = ConexionBD.getConnection();
        if (connection != null) {
            JfrmLoginUser pageJfrmLoginUser = new JfrmLoginUser();
            new LoginController(pageJfrmLoginUser, connection);
            pageJfrmLoginUser.setLocationRelativeTo(null);
            pageJfrmLoginUser.setVisible(true);
        } else {
            System.out.println("Application stopped. Could not connect to the database.");
        }
    }
}
