package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class IndexerSubsystem extends SubsystemBase {

  private WPI_VictorSPX upperIndexMotor;
  private WPI_VictorSPX lowerIndexMotor;

  public IndexerSubsystem(){
    upperIndexMotor = new WPI_VictorSPX(IndexerConstants.upperIndexMotorChannel);
    lowerIndexMotor = new WPI_VictorSPX(IndexerConstants.lowerIndexMotorChannel);
  }

  public void turnBothIndexMotors(double upperIndexSpeed, double lowerIndexSpeed){
    upperIndexMotor.set(ControlMode.PercentOutput, upperIndexSpeed);
    lowerIndexMotor.set(ControlMode.PercentOutput, lowerIndexSpeed);
  }

  public void turnUpperIndexMotor(double upperIndexSpeed){
    upperIndexMotor.set(ControlMode.PercentOutput, upperIndexSpeed);
  }
  
  //FIXME: are the methods above and below redundant?
  public void turnLowerIndexMotor(double lowerIndexSpeed){
    lowerIndexMotor.set(ControlMode.PercentOutput, lowerIndexSpeed);
  }

  @Override
  public void periodic() {
  }
}
