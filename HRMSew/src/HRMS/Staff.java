/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class Staff extends StaffSQL {
    @Override
    public boolean addValue(){
        return true;
    };
    
    public static String getPassword(String username){
        System.out.println(username);
        String password = "";
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select staffpassword from STAFF where '" + username + "' = username";
            ResultSet rs = stmt.executeQuery(search);
            while (rs.next()) {
                password = rs.getString("staffpassword");
                System.out.println(password);
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    };
    public static String getValue(){
    
    return "";
    };
   
    public static boolean setPassword(String password, String username) {
        try{
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();

            String insertsql = "UPDATE STAFF SET staffPassword = ? WHERE Username = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getStaff() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

abstract class StaffSQL{
    public abstract boolean addValue();
    public abstract String getStaff();
}