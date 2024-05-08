package HRMS;

import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class Guest {

    static String getGuestString() throws SQLException {
        int id = 0;
        Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
        String sql = "Select MAX(GuestID) AS [GuestID] from Guest";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            id = rs.getInt("GuestID") + 1;
        }
        return Integer.toString(id);
    }

    static String getGuestNum(int guestID) {
        try {
            String id="";
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String sql = "Select PhoneNo from Guest where "+guestID+" = GuestID";
            Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            id = rs.getString("PhoneNo");
        }
        return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
     static String getGuestEmail(int guestID) {
        try {
            String id="";
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String sql = "Select Email from Guest where "+guestID+" = GuestID";
            Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            id = rs.getString("Email");
        }
        return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
     
    static String getGuestName(int guestID) {
        try {
            String id="";
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String sql = "Select concat(FirstName,' ', LastName) as[Name] from Guest where "+guestID+" = GuestID";
            Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            id = rs.getString("Name");
        }
        return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static boolean AddGuest(String first, String middle, String last, String mobile, String email, String home, Date sqlDate, String gender, int status) throws SQLException {

        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());

            String sql = "INSERT INTO Guest ( FirstName, MiddleName, LastName, PhoneNo, Email, HomeAddress, BirthDate, Gender, MemberStatus) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,? )";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, first);
            pstmt.setString(2, middle);
            pstmt.setString(3, last);
            pstmt.setString(4, mobile);
            pstmt.setString(5, email);
            pstmt.setString(6, home);
            pstmt.setDate(7, sqlDate);
            pstmt.setString(8, gender);
            pstmt.setInt(9, status);
            pstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean EditGuest() {
        try {
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
