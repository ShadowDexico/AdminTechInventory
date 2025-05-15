package admintechinventory.Dao.Client;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class ClientDAO {

    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public DefaultTableModel ObtainClients() {
        DefaultTableModel model = new DefaultTableModel();

        if (connection == null) {
            System.err.println("Error: No se pudo establecer conexión con la base de datos.");
            return model;
        }

        try (CallableStatement stmt = connection.prepareCall("{CALL showclient()}"); ResultSet rs = stmt.executeQuery()) {
            model.addColumn("Name");
            model.addColumn("Last ,ame");
            model.addColumn("DNI type");
            model.addColumn("DNI");
            model.addColumn("Address");
            model.addColumn("Telephone");
            model.addColumn("Mail");
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Name"),
                    rs.getString("Last name"),
                    rs.getString("DNI type"),
                    rs.getString("DNI"),
                    rs.getString("Address"),
                    rs.getString("Telephone"),
                    rs.getString("Mail")
                });
            }

        } catch (SQLException e) {
            System.err.println("Error ejecutando procedimiento almacenado: " + e.getMessage());
        }

        return model;
    }

}
