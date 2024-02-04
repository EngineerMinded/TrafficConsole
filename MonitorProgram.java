/************************************************************************************
 * Wayne Alexander Mack Jr.                                                         *
 * email: wamj283@gmail.com                                                         *
 * phone: (443) 627 - 1117                                                          *
 * -------------------------------------------------------------------------------- *
 * CMSC 335 - Project 3                                                             *
 * Written in : JAVA                                                                *
 * (c) 2020 Wayne Alexander Mack Jr.                                                *
 * -------------------------------------------------------------------------------- *
 * Change Log:                                                                      *
 * 17 SEP 2020 - Still frame graphics tested okay                                   *
 * 21 SEP 2020 - Thread for Traffic Light tested okay                               *
 * 25 SEP 2020 - Cars move okay, Error causes cars to stop                          *
 * 28 SEP 2020 - Threads written and confirmed to operate properly                  *
 * 29 SEP 2020 - Cars respond to traffic trends. Operations complete                *
 * 03 OCT 2020 - Car speed was programmed and proven to respond                     *
 * 05 OCT 2020 - Pause functionality was added and tested                           *
 * O6 OCT 2020 - Added Borders, about window, error message window                  *
 * 06 OCT 2020 - Placed all classes individual .java files                          *
 ************************************************************************************/
package Project3;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
public class MonitorProgram extends JFrame {
    static Display display1;
    static Display display2;
    static Display display3;
    JPanel mainPanel;
    JButton aboutButton;
    JLabel timeLabel;
    LocalTime timeOfDay;
    MonitorProgram () {
        setTitle("Traffic Monitoring Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize (1500,1000);
        display1 = new Display("Intersection 1");
        display2 = new Display("Intersection 2");
        display3 = new Display("Intersection 3");
        mainPanel = new JPanel();
        aboutButton = new JButton();
        timeLabel = new JLabel();
        mainPanel.setBounds(0,0,1500,1050);
        mainPanel.setLayout(null);
        display1.setBounds(0,0,500,1000);
        display2.setBounds(500,0,500,1000);
        display3.setBounds(1000,0,500,1000);
        aboutButton.setBounds (300,850,100,20);
        aboutButton.setText("About");
        timeLabel.setText("TIME:");
        timeLabel.setBounds(100,850,200,20);
        display1.add(aboutButton);
        display1.add(timeLabel);
        mainPanel.add(display1);
        mainPanel.add(display2);
        mainPanel.add(display3);
        add(mainPanel);
        setVisible(true);
        Thread timeDisplay = new Thread() {
            public void run () {
                while (!currentThread().isInterrupted()) {
                    try {
                        sleep (200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeOfDay = LocalTime.now();
                    timeLabel.setText("Time: " + timeOfDay.toString());
                }
            }
        };
        timeDisplay.start();
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PopUpMessages.aboutMessage();
            }
        });
    }
    public static void main (String[] args) {
        new MonitorProgram();
    }
}
