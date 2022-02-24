package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.RevColorSensor;

public class IndexerSubsystem extends SubsystemBase {
  public static ShuffleboardTab tab = null;

  private WPI_VictorSPX upperIndexMotor;
  private WPI_VictorSPX lowerIndexMotor;

  public IndexerSubsystem(BallIdentification upperColorSensor, BallIdentification lowerColorSensor){
    tab = Shuffleboard.getTab("Indexer");
    tab.add("Upper motor speed is:", upperIndexMotor);
    tab.add("Lower motor speed is:", lowerIndexMotor);
    tab.add("Upper color sensor", upperColorSensor);
    tab.add("Lower color sensor", lowerColorSensor);
    
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
  }
}
