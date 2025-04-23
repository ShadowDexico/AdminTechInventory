package admintechinventory.Dao.Login;

import admintechinventory.Dao.ConexionBD;
import java.util.ArrayList;
import admintechinventory.Models.Rol;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.text.View;

public class RolDao {

    private Connection connection;

    public RolDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Rol> getAllRoles() {
        ArrayList<Rol> rols = new ArrayList<>();
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("{CALL ViewRols()}"); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Rol role = new Rol(rs.getInt("id"), rs.getString("name"));
                rols.add(role);
            }
        } catch (SQLException e) {
            System.out.println("Error loading roles: " + e.getMessage());
        }
        return rols;

    }
}
