package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.FaultID;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.Flags;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {

  private CANSparkMax motor;
  public RelativeEncoder encoder;
  // private SparkMaxPIDController pid;

  // private double kP, kI, kD, kF, kMaxOutput, kMinOutput, setpoint;

  public TurretSubsystem() {
    if (!Flags.turret) throw new Error("Turret flag must be set to create a TurretSubsystem!");

    motor = new CANSparkMax(TurretConstants.deviceID, MotorType.kBrushless);
    motor.restoreFactoryDefaults();

    motor.setInverted(false);
    motor.setIdleMode(IdleMode.kBrake);
    motor.enableVoltageCompensation(11.0);

    // Internal limit switches based on encoder position
    motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, TurretConstants.forwardRotations);
    motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, TurretConstants.reverseRotations);

    // pid = motor.getPIDController();

    encoder = motor.getEncoder();
    encoder.setPosition(0);

    // TODO: Configure constants
    // TODO: there's 320 teeth on the turret gear btw. 
    // F-PID
    // kF = 0.05;
    // kP = 0.01;
    // kI = 0.0;
    // kD = 0.0;
    // kMaxOutput = 1.0;
    // kMinOutput = -1.0;

    // pid.setFF(kF);
    // pid.setP(kP);
    // pid.setI(kI);
    // pid.setD(kD);
    // pid.setOutputRange(kMinOutput, kMaxOutput);

    // SmartDashboard.putNumber("P Gain", kP);
    // SmartDashboard.putNumber("I Gain", kI);
    // SmartDashboard.putNumber("D Gain", kD);
    // SmartDashboard.putNumber("Feed Forward", kF);
    // SmartDashboard.putNumber("Max Output", kMaxOutput);
    // SmartDashboard.putNumber("Min Output", kMinOutput);
  }

  // public void turn(double setpoint) {
  //   setSetPoint(setpoint);
  //   motor.set(setpoint);
    //pid.setReference(setpoint, CANSparkMax.ControlType.kVelocity);
  // }

  public void turnManual(double speed){
    motor.set(speed);
  }

  public boolean getHitLeftLimitSwitch() {
    return motor.getFault(FaultID.kSoftLimitRev);
  }

  public boolean getHitRightLimitSwitch() {
    return motor.getFault(FaultID.kSoftLimitFwd);
  }

  // public double getSetPoint() {
    // return setpoint;
  // }

  // public void setSetPoint(double setpoint) {
    // this.setpoint = setpoint;
  // }

  @Override
  public void periodic() {
    // double p = SmartDashboard.getNumber("P Gain", 0);
    // double i = SmartDashboard.getNumber("I Gain", 0);
    // double d = SmartDashboard.getNumber("D Gain", 0);
    // double ff = SmartDashboard.getNumber("Feed Forward", 0);
    // double max = SmartDashboard.getNumber("Max Output", 0);
    // double min = SmartDashboard.getNumber("Min Output", 0);

    // if((p != kP)) { pid.setP(p); kP = p; }
    // if((i != kI)) { pid.setI(i); kI = i; }
    // if((d != kD)) { pid.setD(d); kD = d; }
    // if((ff != kF)) { pid.setFF(ff); kF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   pid.setOutputRange(min, max); 
    //   kMinOutput = min; kMaxOutput = max; 
    // }
    // SmartDashboard.putNumber("SetPoint", setpoint);
    // SmartDashboard.putNumber("ProcessVariable", encoder.getVelocity());
  }
}
