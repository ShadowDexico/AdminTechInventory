package admintechinventory.Views.Home;

import admintechinventory.Controllers.client.ClientController;
import admintechinventory.Dao.Client.ClientDAO;
import admintechinventory.Dao.ConexionBD;
import admintechinventory.Dao.Reports.ReportDao;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class JfrmHome extends javax.swing.JFrame {

    private ReportDao reportDao = new ReportDao(ConexionBD.getConnection());

    private ClientDAO clientDAO = new ClientDAO(ConexionBD.getConnection());
    private ClientController clientController = new ClientController(clientDAO);

    public JfrmHome() {
        initComponents();

        loadReportsComboBox();
        setupComboBoxListener();
        loadClientsTable();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblPositionEmployee = new javax.swing.JLabel();
        lblNameEmployee1 = new javax.swing.JLabel();
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
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnGetClient = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
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
        jTextField13 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(0, 204, 204));

        lblPositionEmployee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPositionEmployee.setForeground(new java.awt.Color(0, 0, 0));
        lblPositionEmployee.setText("Position:");

        lblNameEmployee1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNameEmployee1.setForeground(new java.awt.Color(0, 0, 0));
        lblNameEmployee1.setText("Employee:");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addContainerGap(624, Short.MAX_VALUE)
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
                    .addComponent(lblNameEmployee1))
                .addContainerGap(27, Short.MAX_VALUE))
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

        tableReports.setBackground(new java.awt.Color(255, 255, 255));
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
        tableReports.setSelectionForeground(new java.awt.Color(0, 0, 0));
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
        pnlRepair.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 150, -1));
        pnlRepair.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 150, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phone brand:");
        pnlRepair.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Type service:");
        pnlRepair.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlRepair.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 150, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Payment method:");
        pnlRepair.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 200, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlRepair.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 150, -1));
        pnlRepair.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 150, -1));
        pnlRepair.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 150, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        pnlRepair.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 380, 180));

        btnGetClient.setForeground(new java.awt.Color(0, 0, 0));
        btnGetClient.setText("Add client");
        pnlRepair.add(btnGetClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 130, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Add repair");
        pnlRepair.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 90, 40));

        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Repair phone");
        pnlRepair.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, 130, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search client:");
        pnlRepair.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Clear request");
        pnlRepair.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 470, 130, 40));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Spare part value:");
        pnlRepair.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Phone model:");
        pnlRepair.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Cashing");
        pnlRepair.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, -1, -1));

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

        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Add client");
        pnlProduct.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 510, 120, 40));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Name product:");
        pnlProduct.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Type product:");
        pnlProduct.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Search");
        pnlProduct.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 90, -1));

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        pnlProduct.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 20, 600));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Name product:");
        pnlProduct.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 90, -1));
        pnlProduct.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 170, -1));

        jButton6.setForeground(new java.awt.Color(0, 0, 0));
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

        jButton7.setForeground(new java.awt.Color(0, 0, 0));
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

        jButton8.setForeground(new java.awt.Color(0, 0, 0));
        jButton8.setText("Add person");

        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("Clean data");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(jTextField14)
                                        .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlAddClientLayout.createSequentialGroup()
                                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15))))
                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(jLabel35))
                    .addGroup(pnlAddClientLayout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(pnlAddClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("Delete supplier");

        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("Add supplier");

        jButton14.setForeground(new java.awt.Color(0, 0, 0));
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

        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setText("Exit");

        jButton10.setForeground(new java.awt.Color(0, 0, 0));
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetClient;
    private javax.swing.JComboBox<String> cbxTypeProduct;
    private javax.swing.JComboBox<String> cbxTypeProduct1;
    private javax.swing.JComboBox<String> cbxTypeProduct2;
    private javax.swing.JComboBox<String> cbxTypeProduct3;
    private javax.swing.JComboBox<String> cbxTypeProduct4;
    private javax.swing.JComboBox<String> comboxReports;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
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
    private javax.swing.JTextField txtSearchClient;
    // End of variables declaration//GEN-END:variables
}
