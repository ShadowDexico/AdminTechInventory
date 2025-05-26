package admintechinventory.Dao.Client;

import admintechinventory.Dao.ConexionBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class DNITypeDAO {

    public List<String> getDNITypes() {
        List<String> dniTypes = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("CALL ShowDNITypes()"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dniTypes.add(rs.getString("type"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener tipos de DNI: " + e.getMessage());
        }
        return dniTypes;
    }
}
