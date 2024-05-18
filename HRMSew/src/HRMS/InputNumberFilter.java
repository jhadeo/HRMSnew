/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.NumberFormatter;

/* How to use:
*  Customize Code, change new javax.swing.JTextField() to new javax.swing.JFormattedTextField(numFormat)
*                                                             javax.swing.JFormattedTextField(currencyFormat)
 */
public class InputNumberFilter extends NumberFormatter {

    public InputNumberFilter(NumberFormat longFormat) {
        super(longFormat);
    }

    /**
     *
     * @param text
     * @return
     * @throws ParseException
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text.isEmpty()) {
            return null;
        } else {
        }
        return super.stringToValue(text);
    }

    public static String currencyConverter(double initial){
       String converted = "";
       String result;
       converted = initial + "";
       int length = converted.length();
       while(converted.length()!=8){
       converted = "0" + converted;
       }
       if(converted.indexOf('.')!=5){
       converted = converted.substring(1) + "0";
       }  
       converted = converted.substring(0,8);
       System.out.println(converted);
       converted ="₱" + converted;
       return converted;
   }
}
