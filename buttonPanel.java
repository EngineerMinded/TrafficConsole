/************************************************************************************
 * Wayne Alexander Mack Jr.                                                         *
 * 6425 Union Court                                                                 *
 * Glen Burnie, Maryland 21061                                                      *
 * email: wamj283@gmail.com                                                         *
 * phone: (443) 627 - 1117                                                          *
 * -------------------------------------------------------------------------------- *
 * CMSC 335 - Project 3                                                             *
 * Written in : JAVA                                                                *
 * (c) 2020 Wayne Alexander Mack Jr.                                                *
 * -------------------------------------------------------------------------------- *
 *  buttonPanel.java - for Panel object used in display                             *
 ************************************************************************************/
package Project3;

import javax.swing.*;

public class buttonPanel extends JPanel {
        JButton button;
        buttonPanel (String buttonLabel, int rowNumber) {
        setLayout(null);
        setBounds(0,((rowNumber - 1) * 50),400,50);
        button = new JButton();
        button.setBounds (150,10,100,30);
        button.setText(buttonLabel);
        add(button);
        }
public void changeText (String newText) {
        button.setText(newText);
        }
        }