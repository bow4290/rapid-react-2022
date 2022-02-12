package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberFrontSubsystem extends SubsystemBase {
  private CANSparkMax frontClimberMotorChannel = new CANSparkMax(ClimberConstants.frontClimberMotorChannel, MotorType.kBrushless);
  
  public ClimberFrontSubsystem() { frontClimberMotorChannel.setInverted(false); }

  public void extendFrontClimber(double climberSpeed) { frontClimberMotorChannel.set(climberSpeed); }

  public void retractFrontClimber(double climberSpeed) { frontClimberMotorChannel.set(-climberSpeed); }

  public void stopFrontClimber() { frontClimberMotorChannel.set(0); }

  @Override
  public void periodic() {
  }
}
