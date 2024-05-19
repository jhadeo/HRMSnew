/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import java.sql.Connection;
import java.sql.DriverManager;
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
    @Override
    public String getPassword(String username){
        String password = "";
        try {
            Connection con = DriverManager.getConnection(conSQL.connect(), conSQL.user(), conSQL.password());
            Statement stmt = con.createStatement();
            String search = "select password from STAFF where " + username + "= username";
            ResultSet rs = stmt.executeQuery(search);
            while (rs.next()) {
                password = rs.getString("username");
            }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    };
    public String getValue(){
    
    return "";
    };

    @Override
    public String getStaff() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

abstract class StaffSQL{
    public abstract boolean addValue();
    public abstract String getStaff();
    public abstract String getPassword(String username);
}