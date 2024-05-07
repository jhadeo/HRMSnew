/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HRMS;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
/**
 *
 * @author PC
 */
class UtilityCollection {
    /**how to use:
     * JTextFieldLimit - JTextFieldName.setDocument(new JTextFieldLimit(10));
    */
    UtilityCollection(int choice, int i){
    switch(choice){
        case 1: {new JTextFieldLimit(i); break;}
    }
    }
}

public class JTextFieldLimit extends PlainDocument { //sets maximum length of text field
  private int limit;

  JTextFieldLimit(int limit) {
   super();
   this.limit = limit;
   }

  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str, attr);
    }
  }
}