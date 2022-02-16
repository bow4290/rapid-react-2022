package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class ClimberRearSubsystem extends SubsystemBase {
  private CANSparkMax rearClimberMotorChannel = new CANSparkMax(ClimberConstants.rearClimberMotorChannel, MotorType.kBrushless);
  private final DoubleSolenoid rearClimberSolenoid;
  public enum ClimberStatus { EXTENDED, RETRACTED }
  public static ClimberStatus climberStatus = ClimberStatus.RETRACTED;

  public ClimberRearSubsystem() { 
    rearClimberMotorChannel.setInverted(false);
    rearClimberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ClimberConstants.rearClimberSolenoidUpChannel, ClimberConstants.rearClimberSolenoidDownChannel);
  }

  public void extendRearClimber(double climberSpeed) { rearClimberMotorChannel.set(climberSpeed); }

  public void extendRearClimberSolenoid() {
    rearClimberSolenoid.set(DoubleSolenoid.Value.kForward);
    climberStatus = ClimberStatus.EXTENDED;
  }

  public void retractRearClimber(double climberSpeed) { rearClimberMotorChannel.set(-climberSpeed); }

  public void retractRearClimberSolenoid() {
    rearClimberSolenoid.set(DoubleSolenoid.Value.kReverse);
    climberStatus = ClimberStatus.RETRACTED;
  }

  public void stopRearClimber() { rearClimberMotorChannel.set(0); }

  public static ClimberStatus getRearClimberPosition() { return climberStatus; }

  @Override
  public void periodic() {
    SmartDashboard.putString("Rear Climber Status", climberStatus == ClimberStatus.RETRACTED ? "RETRACTED" : "EXTENDED");
  }
}
