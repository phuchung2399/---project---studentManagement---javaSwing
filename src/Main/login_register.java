/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ly.doan
 */
public class login_register extends javax.swing.JFrame {

    private String status = "login";

    /**
     * Creates new form login_register
     */
    private DefaultTableModel model;
    private Connection connect;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private ResultSetMetaData resultSetMetaData;

    public login_register() {
        initComponents();
        icon();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(664, 411);
        setLocationRelativeTo(null);
        connect = ConnectDatabase.getConnection();
        showLogin();
    }
    public  void icon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        tUser = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        tPassword = new javax.swing.JPasswordField();
        lblTitle = new javax.swing.JLabel();
        lblConfirm = new javax.swing.JLabel();
        tConfirm = new javax.swing.JPasswordField();
        btnCreate = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnRegister = new javax.swing.JButton();
        btnShowLogin = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        tID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lblUsername.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 51, 51));
        lblUsername.setText("User name: ");
        getContentPane().add(lblUsername);
        lblUsername.setBounds(260, 150, 90, 20);

        tUser.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        tUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tUserActionPerformed(evt);
            }
        });
        tUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tUserKeyPressed(evt);
            }
        });
        getContentPane().add(tUser);
        tUser.setBounds(350, 140, 140, 30);

        lblPassword.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 51, 51));
        lblPassword.setText("Password: ");
        getContentPane().add(lblPassword);
        lblPassword.setBounds(270, 200, 80, 19);

        tPassword.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        tPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPasswordActionPerformed(evt);
            }
        });
        tPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(tPassword);
        tPassword.setBounds(350, 190, 140, 30);

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 0, 51));
        lblTitle.setText("LOGIN");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(370, 50, 130, 40);

        lblConfirm.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblConfirm.setForeground(new java.awt.Color(255, 0, 51));
        lblConfirm.setText("CofirmPassword: ");
        getContentPane().add(lblConfirm);
        lblConfirm.setBounds(220, 250, 120, 19);

        tConfirm.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        tConfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tConfirmKeyPressed(evt);
            }
        });
        getContentPane().add(tConfirm);
        tConfirm.setBounds(350, 240, 140, 30);

        btnCreate.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 0, 51));
        btnCreate.setText("Create Account");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        btnCreate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCreateKeyPressed(evt);
            }
        });
        getContentPane().add(btnCreate);
        btnCreate.setBounds(310, 320, 140, 30);

        btnLogin.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 0, 51));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLoginKeyPressed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(330, 280, 90, 30);

        btnRegister.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 0, 51));
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister);
        btnRegister.setBounds(490, 280, 110, 30);

        btnShowLogin.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnShowLogin.setForeground(new java.awt.Color(255, 0, 0));
        btnShowLogin.setText("Login");
        btnShowLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnShowLogin);
        btnShowLogin.setBounds(500, 320, 100, 30);

        lblID.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 0, 0));
        lblID.setText("ID: ");
        getContentPane().add(lblID);
        lblID.setBounds(300, 110, 40, 19);

        tID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tIDKeyPressed(evt);
            }
        });
        getContentPane().add(tID);
        tID.setBounds(350, 100, 140, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/userlogin.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 110, 250, 220);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 23)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 51, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CHÀO MỪNG BẠN ĐẾN VỚI PHẦN MỀM VIETSCHOOL");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 10, 630, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/AnhNen/phong-nen-5.jpg"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, -50, 670, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tUserActionPerformed

    private void tPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPasswordActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        register();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        String username = tUser.getText();
        String password = tPassword.getText();
        String re_password = tConfirm.getText();
        String ID = tID.getText();
        String role = null;
        PreparedStatement preparedStatement2;
        ResultSet resultSet2;
        if (!ID.equals("")) {
            String firstID = String.valueOf(ID.charAt(0));
            if (firstID.equals("S")) {
                role = "ST";
            } else if (firstID.equals("T")) {
                role = "TC";
            }
        }

        if (ID.equals("") || username.equals("") || password.equals("") || re_password.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Fill Information");
        } else {
            try {
                preparedStatement = connect.prepareStatement("SELECT ID FROM Person WHERE ID = ? LIMIT 1");
                preparedStatement.setString(1, ID);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.first()) {
                    JOptionPane.showMessageDialog(null, "ID doesn't exist");
                } else {
                    preparedStatement2 = connect.prepareStatement("SELECT ID FROM user WHERE ID = ? LIMIT 1");
                    preparedStatement2.setString(1, ID);
                    resultSet2 = preparedStatement2.executeQuery();
                    if (resultSet2.first()) {
                        JOptionPane.showMessageDialog(null, " You have got account");
                    } else {
                        try {
                            preparedStatement = connect.prepareStatement("SELECT userName FROM user WHERE userName = ? LIMIT 1;");
                            preparedStatement.setString(1, username);
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.first()) {
                                JOptionPane.showMessageDialog(null, "User name already exist!");
                            } else {
                                if (password.equals(re_password)) {
                                    try {
                                        preparedStatement = connect.prepareStatement("INSERT INTO user(ID,userName,password,role) VALUES ( ?, ?,?,?);");
                                        preparedStatement.setString(1, ID);
                                        preparedStatement.setString(2, username);
                                        preparedStatement.setString(3, password);
                                        preparedStatement.setString(4, role);
                                        preparedStatement.executeUpdate();
                                        JOptionPane.showMessageDialog(null, "Register Successful");
                                    } catch (SQLException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Register Unsuccessful\nRe-password didn't match with password");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(login_register.class.getName()).log(Level.SEVERE, null, ex);
            }

    }//GEN-LAST:event_btnCreateActionPerformed
    }

    private void btnShowLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowLoginActionPerformed
        showLogin();
    }//GEN-LAST:event_btnShowLoginActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = tUser.getText();
        String password = tPassword.getText();
        PreparedStatement preparedStatement2, preparedStatement3;
        ResultSet resultSet2, resultSet3;

        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Fill Information");
        } else {
            try {
                preparedStatement = connect.prepareStatement("SELECT userName FROM user WHERE username=? LIMIT 1");
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                preparedStatement2 = connect.prepareStatement("SELECT userName FROM user WHERE userName=? AND password=? LIMIT 1");
                preparedStatement2.setString(1, username);
                preparedStatement2.setString(2, password);
                resultSet2 = preparedStatement2.executeQuery();
                if (resultSet.first() && resultSet2.first()) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    if (typeOfAccout(username, "TC")) {
                        preparedStatement3 = connect.prepareStatement("SELECT ID,lop.class FROM User,Lop WHERE User.ID=Lop.ChuNhiem AND UserName=?");
                        preparedStatement3.setString(1, username);
                        resultSet3 = preparedStatement3.executeQuery();
                        if (resultSet3.next()) {
                            TrangChu trangChu = new TrangChu("TC", username, 1, resultSet3.getString(2));
                            trangChu.setVisible(true);
                        }
                        else{
                            TrangChu trangChu =new TrangChu("TC", username);
                            trangChu.setVisible(true);
                        }
                    } else if (typeOfAccout(username, "ST")) {
                        TrangChu trangChu = new TrangChu("ST", username);
                        trangChu.setVisible(true);
                    } else if (typeOfAccout(username, "AD")) {
                        TrangChu trangChu = new TrangChu("AD", username);
                        trangChu.setVisible(true);
                    }

                } else if (resultSet.first() && !resultSet2.first()) {
                    JOptionPane.showMessageDialog(null, "Wrong Password. Try again");
                } else {
                    JOptionPane.showMessageDialog(null, "Account doesn't exist. You can register");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }//GEN-LAST:event_btnLoginActionPerformed

    private void tIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tIDKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            tUser.requestFocus();
        }
    }//GEN-LAST:event_tIDKeyPressed

    private void tUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tUserKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            tPassword.requestFocus();
        }
    }//GEN-LAST:event_tUserKeyPressed

    private void tPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tPasswordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (status.equals("login")) {
                btnLoginActionPerformed(null);
            }else{
                tConfirm.requestFocus();
            }
            // btnLoginActionPerformed(null);
        }
    }//GEN-LAST:event_tPasswordKeyPressed

    private void tConfirmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tConfirmKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnCreateActionPerformed(null);
        }
    }//GEN-LAST:event_tConfirmKeyPressed

    private void btnLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyPressed

    }//GEN-LAST:event_btnLoginKeyPressed

    private void btnCreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCreateKeyPressed

    }//GEN-LAST:event_btnCreateKeyPressed
    public void showLogin() {
        status = "login";
        tUser.setText("");
        tPassword.setText("");
        lblID.setVisible(false);
        tID.setVisible(false);
        btnLogin.setVisible(true);
        btnRegister.setVisible(true);
        lblConfirm.setVisible(false);
        tConfirm.setVisible(false);
        btnCreate.setVisible(false);
        btnShowLogin.setVisible(false);
    }

    public boolean typeOfAccout(String userName, String type) {
        int n = 0;
        try {
            preparedStatement = connect.prepareStatement("SELECT role FROM user WHERE userName=? AND role =?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, type);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                n = 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (n == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void register() {
        status = "register";
        lblTitle.setText("REGISTER");
        lblID.setVisible(true);
        tID.setVisible(true);
        lblConfirm.setVisible(true);
        tConfirm.setVisible(true);
        btnLogin.setVisible(false);
        btnRegister.setVisible(false);
        btnCreate.setVisible(true);
        btnShowLogin.setVisible(true);
        clear();
    }

    public void clear() {
        tConfirm.setText("");
        tPassword.setText("");
        tUser.setText("");
    }

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
            java.util.logging.Logger.getLogger(login_register.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_register.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_register.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_register.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnShowLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblConfirm;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField tConfirm;
    private javax.swing.JTextField tID;
    private javax.swing.JPasswordField tPassword;
    private javax.swing.JTextField tUser;
    // End of variables declaration//GEN-END:variables
}