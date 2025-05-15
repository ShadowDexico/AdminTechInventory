
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
        try (CallableStatement stmt = connection.prepareCall("{CALL CommonFaultReport()}");
             ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Fault Type"); 
            model.addColumn("Amount");      

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("fault type"),
                    rs.getInt("amount")
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
        try (CallableStatement stmt = connection.prepareCall("{CALL AverageFaultTimeReport()}");
             ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Fault Type");     
            model.addColumn("Average Hours"); 

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("FaultType"),
                    rs.getDouble("AverageHours")
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
        try (CallableStatement stmt = connection.prepareCall("{CALL FrequentCustomersReport()}");
             ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Client");              
            model.addColumn("Total Repairs");       
            model.addColumn("Requested Services");  

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Client"),
                    rs.getInt("TotalRepairs"),
                    rs.getString("RequestedServices")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    // 📌 Reporte de ingresos por reparaciones
    public DefaultTableModel getRepairIncome() {
        DefaultTableModel model = new DefaultTableModel();
        if (connection == null) {
            System.err.println("Error: No se pudo establecer conexión con la base de datos.");
            return model;
        }
        try (CallableStatement stmt = connection.prepareCall("{CALL RepairIncomeReport()}");
             ResultSet rs = stmt.executeQuery()) {

            model.addColumn("Date");             
            model.addColumn("Quantity Repairs");  
            model.addColumn("Total Revenue");     

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("date"),
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
