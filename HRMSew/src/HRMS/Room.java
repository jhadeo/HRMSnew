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

    static boolean AddReservation(int guestID, String roomNo, String checkInDate, String checkOutDate, String roomRate, String payMethod, String Taxes, String Total, boolean status) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();

            String insertsql = "INSERT INTO RoomReservation (GuestID, RoomNo, CheckInDate, CheckOutDate, RoomRate, PayMethod, Taxes,Total,PaymentStatus) values(?, ?, ?, ?, ?, ?, ?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setInt(1, guestID);
            pstmt.setString(2, roomNo);
            pstmt.setString(3, checkInDate);
            pstmt.setString(4, checkOutDate);
            pstmt.setString(5, roomRate);
            pstmt.setString(6, payMethod);
            pstmt.setString(7,Taxes );
            pstmt.setString(8, Total);
            pstmt.setBoolean(9, status);
            pstmt.executeUpdate();

            String sql2 = "UPDATE Room SET RoomStatus = 'Reserved' WHERE RoomNo = " + roomNo;
            stmt.executeUpdate(sql2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    static boolean CheckOut(int RoomNo) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();

            String insertSql = "UPDATE Room SET RoomStatus = 'Available' WHERE RoomNo = " + RoomNo;
            stmt.execute(insertSql);
            String Sql = "UPDATE RoomReservation SET PaymentStatus = 1, CheckOutStatus = 1 WHERE RoomNo = " + RoomNo+ " AND CheckOutStatus = 0 AND CheckInDate < GETDATE();";
            stmt.execute(Sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}
