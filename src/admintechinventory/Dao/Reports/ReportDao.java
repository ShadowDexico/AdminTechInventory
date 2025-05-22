package admintechinventory.Dao.Reports;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ReportDao {

    private Connection connection;

    public ReportDao(Connection connection) {
        this.connection = connection;
    }

    public DefaultTableModel getCommonFaults() {
        DefaultTableModel model = new DefaultTableModel();
        if (connection == null) {
            System.err.println("Error: No se pudo establecer conexión con la base de datos.");
            return model;
        }
        try (CallableStatement stmt = connection.prepareCall("{CALL CommonFaultReport()}"); ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Fault Type");
            model.addColumn("Amount");
            model.addColumn("First Occurrence");
            model.addColumn("Latest Occurrence");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("fault type"),
                    rs.getInt("amount"),
                    rs.getTimestamp("first occurrence"),
                    rs.getTimestamp("latest occurrence")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public DefaultTableModel getTimeAverageFaults() {
        DefaultTableModel model = new DefaultTableModel();
        if (connection == null) {
            System.err.println("Error: No se pudo establecer conexión con la base de datos.");
            return model;
        }
        try (CallableStatement stmt = connection.prepareCall("{CALL AverageFaultTimeReport()}"); ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Fault Type");
            model.addColumn("Average Hours");
            model.addColumn("Earliest Repair");
            model.addColumn("Latest Repair");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("FaultType"),
                    rs.getDouble("AverageHours"),
                    rs.getTimestamp("earliest repair"),
                    rs.getTimestamp("latest repair")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public DefaultTableModel getFrequentCustomers() {
        DefaultTableModel model = new DefaultTableModel();
        if (connection == null) {
            System.err.println("Error: No se pudo establecer conexión con la base de datos.");
            return model;
        }
        try (CallableStatement stmt = connection.prepareCall("{CALL FrequentCustomersReport()}"); ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Client");
            model.addColumn("Total Repairs");
            model.addColumn("Requested Services");
            model.addColumn("First Repair");
            model.addColumn("Latest Repair");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Client"),
                    rs.getInt("TotalRepairs"),
                    rs.getString("RequestedServices"),
                    rs.getTimestamp("first repair"),
                    rs.getTimestamp("latest repair")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public DefaultTableModel getRepairIncome() {
        DefaultTableModel model = new DefaultTableModel();
        if (connection == null) {
            System.err.println("Error: No se pudo establecer conexión con la base de datos.");
            return model;
        }
        try (CallableStatement stmt = connection.prepareCall("{CALL RepairIncomeReport()}"); ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Repair Date");
            model.addColumn("Quantity Repairs");
            model.addColumn("Total Revenue");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getDate("repair date"),
                    rs.getInt("QuantityRepairs"),
                    rs.getDouble("TotalRevenue")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

}
