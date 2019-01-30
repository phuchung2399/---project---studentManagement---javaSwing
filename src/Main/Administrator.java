/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ly.doan
 */
public class Administrator extends javax.swing.JFrame {

    private DefaultTableModel modelTC, modelST;
    private Connection connect;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private ResultSetMetaData resultSetMetaData;
    String userName;

    /**
     * Creates new form Administrator
     */
    public Administrator(String username) {
        initComponents();
        icon();
        setResizable(false);
        connect = ConnectDatabase.getConnection();
        modelTC = (DefaultTableModel) tableTC.getModel();
        modelST = (DefaultTableModel) tbStudent.getModel();
        userName = username;
        btnAccount.setText(userName);
        setSize(1000, 690);
        setLocationRelativeTo(null);
        setTitle("Administrator");
        showAvartar();
        setCbBirthday(cbYearSt);
        setCbBirthday(cbYearTC);
        jPanel12.setVisible(false);
    }

    public void icon() {
        //   setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("school.png")));
    }

    private void setCbBirthday(JComboBox cbYear) {
        for (int i = 0; i < 100; i++) {
            cbYear.addItem(2017 - i);
        }
    }

    private void cbMonthEvent(JComboBox cbYear, JComboBox cbMonth, JComboBox cbDay) {
        if (cbMonth.getSelectedIndex() != -1) {
            cbDay.removeAllItems();
            int month = Integer.parseInt(String.valueOf(cbMonth.getSelectedItem()));
            int day;
            cbDay.addItem("Day");
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    day = 31;
                    while (day > 0) {
                        cbDay.addItem(day);
                        day--;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    day = 30;
                    while (day > 0) {
                        cbDay.addItem(day);
                        day--;
                    }
                    break;
                case 2:
                    if (cbYear.getSelectedIndex() != -1 && Integer.parseInt(cbYear.getSelectedItem().toString()) % 4 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    while (day > 0) {
                        cbDay.addItem(day);
                        day--;
                    }
                    break;
            }
        }
    }

    public void showAvartar() {
        try {
            preparedStatement = connect.prepareStatement("SELECT avatar FROM user WHERE userName=? AND avatar != ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, "");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                avatar(resultSet.getString(1));
            } else {
                avatar("src/Main/HinhAnh/Icon/icons8-administrator-male-100 (1).png");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void avatar(String URL) {
        try {
            BufferedImage image = ImageIO.read(new File(URL));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(140, 110, image.SCALE_SMOOTH));
            lblAccount.setIcon(icon);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AnhHoSo(String anh, String type) {
        try {
            BufferedImage image = ImageIO.read(new File(anh));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 110, image.SCALE_SMOOTH));
            if (type.equals("ST")) {
                lbPictureST.setIcon(icon);
            } else if (type.equals("TC")) {
                lblAnhHoSoTC.setIcon(icon);
            } else if (type.equals("GVCN")) {
                lblAnhGVCN.setIcon(icon);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException e) {
            lblAnhHoSoST.setIcon(null);
            lblAnhHoSoTC.setIcon(null);
            lblAnhGVCN.setIcon(null);
        }
    }

    public void thongTinLop(String lop) {
        try {
            preparedStatement = connect.prepareStatement("SELECT * FROM Lop WHERE Class = ?");
            preparedStatement.setString(1, lop);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tTenLop.setText(resultSet.getString(1));
                tSL.setText(String.valueOf(resultSet.getInt(2)));
                tGVCN.setText(resultSet.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void thongTinGVCN() {
        try {
            preparedStatement = connect.prepareStatement("SELECT Person.name,teacher.mon, teacher.khoi,person.ngaySinh,person.gioiTinh,person.address,person.email,person.phone,person.image FROM person,teacher WHERE ID_teacher=ID AND ID=?");
            preparedStatement.setString(1, tGVCN.getText());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tTen.setText(resultSet.getString(1));
                tMon.setText(resultSet.getString(2));
                tKhoi.setText(resultSet.getString(3));
                tNgaySinh.setText(resultSet.getString(4));
                tGioiTinh.setText(resultSet.getString(5));
                tDiaChi.setText(resultSet.getString(6));
                tEmail.setText(resultSet.getString(7));
                tPhone.setText(resultSet.getString(8));
                AnhHoSo(resultSet.getString(9), "GVCN");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableStudent = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAccount = new javax.swing.JButton();
        lblAccount = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblSearchingTC = new javax.swing.JLabel();
        tSearchingTC = new javax.swing.JTextField();
        cboKhoi = new javax.swing.JComboBox();
        btnAnhHoSoTC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTC = new javax.swing.JTable();
        lIDTC1 = new javax.swing.JLabel();
        lTenTC2 = new javax.swing.JLabel();
        lPhoneTC = new javax.swing.JLabel();
        lMonTC = new javax.swing.JLabel();
        lKhoiTC = new javax.swing.JLabel();
        lNgaySinhTC = new javax.swing.JLabel();
        lGioiTinhTC = new javax.swing.JLabel();
        lDiaChiTC = new javax.swing.JLabel();
        lEmailTC = new javax.swing.JLabel();
        tImageTC = new javax.swing.JTextField();
        tIDTC = new javax.swing.JTextField();
        tTenTC = new javax.swing.JTextField();
        tDiaChiTC = new javax.swing.JTextField();
        tEmailTC = new javax.swing.JTextField();
        btnAddTC = new javax.swing.JButton();
        btnDeleteTC = new javax.swing.JButton();
        btnUpdateTC = new javax.swing.JButton();
        lblKhoi = new javax.swing.JLabel();
        cboMon = new javax.swing.JComboBox();
        cboKhoiTC = new javax.swing.JComboBox();
        cboGioiTinhTC = new javax.swing.JComboBox();
        btnSearchingTC = new javax.swing.JButton();
        btnLogOutTC = new javax.swing.JButton();
        btnExportTC = new javax.swing.JButton();
        lPhoneTC1 = new javax.swing.JLabel();
        tPhoneTC = new javax.swing.JTextField();
        btnThayAnhHoSoTC = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblAnhHoSoTC = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        cbYearTC = new javax.swing.JComboBox();
        cbMonthTC = new javax.swing.JComboBox();
        cbDayTC = new javax.swing.JComboBox();
        lblBacfgroundTC = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tSearchingST = new javax.swing.JTextField();
        btnSearchingST = new javax.swing.JButton();
        cboLopST = new javax.swing.JComboBox();
        btnAddST = new javax.swing.JButton();
        btnOKST = new javax.swing.JButton();
        btnUpdateST = new javax.swing.JButton();
        btnDeleteST = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tSearchingST1 = new javax.swing.JTextField();
        btnSearchingST1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableStudent1 = new javax.swing.JTable();
        btnAddST1 = new javax.swing.JButton();
        btnOKST1 = new javax.swing.JButton();
        btnUpdateST1 = new javax.swing.JButton();
        btnDeleteST1 = new javax.swing.JButton();
        lblIDST5 = new javax.swing.JLabel();
        lblIDST6 = new javax.swing.JLabel();
        lblIDST7 = new javax.swing.JLabel();
        lblIDST8 = new javax.swing.JLabel();
        lblIDST9 = new javax.swing.JLabel();
        lblIDST = new javax.swing.JLabel();
        lblDiaChiST = new javax.swing.JLabel();
        lblGioiTinhST = new javax.swing.JLabel();
        lblTenST = new javax.swing.JLabel();
        lblNgaySinhST = new javax.swing.JLabel();
        lblLopST = new javax.swing.JLabel();
        tPhoneST = new javax.swing.JTextField();
        tIDST = new javax.swing.JTextField();
        tTenST = new javax.swing.JTextField();
        tDiaChiST = new javax.swing.JTextField();
        tEmailST = new javax.swing.JTextField();
        lblDiaChiST1 = new javax.swing.JLabel();
        lblDiaChiST2 = new javax.swing.JLabel();
        cboLop = new javax.swing.JComboBox();
        cboGioiTinhST = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbStudent = new javax.swing.JTable();
        btnLogOutST = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        btnBrowserST = new javax.swing.JButton();
        tImageST = new javax.swing.JTextField();
        pnImage = new javax.swing.JPanel();
        lbPictureST = new javax.swing.JLabel();
        btnThayAnhST = new javax.swing.JButton();
        cbYearSt = new javax.swing.JComboBox();
        cbMonthSt = new javax.swing.JComboBox();
        cbDaySt = new javax.swing.JComboBox();
        lblAnhHoSoST = new javax.swing.JLabel();
        lblBackgroundST = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnLop3 = new javax.swing.JButton();
        btnLop5 = new javax.swing.JButton();
        btnLop9 = new javax.swing.JButton();
        btnLop10 = new javax.swing.JButton();
        btnLop11 = new javax.swing.JButton();
        btnLop12 = new javax.swing.JButton();
        pnClass = new javax.swing.JPanel();
        btnLop16 = new javax.swing.JButton();
        btnLop17 = new javax.swing.JButton();
        btnLop18 = new javax.swing.JButton();
        btnLop15 = new javax.swing.JButton();
        btnLop14 = new javax.swing.JButton();
        btnLop13 = new javax.swing.JButton();
        btnLop6 = new javax.swing.JButton();
        btn6A = new javax.swing.JButton();
        btn6B = new javax.swing.JButton();
        btnLop7 = new javax.swing.JButton();
        btnLop8 = new javax.swing.JButton();
        btnLop4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lblTenLop = new javax.swing.JLabel();
        tTenLop = new javax.swing.JTextField();
        lblTenLop1 = new javax.swing.JLabel();
        tGVCN = new javax.swing.JTextField();
        lblTenLop2 = new javax.swing.JLabel();
        tSL = new javax.swing.JTextField();
        btnTTGVCN = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        tTen = new javax.swing.JTextField();
        lblMon = new javax.swing.JLabel();
        tMon = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tKhoi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tNgaySinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tGioiTinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tDiaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tPhone = new javax.swing.JTextField();
        lblAnhGVCN = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();

        tableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Lớp", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Email", "Phone"
            }
        ));
        jScrollPane2.setViewportView(tableStudent);

        getContentPane().setLayout(null);

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 51, 51));
        lblTitle.setText("QUẢN LÍ THÔNG TIN HỌC SINH VÀ GIÁO VIÊN");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(240, 20, 550, 30);

        jPanel1.setLayout(null);

        btnAccount.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-screenshot-26.png"))); // NOI18N
        btnAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountActionPerformed(evt);
            }
        });
        jPanel1.add(btnAccount);
        btnAccount.setBounds(0, 110, 140, 40);

        lblAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lblAccount);
        lblAccount.setBounds(0, 0, 140, 110);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 20, 140, 150);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255), 3));
        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel2.setLayout(null);

        lblSearchingTC.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblSearchingTC.setForeground(new java.awt.Color(255, 0, 51));
        lblSearchingTC.setText("SEARCHING: ");
        jPanel2.add(lblSearchingTC);
        lblSearchingTC.setBounds(70, 20, 110, 30);
        jPanel2.add(tSearchingTC);
        tSearchingTC.setBounds(190, 20, 280, 30);

        cboKhoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboKhoi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9" }));
        jPanel2.add(cboKhoi);
        cboKhoi.setBounds(620, 20, 40, 30);

        btnAnhHoSoTC.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnAnhHoSoTC.setText("...");
        btnAnhHoSoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhHoSoTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnhHoSoTC);
        btnAnhHoSoTC.setBounds(720, 420, 30, 30);

        tableTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Môn", "Khối", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Email", "Phone"
            }
        ));
        tableTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTC);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 210, 460, 250);

        lIDTC1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lIDTC1.setText("ID:");
        jPanel2.add(lIDTC1);
        lIDTC1.setBounds(530, 70, 80, 14);

        lTenTC2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lTenTC2.setText("Tên:");
        jPanel2.add(lTenTC2);
        lTenTC2.setBounds(520, 110, 100, 14);

        lPhoneTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lPhoneTC.setText("Phone:");
        jPanel2.add(lPhoneTC);
        lPhoneTC.setBounds(510, 390, 100, 14);

        lMonTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lMonTC.setText("Môn:");
        jPanel2.add(lMonTC);
        lMonTC.setBounds(520, 150, 80, 14);

        lKhoiTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lKhoiTC.setText("Khối:");
        jPanel2.add(lKhoiTC);
        lKhoiTC.setBounds(520, 190, 80, 14);

        lNgaySinhTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lNgaySinhTC.setText("Ngày Sinh:");
        jPanel2.add(lNgaySinhTC);
        lNgaySinhTC.setBounds(490, 230, 100, 14);

        lGioiTinhTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lGioiTinhTC.setText("Giới Tính:");
        jPanel2.add(lGioiTinhTC);
        lGioiTinhTC.setBounds(490, 270, 120, 14);

        lDiaChiTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lDiaChiTC.setText("Địa Chỉ:");
        jPanel2.add(lDiaChiTC);
        lDiaChiTC.setBounds(500, 310, 120, 14);

        lEmailTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lEmailTC.setText("Email:");
        jPanel2.add(lEmailTC);
        lEmailTC.setBounds(510, 350, 100, 14);
        jPanel2.add(tImageTC);
        tImageTC.setBounds(560, 420, 150, 30);
        jPanel2.add(tIDTC);
        tIDTC.setBounds(560, 60, 190, 30);
        jPanel2.add(tTenTC);
        tTenTC.setBounds(560, 100, 190, 30);
        jPanel2.add(tDiaChiTC);
        tDiaChiTC.setBounds(560, 300, 190, 30);
        jPanel2.add(tEmailTC);
        tEmailTC.setBounds(560, 340, 190, 30);

        btnAddTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAddTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-add-24.png"))); // NOI18N
        btnAddTC.setText("ADD");
        btnAddTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddTC);
        btnAddTC.setBounds(230, 490, 90, 30);

        btnDeleteTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDeleteTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-trash-24.png"))); // NOI18N
        btnDeleteTC.setText("DELETE");
        btnDeleteTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnDeleteTC);
        btnDeleteTC.setBounds(470, 490, 110, 30);

        btnUpdateTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnUpdateTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-downloading-updates-24.png"))); // NOI18N
        btnUpdateTC.setText("UPDATE");
        btnUpdateTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdateTC);
        btnUpdateTC.setBounds(340, 490, 110, 30);

        lblKhoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblKhoi.setText("KHỐI:");
        jPanel2.add(lblKhoi);
        lblKhoi.setBounds(560, 20, 50, 30);

        cboMon.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cboMon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Toan", "Li", "Anh", "Hoa", "Sinh", "Van", "Su", "Dia", "CN", "GDCD" }));
        cboMon.setSelectedIndex(-1);
        jPanel2.add(cboMon);
        cboMon.setBounds(560, 140, 70, 30);

        cboKhoiTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cboKhoiTC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6", "7", "8", "9" }));
        cboKhoiTC.setSelectedIndex(-1);
        jPanel2.add(cboKhoiTC);
        cboKhoiTC.setBounds(560, 180, 70, 30);

        cboGioiTinhTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cboGioiTinhTC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nu", "Khac" }));
        cboGioiTinhTC.setSelectedIndex(-1);
        jPanel2.add(cboGioiTinhTC);
        cboGioiTinhTC.setBounds(560, 260, 70, 30);

        btnSearchingTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-search-24.png"))); // NOI18N
        btnSearchingTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchingTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearchingTC);
        btnSearchingTC.setBounds(480, 20, 50, 30);

        btnLogOutTC.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLogOutTC.setForeground(new java.awt.Color(255, 51, 51));
        btnLogOutTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-export-16.png"))); // NOI18N
        btnLogOutTC.setText("LOG OUT");
        btnLogOutTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogOutTC);
        btnLogOutTC.setBounds(640, 480, 130, 40);

        btnExportTC.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnExportTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-microsoft-excel-24.png"))); // NOI18N
        btnExportTC.setText("ExportExcel");
        jPanel2.add(btnExportTC);
        btnExportTC.setBounds(90, 490, 130, 30);

        lPhoneTC1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lPhoneTC1.setText("Image:");
        jPanel2.add(lPhoneTC1);
        lPhoneTC1.setBounds(510, 430, 100, 14);
        jPanel2.add(tPhoneTC);
        tPhoneTC.setBounds(560, 380, 190, 30);

        btnThayAnhHoSoTC.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThayAnhHoSoTC.setText("...");
        btnThayAnhHoSoTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayAnhHoSoTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnThayAnhHoSoTC);
        btnThayAnhHoSoTC.setBounds(450, 180, 30, 25);

        jPanel6.setLayout(null);
        jPanel6.add(lblAnhHoSoTC);
        lblAnhHoSoTC.setBounds(0, 0, 100, 110);

        jPanel2.add(jPanel6);
        jPanel6.setBounds(380, 70, 100, 110);

        btnOK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        jPanel2.add(btnOK);
        btnOK.setBounds(680, 20, 60, 30);

        cbYearTC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year" }));
        jPanel2.add(cbYearTC);
        cbYearTC.setBounds(560, 220, 70, 30);

        cbMonthTC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "12", "11", "10", "09", "08", "07", "06", "05", "04", "03", "02", "01" }));
        cbMonthTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMonthTCActionPerformed(evt);
            }
        });
        jPanel2.add(cbMonthTC);
        cbMonthTC.setBounds(630, 220, 70, 30);

        cbDayTC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day" }));
        jPanel2.add(cbDayTC);
        cbDayTC.setBounds(700, 220, 60, 30);

        lblBacfgroundTC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/AnhNen/nen2.jpg"))); // NOI18N
        jPanel2.add(lblBacfgroundTC);
        lblBacfgroundTC.setBounds(0, 0, 780, 540);

        jTabbedPane1.addTab("Manage Teacher", jPanel2);

        jPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("SEARCHING:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(70, 30, 110, 20);

        tSearchingST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSearchingSTActionPerformed(evt);
            }
        });
        jPanel3.add(tSearchingST);
        tSearchingST.setBounds(190, 20, 310, 30);

        btnSearchingST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-search-24.png"))); // NOI18N
        btnSearchingST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchingSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnSearchingST);
        btnSearchingST.setBounds(510, 20, 50, 30);

        cboLopST.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6A", "6B", "6C", "7A", "7B", "7C", "8A", "8B", "8C", "9A", "9B", "9C" }));
        cboLopST.setSelectedIndex(-1);
        jPanel3.add(cboLopST);
        cboLopST.setBounds(570, 190, 60, 30);

        btnAddST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAddST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-add-24.png"))); // NOI18N
        btnAddST.setText("ADD");
        btnAddST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddST);
        btnAddST.setBounds(120, 480, 100, 30);

        btnOKST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnOKST.setText("OK");
        btnOKST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnOKST);
        btnOKST.setBounds(640, 20, 60, 30);

        btnUpdateST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnUpdateST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-downloading-updates-24.png"))); // NOI18N
        btnUpdateST.setText("UPDATE");
        btnUpdateST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnUpdateST);
        btnUpdateST.setBounds(230, 480, 110, 30);

        btnDeleteST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDeleteST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-trash-24.png"))); // NOI18N
        btnDeleteST.setText("DELETE");
        btnDeleteST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnDeleteST);
        btnDeleteST.setBounds(350, 480, 110, 30);

        jPanel5.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("SEARCHING:");
        jPanel5.add(jLabel3);
        jLabel3.setBounds(70, 30, 110, 20);

        tSearchingST1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSearchingST1ActionPerformed(evt);
            }
        });
        jPanel5.add(tSearchingST1);
        tSearchingST1.setBounds(190, 20, 310, 30);

        btnSearchingST1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ly.doan.PN\\Downloads\\icons8-search-24.png")); // NOI18N
        jPanel5.add(btnSearchingST1);
        btnSearchingST1.setBounds(510, 20, 50, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6A", "6B", "6C", "7A", "7B", "7C", "8A", "8B", "8C", "9A", "9B", "9C" }));
        jComboBox2.setSelectedIndex(-1);
        jPanel5.add(jComboBox2);
        jComboBox2.setBounds(580, 20, 38, 30);

        tableStudent1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Lớp", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Email", "Phone"
            }
        ));
        jScrollPane3.setViewportView(tableStudent1);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(10, 90, 530, 260);

        btnAddST1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAddST1.setText("ADD");
        jPanel5.add(btnAddST1);
        btnAddST1.setBounds(130, 400, 60, 30);

        btnOKST1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnOKST1.setText("OK");
        jPanel5.add(btnOKST1);
        btnOKST1.setBounds(640, 20, 60, 30);

        btnUpdateST1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnUpdateST1.setText("UPDATE");
        jPanel5.add(btnUpdateST1);
        btnUpdateST1.setBounds(240, 400, 90, 30);

        btnDeleteST1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnDeleteST1.setText("DELETE");
        jPanel5.add(btnDeleteST1);
        btnDeleteST1.setBounds(380, 400, 90, 30);

        lblIDST5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblIDST5.setText("ID:");
        jPanel5.add(lblIDST5);
        lblIDST5.setBounds(560, 90, 17, 20);

        lblIDST6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblIDST6.setText("ID:");
        jPanel5.add(lblIDST6);
        lblIDST6.setBounds(560, 150, 17, 20);

        lblIDST7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblIDST7.setText("ID:");
        jPanel5.add(lblIDST7);
        lblIDST7.setBounds(560, 70, 17, 20);

        lblIDST8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblIDST8.setText("ID:");
        jPanel5.add(lblIDST8);
        lblIDST8.setBounds(560, 180, 17, 20);

        lblIDST9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblIDST9.setText("ID:");
        jPanel5.add(lblIDST9);
        lblIDST9.setBounds(560, 110, 17, 20);

        jPanel3.add(jPanel5);
        jPanel5.setBounds(0, 0, 0, 0);

        lblIDST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblIDST.setText("ID:");
        jPanel3.add(lblIDST);
        lblIDST.setBounds(540, 120, 30, 20);

        lblDiaChiST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblDiaChiST.setText("Phone:");
        jPanel3.add(lblDiaChiST);
        lblDiaChiST.setBounds(510, 400, 50, 20);

        lblGioiTinhST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblGioiTinhST.setText("Giới Tính:");
        jPanel3.add(lblGioiTinhST);
        lblGioiTinhST.setBounds(500, 280, 70, 20);

        lblTenST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTenST.setText("Tên: ");
        jPanel3.add(lblTenST);
        lblTenST.setBounds(530, 160, 30, 20);

        lblNgaySinhST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblNgaySinhST.setText("Ngày Sinh:");
        jPanel3.add(lblNgaySinhST);
        lblNgaySinhST.setBounds(500, 240, 70, 20);

        lblLopST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblLopST.setText("Lớp: ");
        jPanel3.add(lblLopST);
        lblLopST.setBounds(530, 200, 30, 20);
        jPanel3.add(tPhoneST);
        tPhoneST.setBounds(570, 390, 190, 30);
        jPanel3.add(tIDST);
        tIDST.setBounds(570, 110, 190, 30);
        jPanel3.add(tTenST);
        tTenST.setBounds(570, 150, 190, 30);
        jPanel3.add(tDiaChiST);
        tDiaChiST.setBounds(570, 310, 190, 30);
        jPanel3.add(tEmailST);
        tEmailST.setBounds(570, 350, 190, 30);

        lblDiaChiST1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblDiaChiST1.setText("Địa Chỉ: ");
        jPanel3.add(lblDiaChiST1);
        lblDiaChiST1.setBounds(510, 320, 50, 20);

        lblDiaChiST2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblDiaChiST2.setText("Email:");
        jPanel3.add(lblDiaChiST2);
        lblDiaChiST2.setBounds(510, 360, 50, 20);

        cboLop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6A", "6B", "6C", "7A", "7B", "7C", "8A", "8B", "8C", "9A", "9B", "9C" }));
        cboLop.setSelectedIndex(-1);
        jPanel3.add(cboLop);
        cboLop.setBounds(580, 20, 50, 30);

        cboGioiTinhST.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cboGioiTinhST.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nam", "Nu", "Khac" }));
        cboGioiTinhST.setSelectedIndex(-1);
        jPanel3.add(cboGioiTinhST);
        cboGioiTinhST.setBounds(570, 270, 60, 30);

        tbStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Lớp", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Email", "Phone"
            }
        ));
        tbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStudentMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbStudent);

        jPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(10, 200, 490, 260);

        btnLogOutST.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLogOutST.setForeground(new java.awt.Color(255, 51, 51));
        btnLogOutST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-export-16.png"))); // NOI18N
        btnLogOutST.setText("LOG OUT");
        btnLogOutST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnLogOutST);
        btnLogOutST.setBounds(630, 480, 130, 40);

        lblImage.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblImage.setText("Image:");
        jPanel3.add(lblImage);
        lblImage.setBounds(510, 440, 50, 20);

        btnBrowserST.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBrowserST.setText("Browser");
        btnBrowserST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowserSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnBrowserST);
        btnBrowserST.setBounds(730, 430, 30, 30);

        tImageST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tImageSTActionPerformed(evt);
            }
        });
        jPanel3.add(tImageST);
        tImageST.setBounds(570, 430, 150, 30);

        pnImage.setLayout(null);
        pnImage.add(lbPictureST);
        lbPictureST.setBounds(0, 0, 100, 110);

        jPanel3.add(pnImage);
        pnImage.setBounds(400, 60, 100, 110);

        btnThayAnhST.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThayAnhST.setText("...");
        btnThayAnhST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayAnhSTActionPerformed(evt);
            }
        });
        jPanel3.add(btnThayAnhST);
        btnThayAnhST.setBounds(470, 170, 30, 23);

        cbYearSt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year" }));
        jPanel3.add(cbYearSt);
        cbYearSt.setBounds(570, 230, 70, 30);

        cbMonthSt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Month", "12", "11", "10", "09", "08", "07", "06", "05", "04", "03", "02", "01" }));
        cbMonthSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMonthStActionPerformed(evt);
            }
        });
        jPanel3.add(cbMonthSt);
        cbMonthSt.setBounds(640, 230, 70, 30);

        cbDaySt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day" }));
        jPanel3.add(cbDaySt);
        cbDaySt.setBounds(710, 230, 60, 30);
        jPanel3.add(lblAnhHoSoST);
        lblAnhHoSoST.setBounds(400, 60, 100, 110);

        lblBackgroundST.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/AnhNen/nen2.jpg"))); // NOI18N
        jPanel3.add(lblBackgroundST);
        lblBackgroundST.setBounds(0, 0, 780, 540);

        jTabbedPane1.addTab("Manage Student", jPanel3);

        jPanel8.setLayout(null);

        jPanel9.setLayout(null);

        btnLop3.setText("jButton1");
        jPanel9.add(btnLop3);
        btnLop3.setBounds(120, 50, 73, 23);

        btnLop5.setText("jButton1");
        jPanel9.add(btnLop5);
        btnLop5.setBounds(30, 50, 73, 23);

        btnLop9.setText("jButton1");
        jPanel9.add(btnLop9);
        btnLop9.setBounds(220, 50, 73, 23);

        btnLop10.setText("jButton1");
        jPanel9.add(btnLop10);
        btnLop10.setBounds(220, 120, 73, 23);

        btnLop11.setText("jButton1");
        jPanel9.add(btnLop11);
        btnLop11.setBounds(120, 120, 73, 23);

        btnLop12.setText("jButton1");
        jPanel9.add(btnLop12);
        btnLop12.setBounds(30, 120, 73, 23);

        jPanel8.add(jPanel9);
        jPanel9.setBounds(0, 0, 0, 0);

        pnClass.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Class", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        pnClass.setLayout(null);

        btnLop16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop16.setText("9A");
        btnLop16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop16ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop16);
        btnLop16.setBounds(40, 210, 50, 30);

        btnLop17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop17.setText("9B");
        btnLop17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop17ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop17);
        btnLop17.setBounds(140, 210, 50, 30);

        btnLop18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop18.setText("9C");
        btnLop18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop18ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop18);
        btnLop18.setBounds(240, 210, 50, 30);

        btnLop15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop15.setText("8C");
        btnLop15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop15ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop15);
        btnLop15.setBounds(240, 150, 50, 30);

        btnLop14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop14.setText("8B");
        btnLop14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop14ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop14);
        btnLop14.setBounds(140, 150, 50, 30);

        btnLop13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop13.setText("8A");
        btnLop13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop13ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop13);
        btnLop13.setBounds(40, 150, 50, 30);

        btnLop6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop6.setText("7A");
        btnLop6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop6ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop6);
        btnLop6.setBounds(40, 90, 50, 30);

        btn6A.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn6A.setText("6A");
        btn6A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6AActionPerformed(evt);
            }
        });
        pnClass.add(btn6A);
        btn6A.setBounds(40, 40, 50, 30);

        btn6B.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn6B.setText("6B");
        btn6B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6BActionPerformed(evt);
            }
        });
        pnClass.add(btn6B);
        btn6B.setBounds(140, 40, 50, 30);

        btnLop7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop7.setText("7B");
        btnLop7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop7ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop7);
        btnLop7.setBounds(140, 90, 50, 30);

        btnLop8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop8.setText("7C");
        btnLop8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop8ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop8);
        btnLop8.setBounds(240, 90, 50, 30);

        btnLop4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLop4.setText("6C");
        btnLop4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLop4ActionPerformed(evt);
            }
        });
        pnClass.add(btnLop4);
        btnLop4.setBounds(240, 40, 50, 30);

        jPanel8.add(pnClass);
        pnClass.setBounds(20, 20, 330, 290);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thông Tin Lớp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel10.setLayout(null);

        lblTenLop.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTenLop.setText("Tên Lớp:");
        jPanel10.add(lblTenLop);
        lblTenLop.setBounds(50, 30, 48, 30);

        tTenLop.setEditable(false);
        tTenLop.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel10.add(tTenLop);
        tTenLop.setBounds(110, 30, 110, 30);

        lblTenLop1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTenLop1.setText("SL Học Sinh:");
        jPanel10.add(lblTenLop1);
        lblTenLop1.setBounds(30, 80, 80, 30);

        tGVCN.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tGVCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGVCNActionPerformed(evt);
            }
        });
        jPanel10.add(tGVCN);
        tGVCN.setBounds(110, 120, 110, 30);

        lblTenLop2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTenLop2.setText("GVCN:");
        jPanel10.add(lblTenLop2);
        lblTenLop2.setBounds(60, 120, 50, 30);

        tSL.setEditable(false);
        tSL.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel10.add(tSL);
        tSL.setBounds(110, 80, 110, 30);

        btnTTGVCN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTTGVCN.setText("..");
        btnTTGVCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTGVCNActionPerformed(evt);
            }
        });
        jPanel10.add(btnTTGVCN);
        btnTTGVCN.setBounds(190, 153, 30, 30);

        btnUpdate.setBackground(new java.awt.Color(255, 0, 0));
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel10.add(btnUpdate);
        btnUpdate.setBounds(230, 80, 90, 40);

        jPanel8.add(jPanel10);
        jPanel10.setBounds(20, 330, 330, 190);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thông Tin GVCN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel11.setLayout(null);
        jPanel11.add(jPanel12);
        jPanel12.setBounds(250, 20, 10, 10);

        lblTen.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblTen.setText("Tên:");
        jPanel11.add(lblTen);
        lblTen.setBounds(20, 70, 50, 30);

        tTen.setEditable(false);
        tTen.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tTen);
        tTen.setBounds(90, 70, 130, 30);

        lblMon.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblMon.setText("Môn:");
        jPanel11.add(lblMon);
        lblMon.setBounds(20, 120, 50, 30);

        tMon.setEditable(false);
        tMon.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tMon);
        tMon.setBounds(90, 120, 130, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setText("Khối:");
        jPanel11.add(jLabel6);
        jLabel6.setBounds(20, 180, 50, 30);

        tKhoi.setEditable(false);
        tKhoi.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tKhoi);
        tKhoi.setBounds(90, 180, 130, 30);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel7.setText("Ngày Sinh:");
        jPanel11.add(jLabel7);
        jLabel7.setBounds(20, 230, 70, 30);

        tNgaySinh.setEditable(false);
        tNgaySinh.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tNgaySinh);
        tNgaySinh.setBounds(90, 230, 130, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setText("Giới Tính:");
        jPanel11.add(jLabel8);
        jLabel8.setBounds(20, 280, 70, 30);

        tGioiTinh.setEditable(false);
        tGioiTinh.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tGioiTinh);
        tGioiTinh.setBounds(90, 280, 130, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel9.setText("Địa Chỉ:");
        jPanel11.add(jLabel9);
        jLabel9.setBounds(20, 330, 50, 30);

        tDiaChi.setEditable(false);
        tDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tDiaChi);
        tDiaChi.setBounds(90, 330, 130, 30);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setText("Email:");
        jPanel11.add(jLabel10);
        jLabel10.setBounds(20, 390, 50, 30);

        tEmail.setEditable(false);
        tEmail.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tEmail);
        tEmail.setBounds(90, 390, 130, 30);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setText("Phone:");
        jPanel11.add(jLabel11);
        jLabel11.setBounds(20, 440, 50, 30);

        tPhone.setEditable(false);
        tPhone.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jPanel11.add(tPhone);
        tPhone.setBounds(90, 440, 130, 30);

        lblAnhGVCN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.add(lblAnhGVCN);
        lblAnhGVCN.setBounds(240, 70, 100, 110);

        jPanel8.add(jPanel11);
        jPanel11.setBounds(390, 20, 370, 500);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/AnhNen/nen2.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel8.add(jLabel4);
        jLabel4.setBounds(0, 0, 780, 530);

        jTabbedPane1.addTab("Manage Class", jPanel8);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(180, 70, 790, 570);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/AnhNen/nenAdmin3.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 680);
        getContentPane().add(jPanel7);
        jPanel7.setBounds(600, -10, 10, 10);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountActionPerformed
        String anh = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\temp"));
        chooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
        chooser.setFileFilter(new FileNameExtensionFilter("JPG images", "jpg"));
        int returnValue = chooser.showOpenDialog(chooser);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            anh = String.valueOf(chooser.getSelectedFile());
        }

        try {
            preparedStatement = connect.prepareStatement("UPDATE user SET avatar = ? WHERE userName = ?");
            preparedStatement.setString(1, anh);
            preparedStatement.setString(2, userName);
            preparedStatement.execute();
            showAvartar();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnAccountActionPerformed

    public void ShowTableTeacher(String khoi) {
        modelTC.setRowCount(0);
        try {
            preparedStatement = connect.prepareStatement("SELECT person.ID,person.Name,teacher.mon,teacher.khoi,person.NgaySinh,person.gioitinh,person.Address,person.Email,person.phone FROM person,teacher WHERE person.ID=teacher.ID_Teacher AND teacher.khoi=?");
            preparedStatement.setString(1, khoi);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] arr = {
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)
                };
                modelTC.addRow(arr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void clear() {
        tDiaChiTC.setText("");
        tIDTC.setText("");
        tTenTC.setText("");
        cboMon.setSelectedIndex(-1);
        cboKhoiTC.setSelectedIndex(-1);
        tDiaChiTC.setText("");
        tEmailTC.setText("");
        cboGioiTinhTC.setSelectedIndex(-1);
        tImageTC.setText("");
        tDiaChiST.setText("");
        tIDST.setText("");
        cboLopST.setSelectedIndex(-1);
        cboGioiTinhST.setSelectedIndex(-1);
        tEmailST.setText("");
        tTenST.setText("");
        tPhoneST.setText("");
        tImageST.setText("");
        tImageTC.setText("");
    }

    public void LogOut() {
        int luaChon = JOptionPane.showConfirmDialog(null, "Do You Want To Log Out?", "Masage", JOptionPane.YES_NO_OPTION, 0);
        if (luaChon == JOptionPane.OK_OPTION) {
            login_register login_register = new login_register();
            login_register.setVisible(true);
            setVisible(false);
        }
    }

    private void btnAnhHoSoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhHoSoTCActionPerformed
        String anh = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\temp"));
        chooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
        chooser.setFileFilter(new FileNameExtensionFilter("JPG images", "jpg"));
        int returnValue = chooser.showOpenDialog(chooser);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            anh = String.valueOf(chooser.getSelectedFile());
        }
        tImageTC.setText(anh);
    }//GEN-LAST:event_btnAnhHoSoTCActionPerformed


    private void btnAddTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTCActionPerformed
        String dayOfBirth = cbYearTC.getSelectedItem() + "-" + cbMonthTC.getSelectedItem() + "-" + cbDayTC.getSelectedItem();

        try {
            preparedStatement = connect.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, tIDTC.getText());
            preparedStatement.setString(2, tTenTC.getText());
            preparedStatement.setString(3, dayOfBirth);
            preparedStatement.setString(4, String.valueOf(cboGioiTinhTC.getSelectedItem()));
            preparedStatement.setString(5, tDiaChiTC.getText());
            preparedStatement.setString(6, tEmailTC.getText());
            preparedStatement.setString(7, tPhoneTC.getText());
            preparedStatement.setString(8, tImageTC.getText());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            preparedStatement = connect.prepareStatement("INSERT INTO teacher VALUES (?,?,?)");
            preparedStatement.setString(1, tIDTC.getText());
            preparedStatement.setString(2, String.valueOf(cboMon.getSelectedItem()));
            preparedStatement.setString(3, String.valueOf(cboKhoiTC.getSelectedItem()));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ShowTableTeacher(String.valueOf(cboKhoiTC.getSelectedItem()));
        clear();
    }//GEN-LAST:event_btnAddTCActionPerformed

    private void btnUpdateTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTCActionPerformed
        if (modelTC.getRowCount() > 0) {
            String ten, mon, khoi, ngaySinh, gioiTinh, diaChi, email, phone, ID;
            for (int i = 0; i < modelTC.getRowCount(); i++) {
                ten = String.valueOf(modelTC.getValueAt(i, 1));
                mon = String.valueOf(modelTC.getValueAt(i, 2));
                khoi = String.valueOf(modelTC.getValueAt(i, 3));
                ngaySinh = String.valueOf(modelTC.getValueAt(i, 4));
                gioiTinh = String.valueOf(modelTC.getValueAt(i, 5));
                diaChi = String.valueOf(modelTC.getValueAt(i, 6));
                email = String.valueOf(modelTC.getValueAt(i, 7));
                phone = String.valueOf(modelTC.getValueAt(i, 8));
                ID = String.valueOf(modelTC.getValueAt(i, 0));

                try {
                    preparedStatement = connect.prepareStatement("UPDATE Person SET name=?,ngaySinh=?,gioiTinh=?,address=?,Email=?,phone=? WHERE ID=?");
                    preparedStatement.setString(1, ten);
                    preparedStatement.setString(2, ngaySinh);
                    preparedStatement.setString(3, gioiTinh);
                    preparedStatement.setString(4, diaChi);
                    preparedStatement.setString(5, email);
                    preparedStatement.setString(6, phone);
                    preparedStatement.setString(7, ID);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    preparedStatement = connect.prepareStatement("UPDATE Teacher SET Mon=?,Khoi=? WHERE ID_teacher =?");
                    preparedStatement.setString(1, mon);
                    preparedStatement.setString(2, khoi);
                    preparedStatement.setString(3, ID);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnUpdateTCActionPerformed

    private void btnDeleteTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTCActionPerformed
        int luaChon = JOptionPane.showConfirmDialog(null, "Do You Want To Delete?", "Masage", JOptionPane.YES_NO_OPTION, 0);
        if (luaChon == JOptionPane.OK_OPTION) {
            try {
                int row = tableTC.getSelectedRow();
                Object o = tableTC.getValueAt(row, 0);
                preparedStatement = connect.prepareStatement("DELETE FROM Teacher WHERE ID_Teacher = ?;");
                preparedStatement.setString(1, String.valueOf(o));
                preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                int row = tableTC.getSelectedRow();
                Object o = tableTC.getValueAt(row, 0);
                preparedStatement = connect.prepareStatement("DELETE FROM person WHERE ID = ?;");
                preparedStatement.setString(1, String.valueOf(o));
                preparedStatement.execute();
                modelTC.setRowCount(0);
                ShowTableTeacher(String.valueOf(cboKhoi.getSelectedItem()));
                System.out.println("Deleted");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteTCActionPerformed

    private void btnSearchingTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchingTCActionPerformed
        modelTC.setRowCount(0);
        try {
            preparedStatement = connect.prepareStatement("SELECT person.ID,person.Name,teacher.mon,teacher.khoi,person.NgaySinh,person.gioitinh,person.Address,person.Email,person.phone FROM person,teacher WHERE person.ID=teacher.ID_Teacher AND person.Name LIKE ?");
            preparedStatement.setString(1, "%" + tSearchingTC.getText() + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] arr = {
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)
                };
                modelTC.addRow(arr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnSearchingTCActionPerformed

    public void ShowTableStudent(String lop) {
        modelST.setRowCount(0);
        try {
            preparedStatement = connect.prepareStatement("SELECT person.ID,person.Name,student.class,person.NgaySinh,person.gioitinh,person.Address,person.Email,person.phone FROM person,student WHERE person.ID=student.ID_student AND student.class=?");
            preparedStatement.setString(1, lop);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] arr = {
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)
                };
                modelST.addRow(arr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void tSearchingSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSearchingSTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tSearchingSTActionPerformed

    private void tSearchingST1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSearchingST1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tSearchingST1ActionPerformed

    private void btnOKSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKSTActionPerformed
        ShowTableStudent(String.valueOf(cboLop.getSelectedItem()));
    }//GEN-LAST:event_btnOKSTActionPerformed

    private void btnUpdateSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSTActionPerformed
        if (modelST.getRowCount() > 0) {
            String ten, lop, ngaySinh, gioiTinh, diaChi, email, phone, ID;
            for (int i = 0; i < modelST.getRowCount(); i++) {
                ten = String.valueOf(modelST.getValueAt(i, 1));
                lop = String.valueOf(modelST.getValueAt(i, 2));
                ngaySinh = String.valueOf(modelST.getValueAt(i, 3));
                gioiTinh = String.valueOf(modelST.getValueAt(i, 4));
                diaChi = String.valueOf(modelST.getValueAt(i, 5));
                email = String.valueOf(modelST.getValueAt(i, 6));
                phone = String.valueOf(modelST.getValueAt(i, 7));
                ID = String.valueOf(modelST.getValueAt(i, 0));

                try {
                    preparedStatement = connect.prepareStatement("UPDATE Person SET name=?,ngaySinh=?,gioiTinh=?,address=?,Email=?,phone=? WHERE ID=?");
                    preparedStatement.setString(1, ten);
                    preparedStatement.setString(2, ngaySinh);
                    preparedStatement.setString(3, gioiTinh);
                    preparedStatement.setString(4, diaChi);
                    preparedStatement.setString(5, email);
                    preparedStatement.setString(6, phone);
                    preparedStatement.setString(7, ID);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    preparedStatement = connect.prepareStatement("UPDATE Teacher SET class=? WHERE ID_teacher =?");
                    preparedStatement.setString(1, lop);
                    preparedStatement.setString(2, ID);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnUpdateSTActionPerformed

    private void btnDeleteSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSTActionPerformed
        int luaChon = JOptionPane.showConfirmDialog(null, "Do You Want To Delete?", "Masage", JOptionPane.YES_NO_OPTION, 0);
        if (luaChon == JOptionPane.OK_OPTION) {
            int row = tbStudent.getSelectedRow();
            Object o = tbStudent.getValueAt(row, 0);
            int soLuong = 0;
            try {
                preparedStatement = connect.prepareStatement("SELECT soLuong FROM lop WHERE class=?");
                preparedStatement.setString(1, tbStudent.getValueAt(row, 2).toString());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    soLuong = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                preparedStatement = connect.prepareStatement("UPDATE lop SET soLuong =? WHERE class=?");
                preparedStatement.setInt(1, soLuong - 1);
                preparedStatement.setString(2, tbStudent.getValueAt(row, 2).toString());
                int a = preparedStatement.executeUpdate();
                if (a != 0) {
                    System.out.println("Đã giảm SL học sinh thành công");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                preparedStatement = connect.prepareStatement("UPDATE Student SET Class = ? WHERE ID_Student = ?;");
                preparedStatement.setString(1, null);
                preparedStatement.setString(2, String.valueOf(o));
                int kt = preparedStatement.executeUpdate();
                if (kt != 0) {
                    JOptionPane.showMessageDialog(null, "Đã Xóa Khỏi Danh Sách Lớp" + String.valueOf(modelST.getValueAt(tbStudent.getSelectedRow(), 2)));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                preparedStatement = connect.prepareStatement("DELETE FROM User WHERE ID=?");
                preparedStatement.setString(1, String.valueOf(o));
                int n = preparedStatement.executeUpdate();
                if (n != 0) {
                    System.out.println("Xóa tài khoản thành công");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnDeleteSTActionPerformed

    public void themVaoBangDiem(String ID_Student, String mon) {
        try {
            preparedStatement = connect.prepareStatement("INSERT INTO Diem(ID_Student,Mon) VALUES (?,?)");
            preparedStatement.setString(1, ID_Student);
            preparedStatement.setString(2, mon);
            int n = preparedStatement.executeUpdate();
            if (n != 0) {
                System.out.println("Chèn vào bảng điểm thành công");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void btnAddSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSTActionPerformed

        String dayOfBirth = cbYearSt.getSelectedItem() + "-" + cbMonthSt.getSelectedItem() + "-" + cbDaySt.getSelectedItem();
        if (tIDST.getText().equals("") || tTenST.getText().equals("") || dayOfBirth.equals("") || String.valueOf(cboGioiTinhST.getSelectedItem()).equals("") || tDiaChiST.getText().equals("") || tEmailST.getText().equals("") || tPhoneST.getText().equals("") || tImageST.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter fill Infomation,Please!");
        } else {
            try {
                preparedStatement = connect.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, tIDST.getText());
                preparedStatement.setString(2, tTenST.getText());
                preparedStatement.setString(3, dayOfBirth);
                preparedStatement.setString(4, String.valueOf(cboGioiTinhST.getSelectedItem()));
                preparedStatement.setString(5, tDiaChiST.getText());
                preparedStatement.setString(6, tEmailST.getText());
                preparedStatement.setString(7, tPhoneST.getText());
                preparedStatement.setString(8, tImageST.getText());
                preparedStatement.execute();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            try {
                preparedStatement = connect.prepareStatement("INSERT INTO Student VALUES (?,?)");
                preparedStatement.setString(1, tIDST.getText());
                preparedStatement.setString(2, String.valueOf(cboLopST.getSelectedItem()));
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            int soLuong = 0;
            try {
                preparedStatement = connect.prepareStatement("SELECT soLuong FROM lop WHERE class=?");
                preparedStatement.setString(1, cboLopST.getSelectedItem().toString());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    soLuong = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                preparedStatement = connect.prepareStatement("UPDATE lop SET soLuong =? WHERE class=?");
                preparedStatement.setInt(1, soLuong + 1);
                preparedStatement.setString(2, cboLopST.getSelectedItem().toString());
                int a = preparedStatement.executeUpdate();
                if (a != 0) {
                    System.out.println("Đã tăng SL học sinh thành công");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            String[] mons = {"Toan", "Ly", "Hoa", "Sinh", "Anh", "Van", "Su", "Dia", "GDCD", "CN"};
            for (String mon : mons) {
                themVaoBangDiem(tIDST.getText(), mon);
            }

            ShowTableStudent(String.valueOf(cboLop.getSelectedItem()));
            clear();
        }

    }//GEN-LAST:event_btnAddSTActionPerformed

    private void btnSearchingSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchingSTActionPerformed
        modelST.setRowCount(0);
        try {
            preparedStatement = connect.prepareStatement("SELECT person.ID,person.Name,student.class,person.NgaySinh,person.gioitinh,person.Address,person.Email,person.phone FROM person,student WHERE person.ID=student.ID_student AND person.name LIKE ?");
            preparedStatement.setString(1, "%" + tSearchingST.getText() + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Ly");
                Object[] arr = {
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)
                };
                modelST.addRow(arr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_btnSearchingSTActionPerformed

    private void btnLogOutTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutTCActionPerformed
        LogOut();
    }//GEN-LAST:event_btnLogOutTCActionPerformed

    private void btnLogOutSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutSTActionPerformed
        LogOut();
    }//GEN-LAST:event_btnLogOutSTActionPerformed

    private void btnBrowserSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowserSTActionPerformed
        String anh = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\temp"));
        chooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
        chooser.setFileFilter(new FileNameExtensionFilter("JPG images", "jpg"));
        int returnValue = chooser.showOpenDialog(chooser);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            anh = String.valueOf(chooser.getSelectedFile());
        }
        tImageST.setText(anh);
    }//GEN-LAST:event_btnBrowserSTActionPerformed

    private void tImageSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tImageSTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tImageSTActionPerformed

    private void tbStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentMouseClicked
        try {
            preparedStatement = connect.prepareStatement("SELECT Image FROM Person WHERE ID = ? LIMIT 1");
            preparedStatement.setString(1, String.valueOf(modelST.getValueAt(tbStudent.getSelectedRow(), 0)));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                AnhHoSo(resultSet.getString(1), "ST");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_tbStudentMouseClicked

    private void btnThayAnhSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayAnhSTActionPerformed

        String anh = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\temp"));
        chooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
        chooser.setFileFilter(new FileNameExtensionFilter("JPG images", "jpg"));
        int returnValue = chooser.showOpenDialog(chooser);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            anh = String.valueOf(chooser.getSelectedFile());
        }
        System.out.println(anh);
        try {
            preparedStatement = connect.prepareStatement("UPDATE Person SET Image=? WHERE ID=?");
            preparedStatement.setString(1, anh);
            preparedStatement.setString(2, String.valueOf(modelST.getValueAt(tbStudent.getSelectedRow(), 0)));
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                AnhHoSo(anh, "ST");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnThayAnhSTActionPerformed

    private void btnThayAnhHoSoTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayAnhHoSoTCActionPerformed
        String anh = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\temp"));
        chooser.setFileFilter(new FileNameExtensionFilter("PNG images", "png"));
        chooser.setFileFilter(new FileNameExtensionFilter("JPG images", "jpg"));
        int returnValue = chooser.showOpenDialog(chooser);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            anh = String.valueOf(chooser.getSelectedFile());
        }

        try {
            preparedStatement = connect.prepareStatement("UPDATE Person SET Image=? WHERE ID=?");
            preparedStatement.setString(1, anh);
            preparedStatement.setString(2, String.valueOf(modelTC.getValueAt(tableTC.getSelectedRow(), 0)));
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                AnhHoSo(anh, "TC");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnThayAnhHoSoTCActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        modelTC.setRowCount(0);
        ShowTableTeacher(String.valueOf(cboKhoi.getSelectedItem()));
    }//GEN-LAST:event_btnOKActionPerformed

    private void tableTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTCMouseClicked
        try {
            preparedStatement = connect.prepareStatement("SELECT Image FROM Person WHERE ID = ? LIMIT 1");
            preparedStatement.setString(1, String.valueOf(modelTC.getValueAt(tableTC.getSelectedRow(), 0)));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                AnhHoSo(resultSet.getString(1), "TC");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_tableTCMouseClicked

    private void btn6AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6AActionPerformed
        thongTinLop(btn6A.getText());
    }//GEN-LAST:event_btn6AActionPerformed

    private void btn6BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6BActionPerformed
        thongTinLop(btn6B.getText());
    }//GEN-LAST:event_btn6BActionPerformed

    private void btnLop4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop4ActionPerformed
        thongTinLop(btnLop4.getText());
    }//GEN-LAST:event_btnLop4ActionPerformed

    private void btnLop6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop6ActionPerformed
        thongTinLop(btnLop6.getText());
    }//GEN-LAST:event_btnLop6ActionPerformed

    private void btnLop7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop7ActionPerformed
        thongTinLop(btnLop7.getText());
    }//GEN-LAST:event_btnLop7ActionPerformed

    private void btnLop8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop8ActionPerformed
        thongTinLop(btnLop8.getText());
    }//GEN-LAST:event_btnLop8ActionPerformed

    private void btnLop13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop13ActionPerformed
        thongTinLop(btnLop13.getText());
    }//GEN-LAST:event_btnLop13ActionPerformed

    private void btnLop14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop14ActionPerformed
        thongTinLop(btnLop14.getText());
    }//GEN-LAST:event_btnLop14ActionPerformed

    private void btnLop15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop15ActionPerformed
        thongTinLop(btnLop15.getText());
    }//GEN-LAST:event_btnLop15ActionPerformed

    private void btnLop16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop16ActionPerformed
        thongTinLop(btnLop16.getText());
    }//GEN-LAST:event_btnLop16ActionPerformed

    private void btnLop17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop17ActionPerformed
        thongTinLop(btnLop17.getText());
    }//GEN-LAST:event_btnLop17ActionPerformed

    private void btnLop18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLop18ActionPerformed
        thongTinLop(btnLop18.getText());
    }//GEN-LAST:event_btnLop18ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int luaChon = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thay Đổi GVCN?", "Masage", JOptionPane.YES_NO_OPTION, 0);
        if (luaChon == JOptionPane.OK_OPTION) {
            try {
                preparedStatement = connect.prepareStatement("UPDATE lop SET chuNhiem = ? WHERE class = ?");
                preparedStatement.setString(1, tGVCN.getText());
                preparedStatement.setString(2, tTenLop.getText());
                int n = preparedStatement.executeUpdate();
                if (n != 0) {
                    JOptionPane.showMessageDialog(null, "Thay Đổi Thành Công!");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        btn6AActionPerformed(null);
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void cbMonthStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMonthStActionPerformed
        cbMonthEvent(cbYearSt, cbMonthSt, cbDaySt);
    }//GEN-LAST:event_cbMonthStActionPerformed

    private void cbMonthTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMonthTCActionPerformed
        cbMonthEvent(cbYearTC, cbMonthTC, cbDayTC);
    }//GEN-LAST:event_cbMonthTCActionPerformed

    private void tGVCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGVCNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGVCNActionPerformed

    private void btnTTGVCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTGVCNActionPerformed
        thongTinGVCN();
    }//GEN-LAST:event_btnTTGVCNActionPerformed

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
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrator("administrator").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn6A;
    private javax.swing.JButton btn6B;
    private javax.swing.JButton btnAccount;
    private javax.swing.JButton btnAddST;
    private javax.swing.JButton btnAddST1;
    private javax.swing.JButton btnAddTC;
    private javax.swing.JButton btnAnhHoSoTC;
    private javax.swing.JButton btnBrowserST;
    private javax.swing.JButton btnDeleteST;
    private javax.swing.JButton btnDeleteST1;
    private javax.swing.JButton btnDeleteTC;
    private javax.swing.JButton btnExportTC;
    private javax.swing.JButton btnLogOutST;
    private javax.swing.JButton btnLogOutTC;
    private javax.swing.JButton btnLop10;
    private javax.swing.JButton btnLop11;
    private javax.swing.JButton btnLop12;
    private javax.swing.JButton btnLop13;
    private javax.swing.JButton btnLop14;
    private javax.swing.JButton btnLop15;
    private javax.swing.JButton btnLop16;
    private javax.swing.JButton btnLop17;
    private javax.swing.JButton btnLop18;
    private javax.swing.JButton btnLop3;
    private javax.swing.JButton btnLop4;
    private javax.swing.JButton btnLop5;
    private javax.swing.JButton btnLop6;
    private javax.swing.JButton btnLop7;
    private javax.swing.JButton btnLop8;
    private javax.swing.JButton btnLop9;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnOKST;
    private javax.swing.JButton btnOKST1;
    private javax.swing.JButton btnSearchingST;
    private javax.swing.JButton btnSearchingST1;
    private javax.swing.JButton btnSearchingTC;
    private javax.swing.JButton btnTTGVCN;
    private javax.swing.JButton btnThayAnhHoSoTC;
    private javax.swing.JButton btnThayAnhST;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateST;
    private javax.swing.JButton btnUpdateST1;
    private javax.swing.JButton btnUpdateTC;
    private javax.swing.JComboBox cbDaySt;
    private javax.swing.JComboBox cbDayTC;
    private javax.swing.JComboBox cbMonthSt;
    private javax.swing.JComboBox cbMonthTC;
    private javax.swing.JComboBox cbYearSt;
    private javax.swing.JComboBox cbYearTC;
    private javax.swing.JComboBox cboGioiTinhST;
    private javax.swing.JComboBox cboGioiTinhTC;
    private javax.swing.JComboBox cboKhoi;
    private javax.swing.JComboBox cboKhoiTC;
    private javax.swing.JComboBox cboLop;
    private javax.swing.JComboBox cboLopST;
    private javax.swing.JComboBox cboMon;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lDiaChiTC;
    private javax.swing.JLabel lEmailTC;
    private javax.swing.JLabel lGioiTinhTC;
    private javax.swing.JLabel lIDTC1;
    private javax.swing.JLabel lKhoiTC;
    private javax.swing.JLabel lMonTC;
    private javax.swing.JLabel lNgaySinhTC;
    private javax.swing.JLabel lPhoneTC;
    private javax.swing.JLabel lPhoneTC1;
    private javax.swing.JLabel lTenTC2;
    private javax.swing.JLabel lbPictureST;
    private javax.swing.JLabel lblAccount;
    private javax.swing.JLabel lblAnhGVCN;
    private javax.swing.JLabel lblAnhHoSoST;
    private javax.swing.JLabel lblAnhHoSoTC;
    private javax.swing.JLabel lblBacfgroundTC;
    private javax.swing.JLabel lblBackgroundST;
    private javax.swing.JLabel lblDiaChiST;
    private javax.swing.JLabel lblDiaChiST1;
    private javax.swing.JLabel lblDiaChiST2;
    private javax.swing.JLabel lblGioiTinhST;
    private javax.swing.JLabel lblIDST;
    private javax.swing.JLabel lblIDST5;
    private javax.swing.JLabel lblIDST6;
    private javax.swing.JLabel lblIDST7;
    private javax.swing.JLabel lblIDST8;
    private javax.swing.JLabel lblIDST9;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblKhoi;
    private javax.swing.JLabel lblLopST;
    private javax.swing.JLabel lblMon;
    private javax.swing.JLabel lblNgaySinhST;
    private javax.swing.JLabel lblSearchingTC;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTenLop;
    private javax.swing.JLabel lblTenLop1;
    private javax.swing.JLabel lblTenLop2;
    private javax.swing.JLabel lblTenST;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnClass;
    private javax.swing.JPanel pnImage;
    private javax.swing.JTextField tDiaChi;
    private javax.swing.JTextField tDiaChiST;
    private javax.swing.JTextField tDiaChiTC;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tEmailST;
    private javax.swing.JTextField tEmailTC;
    private javax.swing.JTextField tGVCN;
    private javax.swing.JTextField tGioiTinh;
    private javax.swing.JTextField tIDST;
    private javax.swing.JTextField tIDTC;
    private javax.swing.JTextField tImageST;
    private javax.swing.JTextField tImageTC;
    private javax.swing.JTextField tKhoi;
    private javax.swing.JTextField tMon;
    private javax.swing.JTextField tNgaySinh;
    private javax.swing.JTextField tPhone;
    private javax.swing.JTextField tPhoneST;
    private javax.swing.JTextField tPhoneTC;
    private javax.swing.JTextField tSL;
    private javax.swing.JTextField tSearchingST;
    private javax.swing.JTextField tSearchingST1;
    private javax.swing.JTextField tSearchingTC;
    private javax.swing.JTextField tTen;
    private javax.swing.JTextField tTenLop;
    private javax.swing.JTextField tTenST;
    private javax.swing.JTextField tTenTC;
    private javax.swing.JTable tableStudent;
    private javax.swing.JTable tableStudent1;
    private javax.swing.JTable tableTC;
    private javax.swing.JTable tbStudent;
    // End of variables declaration//GEN-END:variables
}
