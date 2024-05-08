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
 * @author admin
 */
public class Events {
    static boolean ReserveEvent (int eventID, int guestID,int room,java.sql.Date sqlDate,int duration, int status, String method, String Taxes, String Total, String ConfCharge, String CateringCharge,String DecorCharge, String RequestCharge){
    try{
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String reserveRoom =  "UPDATE ConferenceRooms SET ConfRoomStatus = 'Reserved' WHERE ConfRoomNo = " + room;
            String sql = "INSERT INTO EventReservation "
                    + "(EventID, GuestID, RoomAssigned, EventDate, Duration, PaymentStatus, PayMethod, Taxes, Total,ConfRoomCharge,CateringCharge,DecorCharge,RequestCharge)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1,eventID);
            pstmt.setInt(2, guestID);
            pstmt.setInt(3, room);
            pstmt.setDate(4, sqlDate);
            pstmt.setInt(5, duration);
            pstmt.setInt(6, status);
            pstmt.setString(7, method);
            pstmt.setString(8, Taxes);
            pstmt.setString(9, Total);
            pstmt.setString(10, ConfCharge);
            pstmt.setString(11, CateringCharge);
            pstmt.setString(12, DecorCharge);
            pstmt.setString(13, RequestCharge);
            
            pstmt.executeUpdate();
            stmt.executeUpdate(reserveRoom);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    static boolean searchRoomID(int RoomID) {
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select ConfRoomNo from ConferenceRooms where " + RoomID + "= ConfRoomNo and ConfRoomStatus = 'Available'";
            ResultSet rs = stmt.executeQuery(search);
            String test = "";
            while (rs.next()) {
                test = rs.getString("ConfRoomNo");
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
    static String getRoomRate(int RoomID){
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select BookRate from ConferenceRooms where " + RoomID + "= ConfRoomNo and ConfRoomStatus = 'Available'";
            ResultSet rs = stmt.executeQuery(search);
            String test = "";
            while (rs.next()) {
                test = rs.getString("BookRate");
                if (test.isEmpty()) {
                    return test;
                } else {
                    return test;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
