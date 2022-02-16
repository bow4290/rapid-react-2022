package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimberFrontSubsystem extends SubsystemBase {
  private CANSparkMax frontClimberMotorChannel = new CANSparkMax(ClimberConstants.frontClimberMotorChannel, MotorType.kBrushless);
  public enum FrontClimberStatus { EXTENDED, RETRACTED }
  public static FrontClimberStatus frontClimberStatus = FrontClimberStatus.RETRACTED;

  public ClimberFrontSubsystem() { frontClimberMotorChannel.setInverted(false); }

  public void extendFrontClimber(double climberSpeed) { 
    frontClimberMotorChannel.set(climberSpeed);
    frontClimberStatus = FrontClimberStatus.EXTENDED;
  }

  public void retractFrontClimber(double climberSpeed) { frontClimberMotorChannel.set(-climberSpeed);
    frontClimberStatus = FrontClimberStatus.RETRACTED;
  }

  public void stopFrontClimber() { frontClimberMotorChannel.set(0); }

  public static FrontClimberStatus getFrontClimberStatus() { return frontClimberStatus; }

  @Override
  public void periodic() {
    SmartDashboard.putString("Front Climber Status", frontClimberStatus  == FrontClimberStatus.RETRACTED ? "RETRACTED"  : "EXTRENDED");
  }
}
