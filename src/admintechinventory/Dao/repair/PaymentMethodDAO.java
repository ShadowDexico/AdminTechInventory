
package admintechinventory.Dao.repair;

import admintechinventory.Dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO {
    public boolean insertPaymentMethodDAO(String paymentMethod) {
        String sql = "CALL PaymentMethodDAO(?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paymentMethod);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getPaymentMethodDAO() {
        List<String> services = new ArrayList<>();
        String sql = "CALL getPaymentMethods()";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                services.add(rs.getString("methods"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}
