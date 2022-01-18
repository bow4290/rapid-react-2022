package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final WPI_VictorSPX intakeMotor;
  private final DoubleSolenoid intakeSolenoid;

  public enum IntakeStatus{
    DOWN, UP
  }
  public static IntakeStatus intakeStatus;

  public IntakeSubsystem() {
    intakeMotor = new WPI_VictorSPX(IntakeConstants.intakeMotorChannel);
    intakeSolenoid = new DoubleSolenoid(IntakeConstants.intakeUpChannel, IntakeConstants.intakeDownChannel);
  
    intakeMotor.setInverted(false);
    intakeStatus = IntakeStatus.UP;
  }

  public void retractIntake(){
    intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    intakeStatus = IntakeStatus.UP;
  }

  public void extendIntake(){
    intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    intakeStatus = IntakeStatus.DOWN;
  }

  public void intakeIn(double intakeSpeed){
    intakeMotor.set(ControlMode.PercentOutput, intakeSpeed);
  }

  public void intakeStop(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
  }

  public static IntakeStatus getIntakePosition(){
    return intakeStatus;
  }

  @Override
  public void periodic() {
    // TODO: smartboard
  }
}
