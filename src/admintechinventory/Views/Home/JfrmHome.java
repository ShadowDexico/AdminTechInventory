package admintechinventory.Views.Home;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class JfrmHome extends javax.swing.JFrame {


    public JfrmHome() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblPositionEmployee = new javax.swing.JLabel();
        lblNameEmployee1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(683, Short.MAX_VALUE)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPositionEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(284, 284, 284))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNameEmployee1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPositionEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 80));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("jLabel3");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 240, -1));

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
        jScrollPane3.setViewportView(jTable2);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 880, 460));

        jTabbedPane1.addTab("tab1", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("jTextField1");
        jPanel5.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 230, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jTextField3.setText("jTextField3");
        jPanel5.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 150, -1));

        jLabel7.setText("jLabel7");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        jTextField2.setText("jTextField2");
        jPanel5.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 150, -1));

        jLabel8.setText("jLabel7");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, -1));

        jLabel9.setText("jLabel9");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 200, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 150, -1));

        jLabel10.setText("jLabel9");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 150, -1));

        jLabel11.setText("jLabel11");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, -1, -1));

        jTextField4.setText("jTextField4");
        jPanel5.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 150, -1));

        jLabel12.setText("jLabel11");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, -1, -1));

        jTextField5.setText("jTextField4");
        jPanel5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 150, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 380, 180));

        jButton1.setText("jButton1");
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 130, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("jLabel13");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 200, 40));

        jButton2.setText("jButton1");
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 130, 40));

        jLabel2.setText("Buscar Cliente:");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jButton3.setText("jButton1");
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 530, 130, 40));

        jTabbedPane1.addTab("tab2", jPanel5);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("jLabel4");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 170, -1));

        jTextField6.setText("jTextField6");
        jPanel6.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 180, -1));

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

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 530, 310));

        jLabel5.setText("jLabel5");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, -1, -1));

        jLabel6.setText("jLabel6");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        jTextField7.setText("jTextField7");
        jPanel6.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 100, -1));

        jLabel14.setText("jLabel5");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        jTextField8.setText("jTextField7");
        jPanel6.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 410, 100, -1));

        jButton4.setText("jButton4");
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        jTabbedPane1.addTab("tab3", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1070, 630));

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lblNameEmployee1;
    private javax.swing.JLabel lblPositionEmployee;
    private javax.swing.JPanel pnlHeader;
    // End of variables declaration//GEN-END:variables
}
