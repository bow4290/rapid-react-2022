/*
    Color thresholds should be in the range [0-255]
    Proximity thresholds should be in the range [0-2047]
*/

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

import com.revrobotics.ColorSensorV3;

public class RevColorSensor implements Sendable {
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

  private static ShuffleboardTab tab = Shuffleboard.getTab("Color Sensors");
  private static int idGen = 0;

  public RevColorSensor(int redLowThresh, int redHighThresh, int greenLowThresh,
                        int greenHighThresh, int blueLowThresh, int blueHighThresh,
                        int proxLowThresh, int proxHighThresh) {
    this(redLowThresh, redHighThresh, greenLowThresh, greenHighThresh, blueLowThresh, blueHighThresh, proxLowThresh, proxHighThresh, null);
  }

  public RevColorSensor(int redLowThresh, int redHighThresh, int greenLowThresh,
                        int greenHighThresh, int blueLowThresh, int blueHighThresh,
                        int proxLowThresh, int proxHighThresh, String name) {
    this.redLowThresh    = redLowThresh;
    this.redHighThresh   = redHighThresh;
    this.greenLowThresh  = greenLowThresh;
    this.greenHighThresh = greenHighThresh;
    this.blueLowThresh   = blueLowThresh;
    this.blueHighThresh  = blueHighThresh;
    this.proxLowThresh   = proxLowThresh;
    this.proxHighThresh  = proxHighThresh;

    // TODO: Should the ids start at 0 or 1?
    tab.add(name != null ? name : ("ID: " + idGen), this);
    idGen++;
  }

  public boolean isTarget() {
    return (isRedThresholdMet() && isGreenThresholdMet() && isBlueThresholdMet() &&
            isProximityMet());
  }

  public boolean isRedThresholdMet() {
    return (getRed() > redLowThresh && getRed() < redHighThresh);
  }

  public boolean isGreenThresholdMet() {
    return (getGreen() > greenLowThresh && getGreen() < greenHighThresh);
  }

  public boolean isBlueThresholdMet() {
    return (getBlue() > blueLowThresh && getBlue() < blueHighThresh);
  }

  public boolean isProximityMet() {
    return (getProximity() > proxLowThresh && getProximity() < proxHighThresh);
  }

  public int getRed() { return colorSensor.getRed(); }

  public int getGreen() { return colorSensor.getGreen(); }

  public int getBlue() { return colorSensor.getBlue(); }

  public double getIR() { return colorSensor.getIR(); }

  public int getProximity() { return colorSensor.getProximity(); }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("Red Threshold Low",        () -> this.redLowThresh,    (val) -> this.redLowThresh    = (int)val);
    builder.addDoubleProperty("Red Threshold High",       () -> this.redHighThresh,   (val) -> this.redHighThresh   = (int)val);
    builder.addDoubleProperty("Blue Threshold Low",       () -> this.blueLowThresh,   (val) -> this.blueLowThresh   = (int)val);
    builder.addDoubleProperty("Blue Threshold High",      () -> this.blueHighThresh,  (val) -> this.blueHighThresh  = (int)val);
    builder.addDoubleProperty("Green Threshold Low",      () -> this.greenLowThresh,  (val) -> this.greenLowThresh  = (int)val);
    builder.addDoubleProperty("Green Threshold High",     () -> this.greenHighThresh, (val) -> this.greenHighThresh = (int)val);
    builder.addDoubleProperty("Proximity Threshold Low",  () -> this.proxLowThresh,   (val) -> this.proxLowThresh   = (int)val);
    builder.addDoubleProperty("Proximity Threshold High", () -> this.proxHighThresh,  (val) -> this.proxHighThresh  = (int)val);

    builder.addDoubleProperty("Red",       this::getRed,       null);
    builder.addDoubleProperty("Blue",      this::getBlue,      null);
    builder.addDoubleProperty("Green",     this::getGreen,     null);
    builder.addDoubleProperty("Proximity", this::getProximity, null);
  }
}
