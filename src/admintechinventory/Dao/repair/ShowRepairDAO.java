package admintechinventory.Dao.repair;

import admintechinventory.Dao.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ShowRepairDAO {

    public DefaultTableModel ShowRepairs() {
        DefaultTableModel model = new DefaultTableModel();

        String query = "{CALL ShowRepairs()}";
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall(query); ResultSet rs = stmt.executeQuery()) {

            model.addColumn("ID");
            model.addColumn("Device");
            model.addColumn("Brand");
            model.addColumn("Model");
            model.addColumn("Fault");
            model.addColumn("Entry Date");
            model.addColumn("Delivery Date");
            model.addColumn("Cost");
            model.addColumn("Client ID");
            model.addColumn("Material ID");
            model.addColumn("Fault Type");
            model.addColumn("Payment Method");
            model.addColumn("Status");
            model.addColumn("Service");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("device"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("faultDescription"),
                    rs.getTimestamp("entryDate"),
                    rs.getTimestamp("deliveryDate"),
                    rs.getDouble("repairCost"),
                    rs.getInt("idClient"),
                    rs.getInt("idMaterial"),
                    rs.getInt("idFaultType"),
                    rs.getInt("idPaymentMethods"),
                    rs.getInt("idStatus"),
                    rs.getInt("idService")
                });
            }

        } catch (SQLException e) {
            System.err.println("Error obteniendo reparaciones: " + e.getMessage());
        }
        return model;
    }
}
