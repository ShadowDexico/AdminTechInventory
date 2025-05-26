package admintechinventory.Dao.Login;

import admintechinventory.Dao.ConexionBD;
import admintechinventory.Models.Sesion;
import java.sql.*;
import admintechinventory.Models.User;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean validateUser(User user) {
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("{CALL Login(?, ?)}")) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");

                // Guardar usuario en sesión
                user.setRole(role);
                Sesion.userActuality = user;

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return false;
    }
}
