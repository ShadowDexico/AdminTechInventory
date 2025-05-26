package admintechinventory.Dao.repair;

import admintechinventory.Dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ServiceDAO {

    public boolean insertService(String service) {
        String sql = "CALL insertService(?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, service);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getServices() {
        List<String> services = new ArrayList<>();
        String sql = "CALL getServices()";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                services.add(rs.getString("service"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

}
