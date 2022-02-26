package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TelescopeConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TelescopeSubsystem extends SubsystemBase {
  private CANSparkMax telescopeMotorChannel = new CANSparkMax(TelescopeConstants.telescopeMotorChannel, MotorType.kBrushless);
  public enum TelescopeStatus { EXTENDED, RETRACTED }
  public static TelescopeStatus telescopeStatus = TelescopeStatus.RETRACTED;

  public TelescopeSubsystem() { 
    telescopeMotorChannel.setIdleMode(IdleMode.kBrake);
    telescopeMotorChannel.setInverted(false);  

    telescopeMotorChannel.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    telescopeMotorChannel.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    telescopeMotorChannel.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, TelescopeConstants.telescopeExtendSoftLimit);
    telescopeMotorChannel.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, TelescopeConstants.telescopeRetractSoftLimit);
   }

  public void extendTelescope(double climberSpeed) { 
    telescopeMotorChannel.set(climberSpeed);
    telescopeStatus = TelescopeStatus.EXTENDED;
  }

  public void retractTelescope(double climberSpeed) { telescopeMotorChannel.set(-climberSpeed);
    telescopeStatus = TelescopeStatus.RETRACTED;
  }

  public void stopTelescope() { telescopeMotorChannel.set(0); }

  public static TelescopeStatus gettelescopeStatus() { return telescopeStatus; }

  @Override
  public void periodic() {
    SmartDashboard.putString("Front Climber Status", telescopeStatus  == TelescopeStatus.RETRACTED ? "RETRACTED"  : "EXTRENDED");
  }
}
