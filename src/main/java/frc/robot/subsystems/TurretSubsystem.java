package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.FaultID;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.Flags;
import frc.robot.Constants.TurretConstants;
import frc.robot.sensors.TargetTracker;

public class TurretSubsystem extends SubsystemBase {
  private CANSparkMax motor;
  public RelativeEncoder encoder;
  private TargetTracker targetTracker;
  public boolean isTurretStopped = false;
  // private SparkMaxPIDController pid;

  // private double kP, kI, kD, kF, kMaxOutput, kMinOutput, setpoint;

  public TurretSubsystem(TargetTracker targetTracker) {
    if (!Flags.turret) throw new Error("Turret flag must be set to create a TurretSubsystem!");

    this.targetTracker = targetTracker;

    motor = new CANSparkMax(TurretConstants.deviceID, MotorType.kBrushless);
    motor.restoreFactoryDefaults();

    motor.setInverted(false);
    motor.setIdleMode(IdleMode.kBrake);
    motor.enableVoltageCompensation(11.0);

    motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, TurretConstants.forwardRotations);
    motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, TurretConstants.reverseRotations);

    // pid = motor.getPIDController();

    encoder = motor.getEncoder();
    encoder.setPosition(0);
  }

  public void turnTurret(double speed){
    motor.set(speed);
  }
  public void stopTurret(){
    motor.set(0);
  }

  public boolean getHitLeftLimitSwitch() {
    return motor.getFault(FaultID.kSoftLimitRev);
  }

  public boolean getHitRightLimitSwitch() {
    return motor.getFault(FaultID.kSoftLimitFwd);
  }

  public boolean isTurretReady(){
    return (targetTracker.hasTarget() && (Math.abs(targetTracker.getXErrorWithOffset(TurretConstants.aimOffsetDistance)) < 3)); // was 2.5
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Is Turret Ready? ", isTurretReady());
    SmartDashboard.putBoolean("Has a target? ", targetTracker.hasTarget());
    SmartDashboard.putNumber("X Error ", targetTracker.getXErrorWithOffset(TurretConstants.aimOffsetDistance));
    // SmartDashboard.putNumber("Y Error ", targetTracker.getYError());
    SmartDashboard.putNumber("Distance ", targetTracker.getDistance());
  }
}
