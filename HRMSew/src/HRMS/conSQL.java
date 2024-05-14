/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import java.sql.*;

/**
 *
 * @author Jhade
 */
public class conSQL {

    private static String hostname = "localhost";
    private static String sqlInstanceName = "DESKTOP-DUP7HF2\\JAVACONNECT"; //computer name 
    private static String sqlDatabase = "HRSDB";  //sql server database name
    private static String sqlUser = "sa";
    private static String sqlPassword = "javaconnect"; //password

    public static String connect() {
        return "jdbc:sqlserver://" + hostname + ":1433"
                + ";instance=" + sqlInstanceName + ";databaseName=" + sqlDatabase
                + ";encrypt=true;trustServerCertificate=true";
    }
    
    public static String user() {
        return sqlUser;
    }
    
    public static String password() {
        return sqlPassword;
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(connect(), user(), password());
            System.out.println("Connect to database successful!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
