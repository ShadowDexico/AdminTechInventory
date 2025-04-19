package admintechinventory.Dao.Login;

import admintechinventory.Dao.ConexionBD;
import java.sql.*;
import admintechinventory.Models.User;

public class UserDao {

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public boolean validateUser(User user) {
        boolean isValid = false;
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("{CALL Login(?, ?, ?)}")) {
            stmt.setString(1, user.getRole());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("coincidencias");
                isValid = count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return isValid;
    }
}
