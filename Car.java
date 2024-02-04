/************************************************************************************
 * Wayne Alexander Mack Jr.                                                         *
 * email: wamj283@gmail.com                                                         *
 * phone: (443) 627 - 1117                                                          *
 * -------------------------------------------------------------------------------- *
 * CMSC 335 - Project 3                                                             *
 * Written in : JAVA                                                                *
 * (c) 2020 Wayne Alexander Mack Jr.                                                *
 * -------------------------------------------------------------------------------- *
 *  Car.Java - Object and properties for the cars used in display                   *
 ************************************************************************************/
package Project3;
import java.awt.*;
import java.util.Random;
public class Car {
    boolean want_to_continue;
    directionState Direction;
    Color carColor;
    int currentCarPosition;
    public int carSpeed;
    boolean Active;
    CarMovement carMovement;
    private int time;
    Car(directionState _directionState, int carSpeed) {
        this.carSpeed = carSpeed;
        this.carColor = newCarColor();
        this.Direction = _directionState;
        currentCarPosition = 0;
        carMovement = CarMovement.PROCEED;
        Active = true;
    }
    public void makeActive() {
        currentCarPosition = 0;
        Active = true;
    }
    public void makeInactive() {
        currentCarPosition = 0;
        Active = false;
    }
    public  void stopOnRed() {
        this.carMovement = CarMovement.STOP;
    }
    public  void goOnGreen() {
        this.carMovement = CarMovement.PROCEED;
    }
    public void setCarSpeed (int _carSpeed) {
        this.carSpeed = _carSpeed;
        System.out.println ("Car speed was changed to " + _carSpeed + "\nCar Speed is now: " + carSpeed );
    }
    private Color newCarColor() {
        Random r = new Random();
        switch (r.nextInt(8)) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.PINK;
            case 7:
                return Color.RED;
            default:
                return Color.YELLOW;
        }
    }
    public Thread moveCar = new Thread() {
        public synchronized void run() {
            while (!currentThread().isInterrupted()) {
                while (want_to_continue) {
                    try {
                        // THIS SLEEP BUFFER NEEDS TO BE HERE BECAUSE, THE THREAD IS BEING EXECUTED TOO FAST
                        sleep(1);
                        if (carMovement == CarMovement.STOP && currentCarPosition == 130) {
                            // DO NOT PROCEED
                        } else {
                            time = 250 / carSpeed;
                            sleep(time);
                            currentCarPosition++;
                            if (currentCarPosition > 380) {
                                Active = false;
                                Random r = new Random();
                                try {
                                    sleep(r.nextInt(5) * 1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Active = true;
                                carColor = newCarColor();
                                currentCarPosition = 0;
                            }
                        }
                    } catch (InterruptedException x) {
                        currentThread().interrupt();
                    }
                }
            }
        }
    };
}
