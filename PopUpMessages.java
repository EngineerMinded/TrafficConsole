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
 *  PopUpMessages.java - Error and about message used in program                    *
 ************************************************************************************/
package Project3;
import javax.swing.*;
public class PopUpMessages {
    private static JFrame frame;
    public static void nonNumericErrorMessage () {
        JOptionPane.showMessageDialog(frame,
                "" +
                        "Non-numeric value entered! \n" +
                        "Please enter a numeric value\n" +
                        "and try again!",
                "Format Error",
                JOptionPane.ERROR_MESSAGE);
    }
    public static void aboutMessage () {
        JOptionPane.showMessageDialog(frame,
                "" +
                        "(c)2020 Wayne Alexander Mack Jr. \n\n" +
                        "Made for CMSC 335 Project 3 Assignment\n" +
                        "\nThis software may be used for demonstration" +
                        "\nor educational purposes. This program is not" +
                        "\npublic domain. Please respect the programmer's" +
                        "\nrights!",
                "Traffic Monitor",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

