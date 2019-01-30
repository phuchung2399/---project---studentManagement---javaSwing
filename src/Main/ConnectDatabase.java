/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ly.doan
 */
public class ConnectDatabase {
     private static Connection connect;

    static {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/QUANLIDIEMHOCSINH?user=root&password=");
            if (connect != null) {
                System.out.println("Connect Secessfully");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static Connection getConnection() {
        return connect;
    }

    public static void main(String[] args) {
        new connection.ConnectDatabase();
    }
}
