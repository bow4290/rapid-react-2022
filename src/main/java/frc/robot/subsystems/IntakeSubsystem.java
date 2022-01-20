package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final WPI_VictorSPX intakeMotor;
  private final DoubleSolenoid intakeSolenoid;

  public enum IntakeStatus { DOWN, UP }
  public static IntakeStatus intakeStatus;

  public IntakeSubsystem() {
    intakeMotor = new WPI_VictorSPX(IntakeConstants.intakeMotorChannel);
    intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.intakeUpChannel, IntakeConstants.intakeDownChannel);

    intakeMotor.setInverted(false);
    intakeStatus = IntakeStatus.UP;
  }

  public void retractIntake() {
    intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    intakeStatus = IntakeStatus.UP;
  }

  public void extendIntake() {
    intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    intakeStatus = IntakeStatus.DOWN;
  }

  public void intakeSpin(double intakeSpeed) {
    intakeMotor.set(ControlMode.PercentOutput, intakeSpeed);
  }

  public static IntakeStatus getIntakePosition() { return intakeStatus; }

  @Override
  public void periodic() {
    // TODO: Should we also display the current intake speed? (maybe in intakeSpin)
    SmartDashboard.putString("Intake Status", intakeStatus == IntakeStatus.UP ? "Up" : "Down");
  }
}
