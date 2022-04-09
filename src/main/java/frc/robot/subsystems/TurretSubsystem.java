package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StickyFaults;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import frc.robot.Constants.Flags;
import frc.robot.Constants.TurretConstants;
import frc.robot.sensors.Limelight;

public class TurretSubsystem extends SubsystemBase {
  // TODO: encasulate?
  public WPI_TalonFX motor;
  private Limelight limelight;
  public boolean isTurretStopped = false;
  public double homingPosition = 0;
  // private SparkMaxPIDController pid;

  // private double kP, kI, kD, kF, kMaxOutput, kMinOutput, setpoint;

  public TurretSubsystem(Limelight limelight) {
    if (!Flags.turret) throw new Error("Turret flag must be set to create a TurretSubsystem!");

    this.limelight = limelight;

    motor = new WPI_TalonFX(TurretConstants.deviceID);
    motor.configFactoryDefault();

    motor.setInverted(false);
    motor.setNeutralMode(NeutralMode.Brake);
    motor.configVoltageCompSaturation(11);
    motor.enableVoltageCompensation(true);

    motor.configForwardSoftLimitEnable(true);
    motor.configReverseSoftLimitEnable(true);

    motor.configForwardSoftLimitThreshold(TurretConstants.forwardRotations);
    motor.configReverseSoftLimitThreshold(TurretConstants.reverseRotations);
    // motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    // motor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    // motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, TurretConstants.forwardRotations);
    // motor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, TurretConstants.reverseRotations);

    // pid = motor.getPIDController();

    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    motor.setSelectedSensorPosition(0);
  }

  public void turnTurret(double speed){
    motor.set(speed);
  }
  public void stopTurret(){
    motor.set(0);
  }

  public boolean getHitLeftLimitSwitch() {
    StickyFaults faults = new StickyFaults();
    motor.getStickyFaults(faults);
    if (faults.ReverseLimitSwitch) {
      motor.clearStickyFaults();
      return true;
    }
    return false;
  }

  public boolean getHitRightLimitSwitch() {
    StickyFaults faults = new StickyFaults();
    motor.getStickyFaults(faults);
    if (faults.ForwardLimitSwitch) {
      motor.clearStickyFaults();
      return true;
    }
    return false;
  }

  public boolean isTurretReady(){
    return (limelight.isTarget() && (Math.abs(limelight.getXErrorWithOffset(TurretConstants.aimOffsetDistance)) < 3)); // was 2.5
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Is Turret Ready? ", isTurretReady());
    SmartDashboard.putBoolean("Has a target? ", limelight.isTarget());
    SmartDashboard.putNumber("X Error ", limelight.getXErrorWithOffset(TurretConstants.aimOffsetDistance));
    SmartDashboard.putNumber("Y Error ", limelight.getYError());
    SmartDashboard.putNumber("Distance ", limelight.getDistance());
  }
}
