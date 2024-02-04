/************************************************************************************
 * Wayne Alexander Mack Jr.                                                         *
 * email: wamj283@gmail.com                                                         *
 * phone: (443) 627 - 1117                                                          *
 * -------------------------------------------------------------------------------- *
 * CMSC 335 - Project 3                                                             *
 * Written in : JAVA                                                                *
 * (c) 2020 Wayne Alexander Mack Jr.                                                *
 * -------------------------------------------------------------------------------- *
 *  categoryPanel.java - for Panel object used in display                           *
 ************************************************************************************/
package Project3;
import javax.swing.*;
public class categoryPanel extends JPanel {        JTextField textInput;
    JLabel      Category;
    categoryPanel (String  _category, int rowNumber) {
        setLayout(null);
        Category = new JLabel(_category + ":");
        textInput = new JTextField(10);
        setBounds(0,((rowNumber - 1) * 50),400, 50);
        Category.setBounds(10, 10, 150, 20);
        textInput.setBounds(200, 10, 100, 20);
        add(Category);
        add(textInput);
    }
    categoryPanel (String _category, int RowNumber , int initialValue) {
        setLayout(null);
        Category = new JLabel (_category + ":");
        textInput = new JTextField(10);
        setSize(400, 50);
        Category.setBounds(10, 10, 150, 20);
        textInput.setBounds(200, 10, 100, 20);
        textInput.setText(String.valueOf(initialValue));
        add (Category);
        add (textInput);
    }
    public int getNumericValue () {
        // USE A TRY/CATCH WHEN USING THIS METHOD OR EXCEPTION WILL OCCUR
        return Integer.parseInt(textInput.getText());
    }
}
