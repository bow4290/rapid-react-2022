package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  private WPI_TalonFX elevatorClimbMotor = new WPI_TalonFX(ElevatorConstants.elevatorClimbMotorChannel);
  // private final DoubleSolenoid elevatorSolenoid;

  // public enum ElevatorStatus { LOCKED, UNLOCKED }
  // public static ElevatorStatus elevatorStatus;
  public ElevatorSubsystem() {
    elevatorClimbMotor.setNeutralMode(NeutralMode.Brake);
    elevatorClimbMotor.setInverted(TalonFXInvertType.CounterClockwise);

    elevatorClimbMotor.configForwardSoftLimitEnable(true);
    elevatorClimbMotor.configReverseSoftLimitEnable(true);

    elevatorClimbMotor.configForwardSoftLimitThreshold(ElevatorConstants.forwardSoftLimit);
    elevatorClimbMotor.configReverseSoftLimitThreshold(ElevatorConstants.reverseSoftLimit);

    elevatorClimbMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    elevatorClimbMotor.setSelectedSensorPosition(0);
    // elevatorSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ElevatorConstants.elevatorLockChannel, ElevatorConstants.elevatorUnlockChannel);
    // elevatorStatus = ElevatorStatus.UNLOCKED;
  }

  public void extendElevator(double climbSpeed){
    elevatorClimbMotor.set(ControlMode.PercentOutput, ElevatorConstants.elevatorSpeed);
  }

  public void retractElevator(double climbSpeed){
    elevatorClimbMotor.set(ControlMode.PercentOutput, -ElevatorConstants.elevatorSpeed);
  }

  public void stopElevator(){
    elevatorClimbMotor.set(ControlMode.PercentOutput, 0);
  }

  public double getEncoderposition(){
    double position = elevatorClimbMotor.getSelectedSensorPosition();
    return position;
  }

  // public void lockElevator(boolean isElevatorLocked){
  //   if (isElevatorLocked == true){
  //     elevatorSolenoid.set(DoubleSolenoid.Value.kForward);
  //     elevatorStatus = elevatorStatus.LOCKED;
  //   } else {
  //     elevatorSolenoid.set(DoubleSolenoid.Value.kReverse);
  //     elevatorStatus = elevatorStatus.UNLOCKED;
  //   }
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // SmartDashboard.putString("elevator locked?", elevatorStatus.toString());
  }
}
