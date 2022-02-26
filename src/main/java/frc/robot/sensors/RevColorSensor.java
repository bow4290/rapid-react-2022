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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.ColorSensorV3;

public class RevColorSensor implements Sendable {
  private final ColorSensorV3 colorSensor;

  private double redLowThresh;
  private double redHighThresh;
  private double greenLowThresh;
  private double greenHighThresh;
  private double blueLowThresh;
  private double blueHighThresh;
  private int proxLowThresh;
  private int proxHighThresh;

  private static int idGen = 0;
  private int id = ++idGen;

  public RevColorSensor(double redLowThresh, double redHighThresh, double greenLowThresh,
                        double greenHighThresh, double blueLowThresh, double blueHighThresh,
                        int proxLowThresh, int proxHighThresh, Boolean onboard) {
    this.redLowThresh    = redLowThresh;
    this.redHighThresh   = redHighThresh;
    this.greenLowThresh  = greenLowThresh;
    this.greenHighThresh = greenHighThresh;
    this.blueLowThresh   = blueLowThresh;
    this.blueHighThresh  = blueHighThresh;
    this.proxLowThresh   = proxLowThresh;
    this.proxHighThresh  = proxHighThresh;

    colorSensor = new ColorSensorV3(onboard ? I2C.Port.kOnboard : I2C.Port.kMXP);
  }

  public void update() {
    SmartDashboard.putNumber("R" + id, getRed());
    SmartDashboard.putNumber("G" + id, getGreen());
    SmartDashboard.putNumber("B" + id, getBlue());
    SmartDashboard.putNumber("Prox" + id, getProximity());
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

  public double getRed() { return colorSensor.getColor().red; }

  public double getGreen() { return colorSensor.getColor().green; }

  public double getBlue() { return colorSensor.getColor().blue; }

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
