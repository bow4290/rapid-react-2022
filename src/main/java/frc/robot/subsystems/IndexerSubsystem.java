package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;

public class IndexerSubsystem extends SubsystemBase {

  private WPI_VictorSPX leftIndexerMotor;
  private WPI_VictorSPX rightIndexerMotor;

  public IndexerSubsystem() {
    leftIndexerMotor = new WPI_VictorSPX(IndexerConstants.leftIndexerMotorChannel);
    rightIndexerMotor = new WPI_VictorSPX(IndexerConstants.rightIndexerMotorChannel);
    leftIndexerMotor.setInverted(true);

  }

  public void indexBall(double indexLeftSpeed, double indexRightSpeed){
    leftIndexerMotor.set(ControlMode.PercentOutput, indexLeftSpeed);
    rightIndexerMotor.set(ControlMode.PercentOutput, indexRightSpeed);
  }
  @Override
  public void periodic() {
  }
}
