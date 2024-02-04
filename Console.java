/************************************************************************************
 * Wayne Alexander Mack Jr.                                                         *
 * email: wamj283@gmail.com                                                         *
 * phone: (443) 627 - 1117                                                          *
 * -------------------------------------------------------------------------------- *
 * CMSC 335 - Project 3                                                             *
 * Written in : JAVA                                                                *
 * (c) 2020 Wayne Alexander Mack Jr.                                                *
 * -------------------------------------------------------------------------------- *
 *  Console.java - for user objects in each traffic display                         *
 ************************************************************************************/
package Project3;
import javax.swing.*;
public class Console extends JPanel {
    categoryPanel eastWestSpeed, northSouthSpeed, northSouthTrafficTime, eastWestTrafficTime;
    buttonPanel pauseResumeButton;
    Console () {
        setBounds(0,0,400,250);
        setLayout(null);
        eastWestSpeed = new categoryPanel("E/W Speed (mph)",1);
        northSouthSpeed = new categoryPanel("N/S Speed (mph)",2);
        eastWestTrafficTime = new categoryPanel("E/W Traffic Time (secs)" , 3);
        northSouthTrafficTime = new categoryPanel("N/S Traffic Time (secs)",4);
        pauseResumeButton = new buttonPanel("Pause",5);
        add(eastWestSpeed);
        add(northSouthSpeed);
        add(northSouthTrafficTime);
        add(eastWestTrafficTime);
        add(pauseResumeButton);
    }
    Console (int initialEWSpeed, int initialNSSpeed, int initialEWTraffic, int initialNSTraffic) {
        setBounds(0,0,400,250);
        setLayout (null);
        eastWestSpeed = new categoryPanel("E/W Speed",1, initialEWSpeed);
        northSouthSpeed = new categoryPanel("N/S Speed",2, initialNSSpeed);
        eastWestTrafficTime = new categoryPanel("E/W Traffic Time" , 3, initialEWTraffic);
        northSouthTrafficTime = new categoryPanel("N/S Traffic Time",4, initialNSTraffic);
        pauseResumeButton = new buttonPanel("Pause",5);
        add(eastWestSpeed);
        add(northSouthSpeed);
        add(northSouthTrafficTime);
        add(eastWestTrafficTime);
        add(pauseResumeButton);
    }
}
