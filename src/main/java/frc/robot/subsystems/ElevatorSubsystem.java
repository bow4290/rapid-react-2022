package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.Flags;

public class ElevatorSubsystem extends SubsystemBase {
  private WPI_TalonFX elevatorClimbMotor = new WPI_TalonFX(ElevatorConstants.elevatorClimbMotorChannel);
  private CANSparkMax traverseClimbMotor;
  private final DoubleSolenoid climberSolenoid;

  public enum PistonStatus { UP, DOWN }
  public static PistonStatus pistonStatus;
  public ElevatorSubsystem() {
    if (!Flags.elevator) throw new Error("Elevator flag must be set to create an ElevatorSubsystem!");
    elevatorClimbMotor.setNeutralMode(NeutralMode.Brake);
    elevatorClimbMotor.setInverted(TalonFXInvertType.CounterClockwise);

    elevatorClimbMotor.configForwardSoftLimitEnable(true);
    elevatorClimbMotor.configReverseSoftLimitEnable(true);
    elevatorClimbMotor.configForwardSoftLimitThreshold(ElevatorConstants.elevatorForwardSoftLimit);
    elevatorClimbMotor.configReverseSoftLimitThreshold(ElevatorConstants.elevatorReverseSoftLimit);

    elevatorClimbMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    elevatorClimbMotor.setSelectedSensorPosition(0);
    
    traverseClimbMotor = new CANSparkMax(ElevatorConstants.traverseClimbMotorChannel, MotorType.kBrushless);
    traverseClimbMotor.restoreFactoryDefaults();

    traverseClimbMotor.setInverted(false);
    traverseClimbMotor.setIdleMode(IdleMode.kBrake);

    traverseClimbMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    traverseClimbMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    traverseClimbMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, ElevatorConstants.armForwardSoftLimit);
    traverseClimbMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, ElevatorConstants.armReverseSoftLimit);
    
    climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ElevatorConstants.armUpChannel, ElevatorConstants.armDownChannel);
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

  public void retractArm() {
    traverseClimbMotor.set(-ElevatorConstants.travArmSpeed);
  }

  public void extendArm() {
    traverseClimbMotor.set(ElevatorConstants.travArmSpeed);
  }

  public void extendPiston() {
    climberSolenoid.set(DoubleSolenoid.Value.kForward);
    pistonStatus = PistonStatus.UP;
  }

  public void retractPiston() {
    climberSolenoid.set(DoubleSolenoid.Value.kReverse);
    pistonStatus = PistonStatus.DOWN;  
  }

  public double getElevatorEncoderposition(){
    return elevatorClimbMotor.getSelectedSensorPosition();
  }

  public static PistonStatus getPistonStatus(){
    return pistonStatus;
  }

  @Override
  public void periodic() {
  }
}
