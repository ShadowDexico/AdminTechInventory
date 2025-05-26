package admintechinventory.Dao.repair;

import admintechinventory.Dao.ConexionBD;
import admintechinventory.Models.Repair;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RepairDAO {

    public boolean insertRepair(Repair repair) {
        String sql = "{CALL insertRepair(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = ConexionBD.getConnection(); CallableStatement stmt = con.prepareCall(sql)) {

            stmt.setString(1, repair.getDevice());
            stmt.setString(2, repair.getBrand());
            stmt.setString(3, repair.getModel());
            stmt.setString(4, repair.getFaultDescription());

            java.sql.Timestamp sqlDate = new java.sql.Timestamp(repair.getDeliveryDate().getTime());
            stmt.setTimestamp(5, sqlDate);

            stmt.setDouble(6, repair.getRepairCost());
            stmt.setInt(7, repair.getIdClient());
            stmt.setInt(8, repair.getIdMaterial());
            stmt.setInt(9, repair.getIdFaultType());
            stmt.setInt(10, repair.getIdPaymentMethod());
            stmt.setInt(11, repair.getIdStatus());
            stmt.setInt(12, repair.getIdService());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar reparación: " + e.getMessage());
            return false;
        }
    }
}
