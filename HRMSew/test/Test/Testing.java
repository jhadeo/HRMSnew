/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author user
 */
public class Testing {
    public static void main(String[] args) {
           MaskFormatter currencyFormat = null;
        try {
            currencyFormat = new MaskFormatter("$######.##");
            currencyFormat.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        } 
        JTextField n = new JFormattedTextField(currencyFormat);
        n.setText("$234567.00");
        String convert = n.getText().substring(1);
        float out = Float.parseFloat(convert);
        System.out.println(out);
    }
}
