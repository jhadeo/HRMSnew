/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

public class Room {

    static String getMaxRoomID() {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "Select MAX(RoomNo) AS [RoomNo] from Room";
            ResultSet rs = stmt.executeQuery(search);
            String test = "";
            while (rs.next()) {
                test = rs.getString("RoomNo");
                if (test.isEmpty()) {
                    return "";
                } else {
                    return test;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

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

    static String getReservationID() {
        String rate = "";
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select MAX(ReservationID)+1 as  [ReservationID] from RoomReservation ";
            ResultSet rs = stmt.executeQuery(search);
            while (rs.next()) {
                rate = rs.getString("ReservationID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rate;
    }

    static boolean AddReservation(String reservationID, int guestID, String roomNo, String checkInDate, String checkOutDate, String roomRate, String Taxes, String Total) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();

            String insertsql = "INSERT INTO RoomReservation (ReservationID, GuestID, RoomNo, CheckInDate, CheckOutDate, RoomRate, Taxes,Total) values(?,?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setString(1, reservationID);
            pstmt.setInt(2, guestID);
            pstmt.setString(3, roomNo);
            pstmt.setString(4, checkInDate);
            pstmt.setString(5, checkOutDate);
            pstmt.setString(6, roomRate);
            pstmt.setString(7, Taxes);
            pstmt.setString(8, Total);
            pstmt.executeUpdate();

            String sql2 = "UPDATE Room SET RoomStatus = 'Reserved' WHERE RoomNo = " + roomNo;
            stmt.executeUpdate(sql2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    static boolean editReservation(String rID, String roomNo, String checkInDate, String checkOutDate, String miscCharge, String total, String initialMisc) {

        double intialmisc = Double.valueOf(initialMisc);

        double misc = Double.valueOf(miscCharge);

        misc = misc - intialmisc;
        double totaldoble = Double.valueOf(total);
        double totalnow = misc + totaldoble;
        total = "" + totalnow;

        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();

            String insertsql = "UPDATE RoomReservation SET roomNo = ?, CheckInDate = ?, CheckOutDate = ?, MiscCharges = ?, Total = ? WHERE ReservationID = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setString(1, roomNo);
            pstmt.setString(2, checkInDate);
            pstmt.setString(3, checkOutDate);
            pstmt.setString(4, miscCharge);
            pstmt.setString(5, total);
            pstmt.setString(6, rID);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean CheckOut(int rID, String paymentMethod,String roomNo) {
        try {

            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            
            String insertsql = "UPDATE RoomReservation SET PaymentStatus = 1, CheckOutStatus = 1, PayMethod =? WHERE ReservationID = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setString(1, paymentMethod);
            pstmt.setInt(2, rID);
            pstmt.executeUpdate();
            
            String update = "UPDATE Room SET RoomStatus = 'Available' WHERE RoomNo = ?";
            PreparedStatement pstmt2 = con.prepareStatement(update);
            pstmt2.setString(1,roomNo);
            pstmt2.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean addRoom(int roomNo, String roomType, double roomRate, int roomLimit, File roomImage) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String insertsql = "INSERT INTO Room (RoomNo, RoomType, RoomRate, RoomLimit, RoomImage, RoomStatus)VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setInt(1, roomNo);
            pstmt.setString(2, roomType);
            pstmt.setDouble(3, roomRate);
            pstmt.setInt(4, roomLimit);
            FileInputStream fis = new FileInputStream(roomImage);
            pstmt.setBinaryStream(5, fis, (int) roomImage.length());
            pstmt.setString(6, "Available");
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean editRoom(int roomNo, String roomType, double roomRate, int roomLimit) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "UPDATE Room SET RoomRate = ?, RoomType = ?, RoomLimit = ? WHERE RoomNo = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setDouble(1, roomRate);
            pstmt.setString(2, roomType);
            pstmt.setInt(3, roomLimit);
            pstmt.setInt(4, roomNo);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean editRoom(int roomNo, String roomType, double roomRate, int roomLimit, File roomImage) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "UPDATE Room SET RoomRate = ?, RoomType = ?, RoomLimit = ?, roomImage = ? WHERE RoomNo = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setDouble(1, roomRate);
            pstmt.setString(2, roomType);
            pstmt.setInt(3, roomLimit);
            FileInputStream fis = new FileInputStream(roomImage);
            pstmt.setBinaryStream(4, fis, (int) roomImage.length());
            pstmt.setInt(5, roomNo);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean removeRoom(String roomID) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "DELETE FROM Room  WHERE RoomNo = ?;";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setString(1, roomID);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //CONFERENCE ROOMS
    static String getMaxConfRoomID() {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "Select MAX(ConfRoomNo) AS [RoomNo] from ConferenceRooms";
            ResultSet rs = stmt.executeQuery(search);
            String test = "";
            while (rs.next()) {
                test = rs.getString("RoomNo");
                if (test.isEmpty()) {
                    return "";
                } else {
                    return test;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";

    }

    static boolean addConferenceRoom(int roomNo, double roomRate, int roomLimit, File roomImage) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "INSERT INTO ConferenceRooms (ConfRoomNo, Capacity, BookRate, ConfRoomImage, ConfRoomStatus)VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setInt(1, roomNo);
            pstmt.setInt(2, roomLimit);
            pstmt.setDouble(3, roomRate);
            FileInputStream fis = new FileInputStream(roomImage);
            pstmt.setBinaryStream(4, fis, (int) roomImage.length());
            pstmt.setString(5, "Available");
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean editConferenceRoom(int roomNo, double roomRate, int roomLimit, File roomImage) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "UPDATE ConferenceRooms SET BookRate = ?, Capacity = ?, ConfRoomImage = ? WHERE ConfRoomNo = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setDouble(1, roomRate);
            pstmt.setInt(2, roomLimit);

            FileInputStream fis = new FileInputStream(roomImage);
            pstmt.setBinaryStream(3, fis, (int) roomImage.length());
            pstmt.setInt(4, roomNo);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean editConferenceRoom(int roomNo, double roomRate, int roomLimit) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "UPDATE ConferenceRooms SET BookRate = ?, Capacity = ? WHERE ConfRoomNo = ?";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setDouble(1, roomRate);
            pstmt.setInt(2, roomLimit);
            pstmt.setInt(3, roomNo);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean removeConferenceRoom(String roomNo) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            String insertsql = "DELETE FROM ConferenceRooms  WHERE ConfRoomNo = ?;";
            PreparedStatement pstmt = con.prepareStatement(insertsql);
            pstmt.setString(1, roomNo);
            pstmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
