package admintechinventory.Dao.Client;

import admintechinventory.Dao.ConexionBD;
import admintechinventory.Models.Client;
import admintechinventory.Models.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GetClientDAO {

    public boolean addClient(Client client) {
        String sql = "{CALL AddClient(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = ConexionBD.getConnection(); CallableStatement cs = conn.prepareCall(sql)) {

            Person p = client.getPerson();

            cs.setString(1, p.getName());
            cs.setString(2, p.getLastName());
            cs.setString(3, p.getTypeDni());
            cs.setString(4, p.getDni());
            cs.setString(5, p.getAddress());
            cs.setString(6, p.getTelephone());
            cs.setString(7, p.getMail());

            cs.execute();
            JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }
}
