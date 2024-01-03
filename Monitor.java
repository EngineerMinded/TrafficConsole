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
 *  Monitor.java - class for graphical display object                               *
 ************************************************************************************/
package Project3;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.currentThread;

public class Monitor extends JPanel {
    TrafficFlowState trafficFlowState;
    Car northCar;
    Car southCar;
    Car eastCar;
    Car westCar;
    public long trafficTimeNS, trafficTimeEW;
    boolean want_to_Continue;
    Monitor () {
        want_to_Continue = true;
        trafficTimeNS = 10000;
        trafficTimeEW = 5000;
        trafficFlowState = TrafficFlowState.GreenEastWest;
        northCar = new Car(directionState.NORTH,30);
        southCar = new Car(directionState.SOUTH,30);
        eastCar = new Car(directionState.EAST,50);
        westCar = new Car(directionState.WEST,50);
    }
    /*************************************************************************************************
     * This method changes the traffic light time for North/South Traffic                            *
     * @param _time                                                                                  *
     *************************************************************************************************/
    public void changeNSTime (int _time) {
        this.trafficTimeNS = Long.valueOf(_time);
    }
    /*************************************************************************************************
     * This method changes the traffic light time for East/West Traffic                              *
     * @param _time                                                                                  *
     *************************************************************************************************/
    public void changeEWTime (int _time) {
        this.trafficTimeEW = Long.valueOf(_time) ;
    }
    /*************************************************************************************************
     * This method is used to paint and refresh the display                                          *
     * @param g                                                                                      *
     *************************************************************************************************/
    public synchronized void paint (Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0,400,399,199);
        setSize (400,400);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(0 ,0 ,400 ,400);
        g.fillRect(0, 0, 400, 400);
        g.drawRect(0, 0, 150, 150);
        // Land on sides of Streets
        g.setColor(Color.green);
        g.fillRect(0, 0, 150, 150);
        g.drawRect(250, 0, 150, 150);
        g.setColor(Color.green);
        g.fillRect(250, 0, 150, 150);
        g.drawRect(0,250,150,150);
        g.setColor(Color.green);
        g.fillRect(0, 250, 150, 150);
        g.drawRect(250,250,150,150);
        g.setColor(Color.green);
        g.fillRect(250, 250, 150, 150);
        // Create Painted Lines on the Street
        g.setColor (Color.WHITE);
        for (int paintedLines = 15; paintedLines < 150; paintedLines = paintedLines + 25) {
            g.drawLine(paintedLines, 200, paintedLines+10, 200);
        }
        for (int paintedLines = 15; paintedLines < 150; paintedLines = paintedLines + 25) {
            g.drawLine(200, paintedLines, 200,paintedLines+10);
        }
        for (int paintedLines = 250; paintedLines < 400; paintedLines = paintedLines + 25) {
            g.drawLine(200, paintedLines, 200,paintedLines+10);
        }
        for (int paintedLines = 250; paintedLines < 400; paintedLines = paintedLines + 25) {
            g.drawLine(paintedLines, 200, paintedLines+10, 200);
        }
        // create stop lines
        g.drawLine(150,150,200,150);
        g.drawLine(150,250,150,200);
        g.drawLine(250,150,250,200);
        g.drawLine(250,250,200,250);
        // create Traffic Light Frames
        g.setColor(Color.BLACK);
        g.fillRect(270,90,20,40);
        g.fillRect(110,270,20,40);
        g.fillRect(270,270,40,20);
        g.fillRect(90,110,40,20);
        // TrafficLight determination
        if (trafficFlowState == TrafficFlowState.AllDirectionsRed) {
            g.setColor (Color.RED);
            g.fillOval (275,95,10,10);
            g.fillOval (115,295,10,10);
            g.fillOval (295,275,10,10);
            g.fillOval (95,115,10,10);
        }
        else if (trafficFlowState == TrafficFlowState.GreenNorthSouth) {
            g.setColor (Color.GREEN);
            g.fillOval (275,115,10,10);
            g.fillOval (115,275,10,10);
            g.setColor(Color.RED);
            g.fillOval (295,275,10,10);
            g.fillOval (95,115,10,10);
        }
        else if (trafficFlowState == TrafficFlowState.YellowNorthSouth) {
            g.setColor (Color.YELLOW);
            g.fillOval (275,105,10,10);
            g.fillOval (115,285,10,10);
            g.setColor(Color.RED);
            g.fillOval (295,275,10,10);
            g.fillOval (95,115,10,10);
        }
        else if (trafficFlowState == TrafficFlowState.GreenEastWest) {
            g.setColor (Color.RED);
            g.fillOval (275,95,10,10);
            g.fillOval (115,295,10,10);
            g.setColor(Color.GREEN);
            g.fillOval (275,275,10,10);
            g.fillOval (115,115,10,10);
        }
        else if (trafficFlowState == TrafficFlowState.YellowEastWest) {
            g.setColor (Color.RED);
            g.fillOval (275,95,10,10);
            g.fillOval (115,295,10,10);
            g.setColor(Color.YELLOW);
            g.fillOval (285,275,10,10);
            g.fillOval (105,115,10,10);
        }
        // Draw Cars!
        if (northCar.Active) {
            g.setColor(northCar.carColor);
            g.fillRect(215,380 - northCar.currentCarPosition,20,20);
        }
        if (southCar.Active) {
            g.setColor(southCar.carColor);
            g.fillRect(165,southCar.currentCarPosition,20,20);
        }
        if (eastCar.Active) {
            g.setColor(eastCar.carColor);
            g.fillRect (eastCar.currentCarPosition,215,20,20);
        }
        if (westCar.Active) {
            g.setColor(westCar.carColor);
            g.fillRect(380 - westCar.currentCarPosition,165,20,20);
        }
    }
    // Thread to refresh screen every 5 milliseconds
    Thread refreshThread = new Thread(() -> {
        while (!currentThread().isInterrupted()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
            repaint();
        }
    });
    Thread reportTrafficConditions = new Thread () {
        public synchronized void run() {
            while (!currentThread().isInterrupted()) {

                // BUFFER DUE TO THREAD EXECUTING TOO FAST
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!want_to_Continue) {
                    northCar.want_to_continue = false;
                    southCar.want_to_continue = false;
                    eastCar.want_to_continue = false;
                    westCar.want_to_continue = false;
                }
                else {
                    northCar.want_to_continue = true;
                    southCar.want_to_continue = true;
                    eastCar.want_to_continue = true;
                    westCar.want_to_continue = true;
                }
                if (trafficFlowState == TrafficFlowState.GreenNorthSouth) {
                    northCar.goOnGreen();
                    southCar.goOnGreen();
                } else {
                    northCar.stopOnRed();
                    southCar.stopOnRed();
                }
                if (trafficFlowState == TrafficFlowState.GreenEastWest) {

                    eastCar.goOnGreen();
                    westCar.goOnGreen();
                }
                else {
                    eastCar.stopOnRed();
                    westCar.stopOnRed();
                }
            }
        }
    };
    Thread trafficLightTimerThread = new Thread() {
        public void run() {
            try {
                while (!currentThread().isInterrupted()) {
                    while (want_to_Continue) {
                        trafficFlowState = TrafficFlowState.AllDirectionsRed;
                        sleep(1000);
                        if (!want_to_Continue) {break;}
                        trafficFlowState = TrafficFlowState.GreenNorthSouth;
                        sleep(trafficTimeNS);
                        if (!want_to_Continue) {break;}
                        trafficFlowState = TrafficFlowState.YellowNorthSouth;
                        sleep(5000);
                        if (!want_to_Continue) {break;}
                        trafficFlowState = TrafficFlowState.AllDirectionsRed;
                        sleep(1000);
                        if (!want_to_Continue) {break;}
                        trafficFlowState = TrafficFlowState.GreenEastWest;
                        sleep(trafficTimeEW);
                        if (!want_to_Continue) {break;}
                        trafficFlowState = TrafficFlowState.YellowEastWest;
                        sleep(5000);
                        if (!want_to_Continue) {break;}
                    }
                }
            }
            catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    };


}
