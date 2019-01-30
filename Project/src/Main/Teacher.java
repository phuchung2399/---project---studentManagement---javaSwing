/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import sun.security.rsa.RSACore;


public class Teacher extends javax.swing.JFrame {

    //private String userName;
    private DefaultTableModel modelTC, modelGVCN;
    private Connection connect;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private ResultSetMetaData resultSetMetaData;
    String mon, ID, khoi;
    String userName;

    private Object[] content;

    /**
     * Creates new form Teacher
     */
    public Teacher(String user) {
        initComponents();
        icon();
        setResizable(false);
        btGVCN.setVisible(false);
        userName = user;
        btnUserName.setText(userName);
       // setTitle("YOU ARE TEACHER");
        setSize(810, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        modelTC = (DefaultTableModel) table.getModel();
        modelGVCN = (DefaultTableModel) tbGVCN.getModel();
        connect = ConnectDatabase.getConnection();
        showAvartar();
        setVisible(true);
        getLop("6");
        getLop("7");
        getLop("8");
        getLop("9");
        tName.setEditable(false);
        showTitle();
        khoi = String.valueOf(String.valueOf(cboLop.getSelectedItem()).charAt(0));
        pnGVCN.setVisible(false);
    }
    public  void icon(){
     //   setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("login.png")));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("school.png")));
    }

    public void showTitle() {
        layMon();

        if (mon.equals("Toan")) {
            lblTitle.setText("NHẬP ĐIỂM TOÁN CHO HỌC SINH");
        }
        if (mon.equals("Van")) {
            lblTitle.setText("NHẬP ĐIỂM VĂN CHO HỌC SINH");
        }
        if (mon.equals("Hoa")) {
            lblTitle.setText("NHẬP ĐIỂM HÓA CHO HỌC SINH");
        }
        if (mon.equals("Ly")) {
            lblTitle.setText("NHẬP ĐIỂM LÍ CHO HỌC SINH");
        }
        if (mon.equals("Sinh")) {
            lblTitle.setText("NHẬP ĐIỂM SINH HỌC CHO HỌC SINH");
        }
        if (mon.equals("Anh")) {
            lblTitle.setText("NHẬP ĐIỂM ANH CHO HỌC SINH");
        }
        if (mon.equals("Su")) {
            lblTitle.setText("NHẬP ĐIỂM SỬ CHO HỌC SINH");
        }
        if (mon.equals("Dia")) {
            lblTitle.setText("NHẬP ĐIỂM ĐỊA CHO HỌC SINH");
        }
        if (mon.equals("GDCD")) {
            lblTitle.setText("NHẬP ĐIỂM GDCD CHO HỌC SINH");
        }
        if (mon.equals("CN")) {
            lblTitle.setText("NHẬP ĐIỂM CÔNG NGHỆ HỌC CHO HỌC SINH");
        }
    }

    public void getLop(String khoi) {
        try {
            preparedStatement = connect.prepareStatement("SELECT teacher.khoi FROM teacher,user,person WHERE person.ID =user.ID AND teacher.ID_Teacher=person.ID AND user.userName= ? AND teacher.khoi = ?;");
            preparedStatement.setString(2, khoi);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                cboLop.addItem(khoi + "A");
                cboLop.addItem(khoi + "B");
                cboLop.addItem(khoi + "C");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GVCN(String lop) {
        btGVCN.setVisible(true);
        btGVCN.setText(lop);
    }

    public void xemDiemGVCN() {
        pnLenh.setVisible(false);
        pnNhap.setVisible(false);
        pnTable.setVisible(false);
        tHK.setVisible(false);
        tKTM.setVisible(false);
        tKt15.setVisible(false);
        tKT11.setVisible(false);
        tKT12.setVisible(false);
        lblKT11.setVisible(false);
        lblKT12.setVisible(false);
        lblKTM.setVisible(false);
        lblKt15.setVisible(false);
        lblName.setVisible(false);
        tName.setVisible(false);
        lblHK.setVisible(false);
        pnGVCN.setVisible(true);
        lblSearching.setVisible(false);
        tSearching.setVisible(false);
        btnSearching.setVisible(false);
    }

    public void Home() {
        pnLenh.setVisible(true);
        pnNhap.setVisible(true);
        pnTable.setVisible(true);
        tHK.setVisible(true);
        tKTM.setVisible(true);
        tKt15.setVisible(true);
        tKT11.setVisible(true);
        tKT12.setVisible(true);
        lblKT11.setVisible(true);
        lblKT12.setVisible(true);
        lblKTM.setVisible(true);
        lblKt15.setVisible(true);
        lblName.setVisible(true);
        tName.setVisible(true);
        lblHK.setVisible(true);
        pnGVCN.setVisible(false);
        lblSearching.setVisible(true);
        tSearching.setVisible(true);
        btnSearching.setVisible(true);
    }

    public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                // ten Cot
                for (int j = 0; j < table.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");
                // Lay du lieu dong
                for (int j = 0; j < table.getRowCount(); j++) {
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        bwrite.write(model.getValueAt(j, k) + "\t");
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }

    public void layMon() {
        try {
            preparedStatement = connect.prepareStatement("SELECT teacher.mon FROM teacher, user WHERE teacher.ID_Teacher = user.ID AND username = ? LIMIT 1;");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                mon = resultSet.getString(1);
                System.out.println(mon);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clear() {
        tName.setText("");
        tKTM.setText("");
        tKt15.setText("");
        tKT11.setText("");
        tKT12.setText("");
        tHK.setText("");
    }

    public void showAvartar() {
        try {
            preparedStatement = connect.prepareStatement("SELECT avatar FROM user WHERE userName=? AND avatar!= ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, "");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                avatar(resultSet.getString(1));
            } else {
                avatar("src/Main/HinhAnh/Icon/icons8-account-80.png");
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

    public void showSearching() {

        try {
            preparedStatement = connect.prepareStatement("SELECT Diem.ID_Student,person.name,KTM,KTMuoiLam,KTMotTietMot,KTMotTietHai,HK,DiemTB FROM person,Diem,student WHERE person.ID=Diem.ID_Student AND person.ID = student.ID_Student AND Diem.Mon=? AND student.class LIKE ? AND person.name  LIKE  ?  ;");
            preparedStatement.setString(1, mon);
            preparedStatement.setString(2, "%" + khoi + "%");
            preparedStatement.setString(3, "%" + tSearching.getText() + "%");
            resultSet = preparedStatement.executeQuery();
            modelTC.setRowCount(0);
            while (resultSet.next()) {
                Object[] arr = {
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)
                };
                modelTC.addRow(arr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showTable(String lop) {

        modelTC.setRowCount(0);
        try {
            preparedStatement = connect.prepareStatement("SELECT Diem.ID_Student,person.name,KTM,KTMuoiLam,KTMotTietMot,KTMotTietHai,HK,DiemTB FROM person,Diem,student WHERE person.ID=Diem.ID_Student AND person.ID = student.ID_Student AND Diem.Mon=? AND student.class=? ;");
            preparedStatement.setString(1, mon);
            preparedStatement.setString(2, lop);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] arr = {
                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)
                };
                modelTC.addRow(arr);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void showTableGVCN() {
        Vector<String> IDs = new Vector<>();
        Vector<String> names = new Vector<>();
        try {
            modelGVCN.setRowCount(0);
            preparedStatement = connect.prepareStatement("SELECT person.ID, person.Name FROM person, student WHERE person.ID = student.ID_Student AND student.class = ?");
            preparedStatement.setString(1, btGVCN.getText());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                IDs.add(resultSet.getString(1));
                names.add(resultSet.getString(2));
            }
            for (int i = 0; i < IDs.size(); i++) {
                Object[] arr = {
                    IDs.get(i),
                    names.get(i),
                    getDiemTB(IDs.get(i), "Toan"),
                    getDiemTB(IDs.get(i), "Ly"),
                    getDiemTB(IDs.get(i), "Hoa"),
                    getDiemTB(IDs.get(i), "Sinh"),
                    getDiemTB(IDs.get(i), "Van"),
                    getDiemTB(IDs.get(i), "Su"),
                    getDiemTB(IDs.get(i), "Dia"),
                    getDiemTB(IDs.get(i), "Tieng Anh"),
                    getDiemTB(IDs.get(i), "GDCD"),
                    getDiemTB(IDs.get(i), "CN")
                };
                modelGVCN.addRow(arr);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private String getDiemTB(String ID, String mon) {
        String DiemTB = "";
        try {
            preparedStatement = connect.prepareStatement("SELECT DiemTB FROM Diem,student WHERE student.ID_Student = Diem.ID_Student AND Diem.ID_Student = ? AND Diem.Mon=?");
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, mon);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                DiemTB = resultSet.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return DiemTB;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnGVCN = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGVCN = new javax.swing.JTable();
        btnHome = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblAccount = new javax.swing.JLabel();
        btnUserName = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        pnNhap = new javax.swing.JPanel();
        cboChon = new javax.swing.JComboBox();
        cboLop = new javax.swing.JComboBox();
        btnOK = new javax.swing.JButton();
        pnLenh = new javax.swing.JPanel();
        btGVCN = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnExportTable = new javax.swing.JButton();
        lblKTM = new javax.swing.JLabel();
        tKTM = new javax.swing.JTextField();
        lblKt15 = new javax.swing.JLabel();
        tKt15 = new javax.swing.JTextField();
        tKT11 = new javax.swing.JTextField();
        tKT12 = new javax.swing.JTextField();
        tHK = new javax.swing.JTextField();
        lblKT11 = new javax.swing.JLabel();
        lblKT12 = new javax.swing.JLabel();
        lblHK = new javax.swing.JLabel();
        btnlogOut = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblSearching = new javax.swing.JLabel();
        tName = new javax.swing.JTextField();
        tSearching = new javax.swing.JTextField();
        btnSearching = new javax.swing.JButton();
        pnTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pnGVCN.setLayout(null);

        tbGVCN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Toán", "Lí", "Hóa", "Sinh", "Văn", "Sử", "Địa", "Tiếng Anh", "GDCD", "CN", "Điểm TB"
            }
        ));
        jScrollPane2.setViewportView(tbGVCN);

        pnGVCN.add(jScrollPane2);
        jScrollPane2.setBounds(10, 40, 740, 190);

        btnHome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-home-24 (1).png"))); // NOI18N
        btnHome.setText("HOME");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        pnGVCN.add(btnHome);
        btnHome.setBounds(620, 260, 110, 30);

        btnExcel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-microsoft-excel-24.png"))); // NOI18N
        btnExcel.setText("Export Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        pnGVCN.add(btnExcel);
        btnExcel.setBounds(460, 260, 137, 30);

        getContentPane().add(pnGVCN);
        pnGVCN.setBounds(30, 170, 760, 320);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        lblAccount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        jPanel1.add(lblAccount);
        lblAccount.setBounds(0, 0, 140, 110);

        btnUserName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnUserName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-screenshot-26.png"))); // NOI18N
        btnUserName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        btnUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserNameActionPerformed(evt);
            }
        });
        jPanel1.add(btnUserName);
        btnUserName.setBounds(0, 110, 140, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 10, 140, 150);

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        getContentPane().add(lblTitle);
        lblTitle.setBounds(240, 10, 470, 30);

        cboChon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhập Điểm", "Xem Điểm" }));
        cboChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChonActionPerformed(evt);
            }
        });

        cboLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLopActionPerformed(evt);
            }
        });

        btnOK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnNhapLayout = new javax.swing.GroupLayout(pnNhap);
        pnNhap.setLayout(pnNhapLayout);
        pnNhapLayout.setHorizontalGroup(
            pnNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhapLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboLop, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboChon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        pnNhapLayout.setVerticalGroup(
            pnNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhapLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(cboChon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboLop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(pnNhap);
        pnNhap.setBounds(30, 170, 110, 160);

        pnLenh.setLayout(null);

        btGVCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGVCNActionPerformed(evt);
            }
        });
        pnLenh.add(btGVCN);
        btGVCN.setBounds(10, 50, 90, 30);

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnLenh.add(btnUpdate);
        btnUpdate.setBounds(10, 10, 90, 30);

        btnExportTable.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnExportTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-microsoft-excel-24.png"))); // NOI18N
        btnExportTable.setText("Excel");
        btnExportTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportTableActionPerformed(evt);
            }
        });
        pnLenh.add(btnExportTable);
        btnExportTable.setBounds(10, 90, 90, 30);

        getContentPane().add(pnLenh);
        pnLenh.setBounds(30, 350, 110, 130);

        lblKTM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblKTM.setText("KTM");
        getContentPane().add(lblKTM);
        lblKTM.setBounds(420, 370, 50, 20);
        getContentPane().add(tKTM);
        tKTM.setBounds(420, 400, 60, 30);

        lblKt15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblKt15.setText("KT 15'");
        getContentPane().add(lblKt15);
        lblKt15.setBounds(500, 370, 60, 20);
        getContentPane().add(tKt15);
        tKt15.setBounds(500, 400, 60, 30);

        tKT11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tKT11ActionPerformed(evt);
            }
        });
        getContentPane().add(tKT11);
        tKT11.setBounds(580, 400, 60, 30);
        getContentPane().add(tKT12);
        tKT12.setBounds(660, 400, 60, 30);
        getContentPane().add(tHK);
        tHK.setBounds(730, 400, 60, 30);

        lblKT11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblKT11.setText("KT 1 Tiết");
        getContentPane().add(lblKT11);
        lblKT11.setBounds(580, 370, 60, 20);

        lblKT12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblKT12.setText("KT 1 Tiết");
        getContentPane().add(lblKT12);
        lblKT12.setBounds(660, 370, 60, 20);

        lblHK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHK.setText("HK");
        getContentPane().add(lblHK);
        lblHK.setBounds(740, 370, 34, 20);

        btnlogOut.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnlogOut.setForeground(new java.awt.Color(255, 0, 51));
        btnlogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-export-16.png"))); // NOI18N
        btnlogOut.setText("LOG OUT");
        btnlogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogOutActionPerformed(evt);
            }
        });
        getContentPane().add(btnlogOut);
        btnlogOut.setBounds(660, 500, 130, 40);

        lblName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblName.setText("Name");
        getContentPane().add(lblName);
        lblName.setBounds(180, 370, 60, 20);

        lblSearching.setBackground(new java.awt.Color(0, 0, 0));
        lblSearching.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        lblSearching.setForeground(new java.awt.Color(255, 0, 0));
        lblSearching.setText("SEARCHING: ");
        getContentPane().add(lblSearching);
        lblSearching.setBounds(180, 100, 150, 30);
        getContentPane().add(tName);
        tName.setBounds(180, 400, 190, 30);

        tSearching.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tSearchingFocusGained(evt);
            }
        });
        tSearching.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tSearchingActionPerformed(evt);
            }
        });
        getContentPane().add(tSearching);
        tSearching.setBounds(330, 100, 370, 30);

        btnSearching.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/Icon/icons8-search-24.png"))); // NOI18N
        btnSearching.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchingActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearching);
        btnSearching.setBounds(710, 100, 50, 30);

        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Student", "Name", "KTM", "KT 15'", "KT 1 Tiết", "KT 1 Tiết", "HK", "Điểm TB"
            }
        ));
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnTable);
        pnTable.setBounds(180, 180, 610, 190);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main/HinhAnh/AnhNen/nen2.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 800, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboChonActionPerformed

    private void cboLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLopActionPerformed

    }//GEN-LAST:event_cboLopActionPerformed

    private void tKT11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tKT11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tKT11ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        clear();
        showTable(String.valueOf(cboLop.getSelectedItem()));
        tinhDiemTB();
    }//GEN-LAST:event_btnOKActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        clear();
        tName.setText(String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 1)));

        if (modelTC.getValueAt(table.getSelectedRow(), 2) != null) {
            tKTM.setText(String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 2)));
        }
        if (modelTC.getValueAt(table.getSelectedRow(), 3) != null) {
            tKt15.setText(String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 3)));
        }
        if (modelTC.getValueAt(table.getSelectedRow(), 4) != null) {
            tKT11.setText(String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 4)));
        }
        if (modelTC.getValueAt(table.getSelectedRow(), 5) != null) {
            tKT12.setText(String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 5)));
        }
        if (modelTC.getValueAt(table.getSelectedRow(), 6) != null) {
            tHK.setText(String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 6)));
        }
        ID = String.valueOf(modelTC.getValueAt(table.getSelectedRow(), 0));
    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        tableMouseClicked(null);
    }//GEN-LAST:event_tableKeyPressed

    private void btnUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserNameActionPerformed
        // TODO add your handling code here:
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

    }//GEN-LAST:event_btnUserNameActionPerformed

    public void tinhDiemTB() {
        if (modelTC.getRowCount() > 0) {
            Double KTM, KT15, KT11, KT12, HK;
            for (int i = 0; i < modelTC.getRowCount(); i++) {
                ID = String.valueOf(modelTC.getValueAt(i, 0));
                String DTB = "";
                try {
                    KTM = Double.parseDouble(String.valueOf(modelTC.getValueAt(i, 2)));
                    KT15 = Double.parseDouble(String.valueOf(modelTC.getValueAt(i, 3)));
                    KT11 = Double.parseDouble(String.valueOf(modelTC.getValueAt(i, 4)));
                    KT12 = Double.parseDouble(String.valueOf(modelTC.getValueAt(i, 5)));
                    HK = Double.parseDouble(String.valueOf(modelTC.getValueAt(i, 6)));
                    DTB = String.valueOf((KTM + KT15 + KT11 * 2 + KT12 * 2 + HK * 3) / 10);
                } catch (NumberFormatException e) {
                    DTB = "";
                }
                try {
                    preparedStatement = connect.prepareStatement("UPDATE diem SET diemTB = ? WHERE ID_Student = ? AND mon = ?");
                    preparedStatement.setString(1, DTB);
                    preparedStatement.setString(2, ID);
                    preparedStatement.setString(3, mon);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            showTable(String.valueOf(cboLop.getSelectedItem()));
        }
    }

    private void btnSearchingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchingActionPerformed
        // TODO add your handling code here:
        showSearching();
    }//GEN-LAST:event_btnSearchingActionPerformed

    private void btnlogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogOutActionPerformed
        // TODO add your handling code here:
        int luaChon = JOptionPane.showConfirmDialog(null, "Do You Want To Log Out?", "Masage", JOptionPane.YES_NO_OPTION, 0);
        if (luaChon == JOptionPane.OK_OPTION) {
            login_register login_register = new login_register();
            login_register.setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btnlogOutActionPerformed

    private void btGVCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGVCNActionPerformed
        xemDiemGVCN();
        showTableGVCN();
    }//GEN-LAST:event_btGVCNActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        Home();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportExcel(tbGVCN);
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnExportTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportTableActionPerformed
        exportExcel(table);
    }//GEN-LAST:event_btnExportTableActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Pattern pattern = Pattern.compile("\\d*");
        Matcher KTM = pattern.matcher(tKTM.getText());
        Matcher KT11 = pattern.matcher(tKT11.getText());
        Matcher KT12 = pattern.matcher(tKT12.getText());
        Matcher KT15 = pattern.matcher(tKt15.getText());
        Matcher HK = pattern.matcher(tHK.getText());
        if (KTM.matches() && KT11.matches() && KT12.matches() && KT15.matches() && HK.matches() ) {
            try {
            preparedStatement = connect.prepareStatement("UPDATE Diem SET KTM = ?, KTMuoiLam = ?, KTMotTietMot = ?, KTMotTietHai = ?, HK = ? WHERE ID_Student = ? AND Diem.Mon =?");
            preparedStatement.setString(1, tKTM.getText());
            preparedStatement.setString(2, tKt15.getText());
            preparedStatement.setString(3, tKT11.getText());
            preparedStatement.setString(4, tKT12.getText());
            preparedStatement.setString(5, tHK.getText());
            preparedStatement.setString(6, ID);
            preparedStatement.setString(7, mon);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        clear();
        showTable(cboLop.getSelectedItem().toString());
        tinhDiemTB();
        }else{
            JOptionPane.showMessageDialog(null, "Bạn vừa nhập không phải số");
        }
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tSearchingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tSearchingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tSearchingActionPerformed

    private void tSearchingFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tSearchingFocusGained
         
    }//GEN-LAST:event_tSearchingFocusGained

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
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Teacher teacher = new Teacher("Thanh");
                teacher.setVisible(true);
                teacher.GVCN("6A");
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGVCN;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnExportTable;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnSearching;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUserName;
    private javax.swing.JButton btnlogOut;
    private javax.swing.JComboBox cboChon;
    private javax.swing.JComboBox cboLop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAccount;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblHK;
    private javax.swing.JLabel lblKT11;
    private javax.swing.JLabel lblKT12;
    private javax.swing.JLabel lblKTM;
    private javax.swing.JLabel lblKt15;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSearching;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnGVCN;
    private javax.swing.JPanel pnLenh;
    private javax.swing.JPanel pnNhap;
    private javax.swing.JPanel pnTable;
    private javax.swing.JTextField tHK;
    private javax.swing.JTextField tKT11;
    private javax.swing.JTextField tKT12;
    private javax.swing.JTextField tKTM;
    private javax.swing.JTextField tKt15;
    private javax.swing.JTextField tName;
    private javax.swing.JTextField tSearching;
    private javax.swing.JTable table;
    private javax.swing.JTable tbGVCN;
    // End of variables declaration//GEN-END:variables
}
