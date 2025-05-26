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

    public User validateUser(String username, String password) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            System.out.println("Username y password no pueden estar vacíos");
            return null;
        }
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("{CALL Login(?, ?)}")) {
            stmt.setString(1, username.trim());
            stmt.setString(2, password.trim());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setRole(rs.getString("role"));
                try {
                    newUser.setIdRol(rs.getInt("idRol"));
                } catch (SQLException e) {
                    System.out.println("Columna idRol no encontrada: " + e.getMessage());
                }
                try {
                    newUser.setId(rs.getInt("id"));
                } catch (SQLException e) {
                    System.out.println("Columna id no encontrada: " + e.getMessage());
                }

                return newUser;
            }

        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
            e.printStackTrace(); 
        }

        return null;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        if (username == null || username.trim().isEmpty()
                || oldPassword == null || oldPassword.trim().isEmpty()
                || newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }

        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("{CALL ChangePassword(?, ?, ?)}")) {

            stmt.setString(1, username.trim());
            stmt.setString(2, oldPassword.trim());
            stmt.setString(3, newPassword.trim());

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("Error al cambiar contraseña: " + e.getMessage());
            return false;
        }
    }
}
