/*
    Color thresholds should be in the range [0-255]
    Proximity thresholds should be in the range [0-2047]
*/

package frc.robot.sensors;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;

public class RevColorSensor {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

    private int redLowThresh;
    private int redHighThresh;
    private int greenLowThresh;
    private int greenHighThresh;
    private int blueLowThresh;
    private int blueHighThresh;
    private int proxLowThresh;
    private int proxHighThresh;

    public RevColorSensor(int redLowThresh,   int redHighThresh,
                          int greenLowThresh, int greenHighThresh,
                          int blueLowThresh,  int blueHighThresh,
                          int proxLowThresh,  int proxHighThresh){
        this.redLowThresh = redLowThresh;
        this.redHighThresh = redHighThresh;
        this.greenLowThresh = greenLowThresh;
        this.greenHighThresh = greenHighThresh;
        this.blueLowThresh = blueLowThresh;
        this.blueHighThresh = blueHighThresh;
        this.proxLowThresh = proxLowThresh;
        this.proxHighThresh = proxHighThresh;
    }

    public boolean isTarget(){
        return(isRedThresholdMet() && isGreenThresholdMet() && isBlueThresholdMet() && isProximityMet());
    }

    public boolean isRedThresholdMet(){
        return(getRed() > redLowThresh && getRed() < redHighThresh);
    }

    public boolean isGreenThresholdMet(){
        return(getGreen() > greenLowThresh && getGreen() < greenHighThresh);
    }

    public boolean isBlueThresholdMet(){
        return(getBlue() > blueLowThresh && getBlue() < blueHighThresh);
    }

    public boolean isProximityMet(){
        return(getProximity() > proxLowThresh && getProximity() < proxHighThresh);
    }

    public int getRed(){
        return colorSensor.getRed();
    }

    public int getGreen(){
        return colorSensor.getGreen();
    }

    public int getBlue(){
        return colorSensor.getBlue();
    }

    public double getIR(){
        return colorSensor.getIR();
    }

    public int getProximity(){
        return colorSensor.getProximity();
    }

}
