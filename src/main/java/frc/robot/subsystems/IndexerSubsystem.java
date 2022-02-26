package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Flags;
import frc.robot.Constants.IndexerConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.RevColorSensor;

public class IndexerSubsystem extends SubsystemBase {
  public static ShuffleboardTab tab = null;

  private WPI_VictorSPX upperIndexMotor;
  private WPI_VictorSPX lowerIndexMotor;

  private BallIdentification down;
  private BallIdentification up;

  public IndexerSubsystem(BallIdentification upperColorSensor, BallIdentification lowerColorSensor){
    if (!Flags.indexer) throw new Error("Indexer flag must be set to create an IndexerSubsystem!");
    tab = Shuffleboard.getTab("Indexer");
    up = upperColorSensor;
    down = lowerColorSensor;
    // tab.add("Upper motor speed is:", upperIndexMotor);
    // tab.add("Lower motor speed is:", lowerIndexMotor);
    // tab.add("Lower color sensor", lowerColorSensor);
    // tab.add("Upper color sensor", upperColorSensor);
    
    upperIndexMotor = new WPI_VictorSPX(IndexerConstants.upperIndexMotorChannel);
    lowerIndexMotor = new WPI_VictorSPX(IndexerConstants.lowerIndexMotorChannel);

    upperIndexMotor.setInverted(false);
    lowerIndexMotor.setInverted(true);

    upperIndexMotor.configVoltageCompSaturation(11);
    lowerIndexMotor.configVoltageCompSaturation(11);
    upperIndexMotor.enableVoltageCompensation(true);
    lowerIndexMotor.enableVoltageCompensation(true);
  }

  public void turnBothIndexMotors(double indexSpeed){
    turnUpperIndexMotor(indexSpeed);
    turnLowerIndexMotor(indexSpeed);
  }

  public void turnUpperIndexMotor(double upperIndexSpeed){
    upperIndexMotor.set(ControlMode.PercentOutput, upperIndexSpeed);
  }

  public void turnLowerIndexMotor(double lowerIndexSpeed){
    lowerIndexMotor.set(ControlMode.PercentOutput, lowerIndexSpeed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Low", down.getBallColor().toString());
    SmartDashboard.putString("Upper", up.getBallColor().toString());
    SmartDashboard.putBoolean("Lowp", down.isBallPresent());
    SmartDashboard.putBoolean("Upperp", up.isBallPresent());
    down.update();
    up.update();
  }
}
