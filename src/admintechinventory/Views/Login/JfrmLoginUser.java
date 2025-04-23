package admintechinventory.Views.Login;

import javax.swing.JOptionPane;
import javax.swing.*;

public class JfrmLoginUser extends javax.swing.JFrame {

    public JfrmLoginUser() {
        initComponents();
    }

    public JComboBox<String> getCmbRole() {
        return comboxRol;
    }
    
    public JTextField getTxtUsername() {
        return txtUser;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnSignIn() {
        return btnSingIn;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelLogin = new javax.swing.JPanel();
        jPanelBackgroundImage = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnSingIn = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        comboxRol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("👤 Login");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanelLogin.setBackground(new java.awt.Color(255, 255, 255));
        jpanelLogin.setPreferredSize(new java.awt.Dimension(800, 500));
        jpanelLogin.setLayout(null);

        jPanelBackgroundImage.setBackground(new java.awt.Color(0, 153, 153));
        jPanelBackgroundImage.setPreferredSize(new java.awt.Dimension(400, 500));

        javax.swing.GroupLayout jPanelBackgroundImageLayout = new javax.swing.GroupLayout(jPanelBackgroundImage);
        jPanelBackgroundImage.setLayout(jPanelBackgroundImageLayout);
        jPanelBackgroundImageLayout.setHorizontalGroup(
            jPanelBackgroundImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanelBackgroundImageLayout.setVerticalGroup(
            jPanelBackgroundImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jpanelLogin.add(jPanelBackgroundImage);
        jPanelBackgroundImage.setBounds(0, 0, 400, 500);

        jLabel1.setFont(new java.awt.Font("Lato", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("LOGIN");
        jpanelLogin.add(jLabel1);
        jLabel1.setBounds(520, 50, 120, 50);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Password:");
        jpanelLogin.add(jLabel2);
        jLabel2.setBounds(430, 240, 80, 16);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("User:");
        jpanelLogin.add(jLabel3);
        jLabel3.setBounds(430, 170, 80, 16);
        jpanelLogin.add(txtUser);
        txtUser.setBounds(430, 190, 260, 26);

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setForeground(new java.awt.Color(0, 0, 0));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jpanelLogin.add(btnCancel);
        btnCancel.setBounds(590, 330, 76, 27);

        btnSingIn.setBackground(new java.awt.Color(255, 255, 255));
        btnSingIn.setForeground(new java.awt.Color(0, 0, 0));
        btnSingIn.setText("Sing In");
        btnSingIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingInActionPerformed(evt);
            }
        });
        jpanelLogin.add(btnSingIn);
        btnSingIn.setBounds(490, 330, 76, 27);

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setForeground(new java.awt.Color(0, 0, 0));
        btnExit.setText("X");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jpanelLogin.add(btnExit);
        btnExit.setBounds(740, 0, 60, 30);
        jpanelLogin.add(txtPassword);
        txtPassword.setBounds(430, 260, 260, 26);

        comboxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jpanelLogin.add(comboxRol);
        comboxRol.setBounds(520, 120, 100, 26);

        getContentPane().add(jpanelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        txtUser.setText("");
        txtPassword.setText("");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Gracias por usar nuestro programa :) ");
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSingInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSingInActionPerformed
        
    }//GEN-LAST:event_btnSingInActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JfrmLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfrmLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfrmLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfrmLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfrmLoginUser().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSingIn;
    private javax.swing.JComboBox<String> comboxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelBackgroundImage;
    private javax.swing.JPanel jpanelLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
