package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class ClimberRearSubsystem extends SubsystemBase {
  private CANSparkMax rearClimberMotorChannel = new CANSparkMax(DriveConstants.rearClimberMotorChannel, MotorType.kBrushless);
  
  public ClimberRearSubsystem() { rearClimberMotorChannel.setInverted(false); }

  public void extendRearClimber(double climberSpeed) { rearClimberMotorChannel.set(climberSpeed); }

  public void retractRearClimber(double climberSpeed) { rearClimberMotorChannel.set(-climberSpeed); }

  public void stopRearClimber() { rearClimberMotorChannel.set(0); }

  @Override
  public void periodic() {
  }
}
