/*
    Color thresholds should be in the range [0-255]
    Proximity thresholds should be in the range [0-2047]
*/

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

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

  private static int idGen = 0;
  private int id;

  private ShuffleboardTab tab;

  private NetworkTableEntry redLowThreshShuffle;
  private NetworkTableEntry redHighThreshShuffle;
  private NetworkTableEntry greenLowThreshShuffle;
  private NetworkTableEntry greenHighThreshShuffle;
  private NetworkTableEntry blueLowThreshShuffle;
  private NetworkTableEntry blueHighThreshShuffle;
  private NetworkTableEntry proxLowThreshShuffle;
  private NetworkTableEntry proxHighThreshShuffle;

  public RevColorSensor(int redLowThresh, int redHighThresh, int greenLowThresh,
                        int greenHighThresh, int blueLowThresh, int blueHighThresh,
                        int proxLowThresh, int proxHighThresh) {
    this.redLowThresh = redLowThresh;
    this.redHighThresh = redHighThresh;
    this.greenLowThresh = greenLowThresh;
    this.greenHighThresh = greenHighThresh;
    this.blueLowThresh = blueLowThresh;
    this.blueHighThresh = blueHighThresh;
    this.proxLowThresh = proxLowThresh;
    this.proxHighThresh = proxHighThresh;

    id = ++idGen;
    tab = Shuffleboard.getTab("Color Sensor " + id);

    redLowThreshShuffle   = tab.add("Red Threshold Low",         redLowThresh).getEntry();
    redLowThreshShuffle   = tab.add("Red Threshold High",        redLowThresh).getEntry();
    greenLowThreshShuffle = tab.add("Green Threshold Low",       greenLowThresh).getEntry();
    greenLowThreshShuffle = tab.add("Green Threshold High",      greenLowThresh).getEntry();
    blueLowThreshShuffle  = tab.add("Blue Threshold Low",        blueLowThresh).getEntry();
    blueLowThreshShuffle  = tab.add("Blue Threshold High",       blueLowThresh).getEntry();
    proxLowThreshShuffle  = tab.add("Proximity Threshold Low",   proxLowThresh).getEntry();
    proxLowThreshShuffle  = tab.add("Proximity Threshold High",  proxLowThresh).getEntry();

    tab.addNumber  ("Red",       this::getRed);
    tab.addNumber  ("Green",     this::getGreen);
    tab.addNumber  ("Blue",      this::getBlue);
    tab.addNumber  ("Proximity", this::getProximity);
    tab.addBoolean ("Target?",   this::isTarget);
  }

  // TODO: Does polling shuffleboard on every method call have performance implications?

  public boolean isTarget() {
    return (isRedThresholdMet() && isGreenThresholdMet() && isBlueThresholdMet() &&
            isProximityMet());
  }

  public boolean isRedThresholdMet() {
    redLowThresh = redLowThreshShuffle.getNumber(redLowThresh).intValue();
    redHighThresh = redHighThreshShuffle.getNumber(redHighThresh).intValue();
    return (getRed() > redLowThresh && getRed() < redHighThresh);
  }

  public boolean isGreenThresholdMet() {
    greenLowThresh = greenLowThreshShuffle.getNumber(greenLowThresh).intValue();
    greenHighThresh = greenHighThreshShuffle.getNumber(greenHighThresh).intValue();
    return (getGreen() > greenLowThresh && getGreen() < greenHighThresh);
  }

  public boolean isBlueThresholdMet() {
    blueLowThresh = blueLowThreshShuffle.getNumber(blueLowThresh).intValue();
    blueHighThresh = blueHighThreshShuffle.getNumber(blueHighThresh).intValue();
    return (getBlue() > blueLowThresh && getBlue() < blueHighThresh);
  }

  public boolean isProximityMet() {
    proxLowThresh = proxLowThreshShuffle.getNumber(proxLowThresh).intValue();
    proxHighThresh = proxHighThreshShuffle.getNumber(proxHighThresh).intValue();
    return (getProximity() > proxLowThresh && getProximity() < proxHighThresh);
  }

  public int getRed() { return colorSensor.getRed(); }

  public int getGreen() { return colorSensor.getGreen(); }

  public int getBlue() { return colorSensor.getBlue(); }

  public double getIR() { return colorSensor.getIR(); }

  public int getProximity() { return colorSensor.getProximity(); }
}
