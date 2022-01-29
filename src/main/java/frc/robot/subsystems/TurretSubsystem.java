package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.FaultID;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {

  private CANSparkMax motor;
  private RelativeEncoder encoder;
  private SparkMaxPIDController pid;

  private double kP, kI, kD, kF;

  public TurretSubsystem() {
    motor = new CANSparkMax(TurretConstants.deviceID, MotorType.kBrushless);
    motor.restoreFactoryDefaults();

    motor.setInverted(true); // Not sure which orientation we want
    motor.setIdleMode(IdleMode.kBrake);
    motor.enableVoltageCompensation(11.0);

    // Internal limit switches based on encoder position
    motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, TurretConstants.forwardRotations);
    motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, TurretConstants.reverseRotations);

    encoder = motor.getEncoder();

    // TODO: Configure constants
    // F-PID
    kF = 0.0;
    kP = 0.1;
    kI = 0.0001;
    kD = 1.0;

    pid.setFF(kF);
    pid.setP(kP);
    pid.setI(kI);
    pid.setD(kD);
    pid.setOutputRange(-1.0, 1.0);
  }

  public void turn(double rotations) {
    // TODO: Angle conversion
    pid.setReference(rotations, CANSparkMax.ControlType.kPosition);
  }

  public boolean getHitLeftLimitSwitch() {
    return motor.getFault(FaultID.kSoftLimitRev);
  }

  public boolean getHitRightLimitSwitch() {
    return motor.getFault(FaultID.kSoftLimitFwd);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Turret Pos:", encoder.getPosition());
  }
}
