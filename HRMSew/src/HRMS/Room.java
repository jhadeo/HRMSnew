/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import java.sql.*;

public class Room {

    static boolean searchRoomID(int RoomID) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select RoomNo from Room where " + RoomID + "= RoomNo and RoomStatus = 'Available'";
            ResultSet rs = stmt.executeQuery(search);
            String test = "";
            while (rs.next()) {
                test = rs.getString("RoomNo");
                if (test.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean searchguestID(int guestID) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select GuestID from Guest where " + guestID + "= Guest.GuestID";
            ResultSet rs = stmt.executeQuery(search);
            String test = "";
            while (rs.next()) {
                test = rs.getString("GuestID");
                if (test.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static String getRoomRate(int RoomNo) {
        String rate = "";
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select RoomRate from Room where " + RoomNo + "= RoomNo";
            ResultSet rs = stmt.executeQuery(search);
            while (rs.next()) {
                rate = rs.getString("RoomRate");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }
    
    

static boolean AddReservation(int guestID, String roomNo, String checkInDate, String checkOutDate, String roomRate, String payMethod, String Taxes, String Total) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO RoomReservation (GuestID, RoomNo, CheckInDate, CheckOutDate, RoomRate, PayMethod, Taxes,Total) values(?, ?, ?, ?, ?, ?, ?,?)";
        } catch (Exception e) {
        }
        return false;
    }
}
