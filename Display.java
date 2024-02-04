/************************************************************************************
 * Wayne Alexander Mack Jr.                                                         *
 * email: wamj283@gmail.com                                                         *
 * phone: (443) 627 - 1117                                                          *
 * -------------------------------------------------------------------------------- *
 * CMSC 335 - Project 3                                                             *
 * Written in : JAVA                                                                *
 * (c) 2020 Wayne Alexander Mack Jr.                                                *
 * -------------------------------------------------------------------------------- *
 *  Display.java - class for each individual display object                         *
 ************************************************************************************/
package Project3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Display extends JPanel {
    public volatile boolean continueRunning = true;
    Monitor monitor;
    Console console;
    Thread primaryDisplayThread, trafficLightTimeKeeper;
    int nSSpeed = 5, eWSpeed = 10;
    long nSTrafficTime, eWTrafficTime;
    CarMovement pauseProceedState;
    JLabel titleLabel;
    Display (String Title) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
        titleLabel = new JLabel();
        titleLabel.setBounds (200,20,100,20);
        titleLabel.setText (Title);
        nSTrafficTime = 10000;
        eWTrafficTime = 5000;
        setLayout (null);
        setSize (500,800);
        monitor = new Monitor();
        console = new Console();
        monitor.setBounds (50,50,400,400);
        console.setBounds (50,500,400,250);
        console.northSouthTrafficTime.textInput.setText("10");
        console.eastWestTrafficTime.textInput.setText("5");
        console.northSouthSpeed.textInput.setText("30");
        console.eastWestSpeed.textInput.setText("50");
        add (titleLabel);
        add(monitor);
        add(console);
        primaryDisplayThread = new Thread(() -> {
            pauseProceedState = CarMovement.PROCEED;
            monitor.trafficLightTimerThread.start();
            monitor.reportTrafficConditions.start();
            monitor.northCar.moveCar.start();
            monitor.southCar.moveCar.start();
            monitor.eastCar.moveCar.start();
            monitor.westCar.moveCar.start();
            monitor.refreshThread.start();
        });
        primaryDisplayThread.start();
        /*******************************************************************************************************
         * These focus listeners are for all of the objects in the console that allow the monitor to be        *
         * modified.                                                                                           *
         *******************************************************************************************************/
        console.northSouthSpeed.textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    monitor.northCar.carSpeed = (Integer.parseInt(
                            console.northSouthSpeed.textInput.getText()));
                    monitor.southCar.carSpeed = (Integer.parseInt(
                            console.northSouthSpeed.textInput.getText()));
                }
                catch (NumberFormatException e1) {
                    PopUpMessages.nonNumericErrorMessage();
                }
            }
        });
        console.eastWestSpeed.textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    monitor.eastCar.carSpeed =(Integer.parseInt(console.eastWestSpeed.textInput.getText()));
                    monitor.westCar.carSpeed = (Integer.parseInt(console.eastWestSpeed.textInput.getText()));
                }
                catch (NumberFormatException e1) {
                    PopUpMessages.nonNumericErrorMessage();
                }
            }
        });
        console.northSouthTrafficTime.textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    monitor.changeNSTime (Integer.parseInt(console.northSouthTrafficTime.textInput.getText())*1000);
                }
                catch (NumberFormatException e1) {
                    PopUpMessages.nonNumericErrorMessage();
                }
            }
        });
        console.eastWestTrafficTime.textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    monitor.changeEWTime (Integer.parseInt(console.eastWestTrafficTime.textInput.getText())*1000);
                }
                catch (NumberFormatException e1) {
                    PopUpMessages.nonNumericErrorMessage();
                }
            }
        });
        console.pauseResumeButton.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pauseProceedState == CarMovement.STOP)  {
                    pauseProceedState = CarMovement.PROCEED;
                    monitor.want_to_Continue = true;
                    console.pauseResumeButton.changeText("Pause");
                }
                else {
                    pauseProceedState = CarMovement.STOP;
                    monitor.want_to_Continue = false;
                    console.pauseResumeButton.changeText("Resume");
                }
            }
        });
    }
}
