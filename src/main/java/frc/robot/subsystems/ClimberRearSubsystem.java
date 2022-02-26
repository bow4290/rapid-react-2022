package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class ClimberRearSubsystem extends SubsystemBase {
  private WPI_TalonFX elevatorClimbMotor = new WPI_TalonFX(ElevatorConstants.elevatorClimbMotorChannel);
  private CANSparkMax elevatorPivotMotor = new CANSparkMax(ElevatorConstants.elevatorPivotMotorChannel, MotorType.kBrushless);
 
  // private final DoubleSolenoid rearClimberSolenoid;
  // public enum RearClimberStatus { EXTENDED, RETRACTED }
  // public static RearClimberStatus rearClimberStatus = RearClimberStatus.RETRACTED;

  public ClimberRearSubsystem() { 
    elevatorClimbMotor.setNeutralMode(NeutralMode.Brake);
    elevatorClimbMotor.setInverted(TalonFXInvertType.Clockwise);

    elevatorPivotMotor.setIdleMode(IdleMode.kBrake);
    elevatorPivotMotor.setInverted(false);
    elevatorPivotMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
    elevatorPivotMotor.enableSoftLimit(SoftLimitDirection.kReverse, true);

    // rearClimberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ClimberConstants.rearClimberSolenoidUpChannel, ClimberConstants.rearClimberSolenoidDownChannel);
  }

  public void extendElevator(double climbSpeed){

  }

  public void retractElevator(double climbSpeed){

  }

  public void stopElevator(double climbSpeed){

  }

  public void pivotElevator(double pivotSpeed){
    elevatorPivotMotor.setSoftLimit(SoftLimitDirection.kForward, ElevatorConstants.pivotSoftLimit);
    elevatorPivotMotor.setSoftLimit(SoftLimitDirection.kReverse, ElevatorConstants.pivotSoftLimit);

    elevatorPivotMotor.set(pivotSpeed);
  }

  public void stopPivotElevator(){
    elevatorPivotMotor.set(0);
  }


  // public void extendRearClimber(double climberSpeed) { rearClimberMotorChannel.set(climberSpeed); }

  // public void extendRearClimberSolenoid() {
    // rearClimberSolenoid.set(DoubleSolenoid.Value.kForward);
    // rearClimberStatus = RearClimberStatus.EXTENDED;
  // }

  // public void retractRearClimber(double climberSpeed) { 
    // rearClimberMotorChannel.set(-climberSpeed); 
  // }

  // public void retractRearClimberSolenoid() {
  //   rearClimberSolenoid.set(DoubleSolenoid.Value.kReverse);
  //   rearClimberStatus = RearClimberStatus.RETRACTED;
  // }

  // public void stopRearClimber() { 
    // rearClimberMotorChannel.set(0); 
  // }

  // public static RearClimberStatus getRearClimberPosition() { return rearClimberStatus; }

  @Override
  public void periodic() {
    // SmartDashboard.putString("Rear Climber Status", rearClimberStatus == RearClimberStatus.RETRACTED ? "RETRACTED" : "EXTENDED");
  }
}
