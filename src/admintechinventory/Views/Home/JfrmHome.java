package admintechinventory.Views.Home;

import admintechinventory.Controllers.Login.LoginController;
import admintechinventory.Controllers.client.ClientController;
import admintechinventory.Controllers.client.DNITypeController;
import admintechinventory.Controllers.client.GetClientController;
import admintechinventory.Controllers.repairs.PaymentMethodController;
import admintechinventory.Controllers.repairs.ServiceController;
import admintechinventory.Dao.Client.ClientDAO;
import admintechinventory.Dao.Client.DNITypeDAO;
import admintechinventory.Dao.Client.GetClientDAO;
import admintechinventory.Dao.ConexionBD;
import admintechinventory.Dao.Reports.ReportDao;
import admintechinventory.Models.Client;
import admintechinventory.Models.Person;
import admintechinventory.Models.Sesion;
import admintechinventory.Views.Login.JfrmLoginUser;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JfrmHome extends javax.swing.JFrame {

    private ReportDao reportDao = new ReportDao(ConexionBD.getConnection());
    private ClientDAO clientDAO = new ClientDAO(ConexionBD.getConnection());
    private ClientController clientController = new ClientController(clientDAO);
    private ServiceController serviceC = new ServiceController();
    private PaymentMethodController paymentMethod = new PaymentMethodController();

    private int selectedClientId = -1;
    private frmRepairs frmRepairs = null;

    public JfrmHome() {
        initComponents();

        loadReportsComboBox();
        setupComboBoxListener();
        loadClientsTable();
        loadServices();
        loadPaymentMethod();
        setupClientTableClick();
        loadTypeDNI();
    }

    private void loadReportsComboBox() {
        comboxReports.removeAllItems();
        comboxReports.addItem("Select to report...");
        comboxReports.addItem("Common Faults");
        comboxReports.addItem("Time Average Faults");
        comboxReports.addItem("Frequent Customers");
        comboxReports.addItem("Repair Income");
    }

    private void setupComboBoxListener() {
        comboxReports.addActionListener(e -> {
            String selectedReport = comboxReports.getSelectedItem().toString();
            showSelectedReport(selectedReport);
        });
    }

    private void showSelectedReport(String reportName) {
        switch (reportName) {
            case "Common Faults":
                tableReports.setModel(reportDao.getCommonFaults());
                break;
            case "Time Average Faults":
                tableReports.setModel(reportDao.getTimeAverageFaults());
                break;
            case "Frequent Customers":
                tableReports.setModel(reportDao.getFrequentCustomers());
                break;
            case "Repair Income":
                tableReports.setModel(reportDao.getRepairIncome());
                break;
            default:
                JOptionPane.showMessageDialog(this, "Select a valid report.");
                break;
        }
    }

    private void loadClientsTable() {
        tableClient.setModel(clientController.getClientsTableModel());
    }

    private void setupClientTableClick() {
        tableClient.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableClient.getSelectedRow();
                if (selectedRow >= 0) {
                    try {
                        selectedClientId = Integer.parseInt(tableClient.getValueAt(selectedRow, 0).toString());
                        JOptionPane.showMessageDialog(this, "Client select: " + selectedClientId);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Error: ID de client incorrect.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        btnRepairPhone.addActionListener(e -> {
            if (selectedClientId == -1) {
                JOptionPane.showMessageDialog(this, "Select client please", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                insertRepair(selectedClientId);
            }
        });
    }

    private int callGetServiceId(String serviceName) {
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("CALL getServiceIdByName(?)")) {
            stmt.setString(1, serviceName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error calling getServiceIdByName: " + e.getMessage());
        }
        return -1;
    }

    private int callGetPaymentMethodId(String methodName) {
        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("CALL getPaymentMethodIdByName(?)")) {
            stmt.setString(1, methodName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error calling getPaymentMethodIdByName: " + e.getMessage());
        }
        return -1;
    }

    private void insertRepair(int clientId) {
        if (clientId <= 0) {
            JOptionPane.showMessageDialog(this, "Please select a client from the table before adding a repair.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String device = txtDevice.getText().trim();
        String brand = txtBrand.getText().trim();
        String model = txtModel.getText().trim();
        String faultDesc = txtaFaultDescription.getText().trim();
        java.util.Date delivery = dcDeliveryDate.getDate();
        String costText = txtRepairCost.getText().trim();

        String serviceName = comboxService.getSelectedItem().toString();
        String methodName = comboxPaymentMethod.getSelectedItem().toString();

        StringBuilder errors = new StringBuilder();

        if (device.isEmpty()) {
            errors.append("- Device cannot be empty.\n");
        }
        if (brand.isEmpty()) {
            errors.append("- Brand cannot be empty.\n");
        }
        if (model.isEmpty()) {
            errors.append("- Model cannot be empty.\n");
        }
        if (faultDesc.isEmpty()) {
            errors.append("- Fault description cannot be empty.\n");
        }
        if (delivery == null) {
            errors.append("- Delivery date must be selected.\n");
        }
        if (costText.isEmpty()) {
            errors.append("- Repair cost cannot be empty.\n");
        } else {
            try {
                Double.parseDouble(costText);
            } catch (NumberFormatException e) {
                errors.append("- Repair cost must be a valid number.\n");
            }
        }
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, "Please correct the following errors:\n" + errors.toString(), "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double cost = Double.parseDouble(costText);
        int idService = callGetServiceId(serviceName);
        int idPaymentMethod = callGetPaymentMethodId(methodName);
        int idMaterial = 1;
        int idFaultType = 1;
        int idStatus = 1;

        if (clientHasRepairOfType(clientId, idService, device, model)) {
            JOptionPane.showMessageDialog(this, "This client already has a repair for this device and service.");
            return;
        }

        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("CALL insertRepair(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, device);
            stmt.setString(2, brand);
            stmt.setString(3, model);
            stmt.setString(4, faultDesc);
            stmt.setTimestamp(5, new java.sql.Timestamp(delivery.getTime()));
            stmt.setDouble(6, cost);
            stmt.setInt(7, clientId);
            stmt.setInt(8, idMaterial);
            stmt.setInt(9, idFaultType);
            stmt.setInt(10, idPaymentMethod);
            stmt.setInt(11, idStatus);
            stmt.setInt(12, idService);
            stmt.execute();

            JOptionPane.showMessageDialog(this, "Repair registered successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inserting repair: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public boolean clientHasRepairOfType(int clientId, int idService, String device, String model) {
        boolean exists = false;

        try (Connection conn = ConexionBD.getConnection(); CallableStatement stmt = conn.prepareCall("CALL CheckExistingRepair(?, ?, ?, ?)")) {

            stmt.setInt(1, clientId);
            stmt.setInt(2, idService);
            stmt.setString(3, device);
            stmt.setString(4, model);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt("repair_count") > 0) {
                exists = true;
            }
        } catch (SQLException e) {
            System.err.println("Error checking repair type: " + e.getMessage());
        }

        return exists;
    }

    public void adminControllers() {
        comboxReports.setEnabled(false);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setSelectedIndex(1);
        btnShowRepairs.setEnabled(false);
    }

    private void loadServices() {
        comboxService.removeAllItems();
        comboxService.addItem("Select to service...");

        List<String> services = serviceC.getServices();
        for (String service : services) {
            comboxService.addItem(service);
        }
        comboxService.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                if (comboxService.getSelectedItem().equals("Select to service...")) {
                    JOptionPane.showMessageDialog(this, "Select a valid service.");
                    comboxService.setSelectedIndex(1);
                }
            }
        });
    }

    private void loadPaymentMethod() {
        comboxPaymentMethod.removeAllItems();
        comboxPaymentMethod.addItem("Select to payment method...");

        List<String> methods = paymentMethod.getPaymentMethod();
        for (String method : methods) {
            comboxPaymentMethod.addItem(method);
        }
        comboxPaymentMethod.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                if (comboxPaymentMethod.getSelectedItem().equals("Select to payment method...")) {
                    JOptionPane.showMessageDialog(this, "Select a valid payment method.");
                    comboxPaymentMethod.setSelectedIndex(1);
                }
            }
        });
    }

    private void loadTypeDNI() {
        comboxTypeDNI.removeAllItems();
        comboxTypeDNI.addItem("Select a DNI Type...");

        DNITypeDAO dao = new DNITypeDAO();
        DNITypeController controller = new DNITypeController(dao);

        List<String> dniTypes = controller.getDNITypes();

        for (String type : dniTypes) {
            comboxTypeDNI.addItem(type);
        }

        comboxTypeDNI.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                if (comboxTypeDNI.getSelectedItem().equals("Select a DNI Type...")) {
                    JOptionPane.showMessageDialog(this, "Please select a valid DNI Type.");
                    comboxTypeDNI.setSelectedIndex(1);
                }
            }
        });
    }

    private void cleanFields() {
        txtName.setText("");
        txtLastName.setText("");
        txtNdni.setText("");
        txtNumberPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        comboxTypeDNI.setSelectedIndex(0);
    }

    private int getSelectedClientIdFromTable() {
        int selectedRow = tableClient.getSelectedRow();
        if (selectedRow == -1) {
            return -1; 
        }
        return (int) tableClient.getValueAt(selectedRow, 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        pnlHeader = new javax.swing.JPanel();
        lblPositionEmployee = new javax.swing.JLabel();
        lblNameEmployee1 = new javax.swing.JLabel();
        btnReturnLogin = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlReport = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboxReports = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableReports = new javax.swing.JTable();
        pnlRepair = new javax.swing.JPanel();
        txtSearchClient = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClient = new javax.swing.JTable();
        txtRepairCost = new javax.swing.JTextField();
        txtDevice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboxService = new javax.swing.JComboBox<>();
        comboxPaymentMethod = new javax.swing.JComboBox<>();
        txtBrand = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaFaultDescription = new javax.swing.JTextArea();
        btnGetClient = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnRepairPhone = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        brnClear = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dcDeliveryDate = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        btnShowRepairs = new javax.swing.JButton();
        pnlProduct = new javax.swing.JPanel();
        cbxTypeProduct = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        pnlAddClient = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtNdni = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNumberPhone = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        btnAddClient = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        comboxTypeDNI = new javax.swing.JComboBox<>();
        pnlAddSupplier = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox<>();
        pnlAddProduct = new javax.swing.JPanel();
        cbxTypeProduct1 = new javax.swing.JComboBox<>();
        jTextField10 = new javax.swing.JTextField();
        cbxTypeProduct2 = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();
        cbxTypeProduct3 = new javax.swing.JComboBox<>();
        cbxTypeProduct4 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(0, 204, 204));

        lblPositionEmployee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPositionEmployee.setText("Position:");

        lblNameEmployee1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNameEmployee1.setText("Employee:");

        btnReturnLogin.setBackground(new java.awt.Color(255, 0, 0));
        btnReturnLogin.setText("Return Login");
        btnReturnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnReturnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 479, Short.MAX_VALUE)
                .addComponent(lblNameEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(lblPositionEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPositionEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameEmployee1)
                    .addComponent(btnReturnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 80));

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        pnlReport.setBackground(new java.awt.Color(0, 0, 0));
        pnlReport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Type report:");
        pnlReport.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        comboxReports.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlReport.add(comboxReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 240, -1));

        tableReports.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableReports.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tableReports);

        pnlReport.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 880, 460));

        jTabbedPane1.addTab("Reports", pnlReport);

        pnlRepair.setBackground(new java.awt.Color(0, 0, 0));
        pnlRepair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlRepair.add(txtSearchClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 230, -1));

        tableClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableClient);

        pnlRepair.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));
        pnlRepair.add(txtRepairCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 150, -1));
        pnlRepair.add(txtDevice, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 150, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phone Model:");
        pnlRepair.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Type service:");
        pnlRepair.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, -1, -1));

        comboxService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlRepair.add(comboxService, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 150, -1));

        comboxPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlRepair.add(comboxPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, 150, -1));
        pnlRepair.add(txtBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 150, -1));

        txtaFaultDescription.setColumns(20);
        txtaFaultDescription.setRows(5);
        jScrollPane2.setViewportView(txtaFaultDescription);

        pnlRepair.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, 380, 180));

        btnGetClient.setText("Add client");
        btnGetClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetClientActionPerformed(evt);
            }
        });
        pnlRepair.add(btnGetClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 520, 130, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Add repair");
        pnlRepair.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 130, 40));

        btnRepairPhone.setText("Repair phone");
        btnRepairPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepairPhoneActionPerformed(evt);
            }
        });
        pnlRepair.add(btnRepairPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 510, 130, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search client:");
        pnlRepair.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        brnClear.setText("Clear request");
        pnlRepair.add(brnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 510, 130, 40));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Repair cost");
        pnlRepair.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Device:");
        pnlRepair.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));
        pnlRepair.add(txtModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 130, 150, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Phone brand:");
        pnlRepair.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Fault description");
        pnlRepair.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, -1, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Delivery date");
        pnlRepair.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));
        pnlRepair.add(dcDeliveryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, 150, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Payment method:");
        pnlRepair.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, -1, -1));

        btnShowRepairs.setText("Show repairs");
        btnShowRepairs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowRepairsActionPerformed(evt);
            }
        });
        pnlRepair.add(btnShowRepairs, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 120, 40));

        jTabbedPane1.addTab("Repairs", pnlRepair);

        pnlProduct.setBackground(new java.awt.Color(0, 0, 0));
        pnlProduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxTypeProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlProduct.add(cbxTypeProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 170, -1));
        pnlProduct.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 170, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        pnlProduct.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 470, 310));

        jButton4.setText("Add client");
        pnlProduct.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 510, 120, 40));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Name product:");
        pnlProduct.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Type product:");
        pnlProduct.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jButton5.setText("Search");
        pnlProduct.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 90, -1));

        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        pnlProduct.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 20, 600));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Name product:");
        pnlProduct.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 90, -1));
        pnlProduct.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 170, -1));

        jButton6.setText("Search");
        pnlProduct.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, 90, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Name product:");
        pnlProduct.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 90, -1));
        pnlProduct.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 170, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        pnlProduct.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 470, 310));

        jButton7.setText("Buy");
        pnlProduct.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 120, 40));

        jTabbedPane1.addTab("Products", pnlProduct);

        pnlAddClient.setBackground(new java.awt.Color(0, 0, 0));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Names:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Last names:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Type ID:");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("N. Document:");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Phone number:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Email:");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Address:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Register Person:");

        btnAddClient.setText("Add Client");
        btnAddClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClientActionPerformed(evt);
            }
        });

        jButton11.setText("Clean data");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        comboxTypeDNI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlAddClientLayout = new javax.swing.GroupLayout(pnlAddClient);
        pnlAddClient.setLayout(pnlAddClientLayout);
        pnlAddClientLayout.setHorizontalGroup(
            pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddClientLayout.createSequentialGroup()
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlAddClientLayout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addGap(33, 33, 33)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlAddClientLayout.createSequentialGroup()
                                    .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddClientLayout.createSequentialGroup()
                                                .addComponent(jLabel28)
                                                .addGap(33, 33, 33))
                                            .addGroup(pnlAddClientLayout.createSequentialGroup()
                                                .addComponent(jLabel29)
                                                .addGap(62, 62, 62)))
                                        .addGroup(pnlAddClientLayout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addGap(33, 33, 33)))
                                    .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtLastName)
                                        .addComponent(comboxTypeDNI, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNdni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlAddClientLayout.createSequentialGroup()
                                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15))))
                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(jLabel35))
                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(btnAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(361, Short.MAX_VALUE))
        );
        pnlAddClientLayout.setVerticalGroup(
            pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddClientLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel35)
                .addGap(30, 30, 30)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(comboxTypeDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtNdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddClient, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Client", pnlAddClient);

        pnlAddSupplier.setBackground(new java.awt.Color(0, 0, 0));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Name supplier:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("N. Document:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Type ID:");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Email:");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Phone number:");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Address");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Company name:");

        jButton12.setText("Delete supplier");

        jButton13.setText("Add supplier");

        jButton14.setText("Edit");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable2);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnlAddSupplierLayout = new javax.swing.GroupLayout(pnlAddSupplier);
        pnlAddSupplier.setLayout(pnlAddSupplierLayout);
        pnlAddSupplierLayout.setHorizontalGroup(
            pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                                    .addComponent(jLabel37)
                                    .addGap(44, 44, 44)
                                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                                    .addComponent(jLabel42)
                                    .addGap(33, 33, 33)
                                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                                    .addComponent(jLabel40)
                                    .addGap(33, 33, 33)
                                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddSupplierLayout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(33, 33, 33)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddSupplierLayout.createSequentialGroup()
                                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(33, 33, 33)
                                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        pnlAddSupplierLayout.setVerticalGroup(
            pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddSupplierLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(pnlAddSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add supplier", pnlAddSupplier);

        pnlAddProduct.setBackground(new java.awt.Color(0, 0, 0));

        cbxTypeProduct1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTypeProduct2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTypeProduct3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTypeProduct4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        jButton9.setText("Exit");

        jButton10.setText("Save");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Name product:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Type product:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Cant product:");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Brad product:");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Model product:");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Purchase price per unit:");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Description product:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Supplier:");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Sales price per unit");

        javax.swing.GroupLayout pnlAddProductLayout = new javax.swing.GroupLayout(pnlAddProduct);
        pnlAddProduct.setLayout(pnlAddProductLayout);
        pnlAddProductLayout.setHorizontalGroup(
            pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(363, 363, 363))
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44))
                                .addGap(60, 60, 60)
                                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxTypeProduct1, 0, 211, Short.MAX_VALUE)
                                    .addComponent(jTextField10))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel46))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel47)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbxTypeProduct2, 0, 260, Short.MAX_VALUE)
                        .addComponent(cbxTypeProduct3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cbxTypeProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
            .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlAddProductLayout.createSequentialGroup()
                    .addGap(177, 177, 177)
                    .addComponent(jLabel49)
                    .addContainerGap(778, Short.MAX_VALUE)))
        );
        pnlAddProductLayout.setVerticalGroup(
            pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddProductLayout.createSequentialGroup()
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxTypeProduct3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47)))
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel44)
                                    .addComponent(cbxTypeProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel43)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxTypeProduct2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel46)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlAddProductLayout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddProductLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel50))))
                    .addGroup(pnlAddProductLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTypeProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(40, 40, 40)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel48))
                    .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51)))
                .addGap(76, 76, 76)
                .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
            .addGroup(pnlAddProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlAddProductLayout.createSequentialGroup()
                    .addGap(159, 159, 159)
                    .addComponent(jLabel49)
                    .addContainerGap(411, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Add products", pnlAddProduct);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1080, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowRepairsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowRepairsActionPerformed

        if (frmRepairs == null || !frmRepairs.isVisible()) {
            frmRepairs = new frmRepairs();
            frmRepairs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frmRepairs.setVisible(true);
        }
    }//GEN-LAST:event_btnShowRepairsActionPerformed

    private void btnGetClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetClientActionPerformed
        jTabbedPane1.setSelectedComponent(pnlAddClient);
    }//GEN-LAST:event_btnGetClientActionPerformed

    private void btnAddClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClientActionPerformed

        try {
            int clientId = getSelectedClientIdFromTable();
            if (clientId <= 0) { 
                JOptionPane.showMessageDialog(this, "Please select a client from the table before adding a repair.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String name = txtName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String dni = txtNdni.getText().trim();
            String phone = txtNumberPhone.getText().trim();
            String email = txtEmail.getText().trim();
            String address = txtAddress.getText().trim();

            StringBuilder errors = new StringBuilder();
            
            if (comboxTypeDNI.getSelectedItem() == null || "Select a DNI Type...".equals(comboxTypeDNI.getSelectedItem().toString())) {
                errors.append("- Select a valid DNI Type.\n");
            }

            String typeDni = comboxTypeDNI.getSelectedItem().toString();

            if (name.isEmpty()) {
                errors.append("- Name cannot be empty.\n");
            }

            if (lastName.isEmpty()) {
                errors.append("- Last Name cannot be empty.\n");
            }

            if (dni.isEmpty()) {
                errors.append("- DNI cannot be empty.\n");
            }

            if (!phone.isEmpty() && !phone.matches("^\\d{8,15}$")) {
                errors.append("- Invalid phone format. Only numbers (8-15 digits) are allowed.\n");
            }

            if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                errors.append("- Invalid email format.\n");
            }

            if (!address.isEmpty() && address.length() < 5) {
                errors.append("- Address must be at least 5 characters long.\n");
            }

            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(this, "Please correct the following errors:\n" + errors.toString(), "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Person person = new Person(name, lastName, typeDni, dni, address, phone, email);
            Client client = new Client(clientId, person);

            GetClientDAO dao = new GetClientDAO();
            GetClientController controller = new GetClientController(dao);

            boolean success = controller.addClient(client);
            JOptionPane.showMessageDialog(this, success ? "Client added successfully!" : "Error adding client.");

            if (success) {
                cleanFields();
                loadClientsTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unexpected error adding client: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAddClientActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        txtName.setText("");
        txtLastName.setText("");
        txtNdni.setText("");
        txtNumberPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        comboxTypeDNI.setSelectedIndex(0);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnRepairPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepairPhoneActionPerformed

    }//GEN-LAST:event_btnRepairPhoneActionPerformed

    private void btnReturnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnLoginActionPerformed
        int option = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea cerrar sesión?",
                "Confirmar cierre de sesión",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            Sesion.closedSesion();

            this.dispose();

            try {
                JfrmLoginUser login = new JfrmLoginUser();
                Connection conn = ConexionBD.getConnection(); // Obtener conexión
                LoginController controller = new LoginController(login, conn);
                login.setLocationRelativeTo(null);
                login.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error al abrir login: " + e.getMessage());
                System.exit(1);
            }
        }
    }//GEN-LAST:event_btnReturnLoginActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnClear;
    private javax.swing.JButton btnAddClient;
    private javax.swing.JButton btnGetClient;
    private javax.swing.JButton btnRepairPhone;
    private javax.swing.JButton btnReturnLogin;
    private javax.swing.JButton btnShowRepairs;
    private javax.swing.JComboBox<String> cbxTypeProduct;
    private javax.swing.JComboBox<String> cbxTypeProduct1;
    private javax.swing.JComboBox<String> cbxTypeProduct2;
    private javax.swing.JComboBox<String> cbxTypeProduct3;
    private javax.swing.JComboBox<String> cbxTypeProduct4;
    private javax.swing.JComboBox<String> comboxPaymentMethod;
    private javax.swing.JComboBox<String> comboxReports;
    private javax.swing.JComboBox<String> comboxService;
    private javax.swing.JComboBox<String> comboxTypeDNI;
    private com.toedter.calendar.JDateChooser dcDeliveryDate;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblNameEmployee1;
    private javax.swing.JLabel lblPositionEmployee;
    private javax.swing.JPanel pnlAddClient;
    private javax.swing.JPanel pnlAddProduct;
    private javax.swing.JPanel pnlAddSupplier;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlProduct;
    private javax.swing.JPanel pnlRepair;
    private javax.swing.JPanel pnlReport;
    private javax.swing.JTable tableClient;
    private javax.swing.JTable tableReports;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtDevice;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNdni;
    private javax.swing.JTextField txtNumberPhone;
    private javax.swing.JTextField txtRepairCost;
    private javax.swing.JTextField txtSearchClient;
    private javax.swing.JTextArea txtaFaultDescription;
    // End of variables declaration//GEN-END:variables
}
