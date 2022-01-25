package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_TalonFX shooterMotor = new WPI_TalonFX(ShooterConstants.shooterMotorChannel);

  public ShooterSubsystem() {
    shooterMotor.setInverted(TalonFXInvertType.Clockwise);

    shooterMotor.setNeutralMode(NeutralMode.Coast);

    shooterMotor.configVoltageCompSaturation(ShooterConstants.shooterMotorVoltage);

    shooterMotor.enableVoltageCompensation(true);

    shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

    shooterMotor.config_kF(0, ShooterConstants.kF);
    shooterMotor.config_kP(0, ShooterConstants.kP);
    shooterMotor.config_kI(0, ShooterConstants.kI);
    shooterMotor.config_kD(0, ShooterConstants.kD);
  }

  public void shoot(double shooterSpeedRPM) {
    // V = Pulses / 100 ms => 1000 ms / 1 s * 60 s / 1 min * 1 rev / 2048 pulses
    double velocityPer100ms = (shooterSpeedRPM*2048)/600;
    shooterMotor.set(ControlMode.Velocity, velocityPer100ms);
  }

  private double getShooterVelocityRaw() { return shooterMotor.getSelectedSensorVelocity(); }

  public double getShooterRPM() { return getShooterVelocityRaw()*600/2048; }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shooter Motors RPM", getShooterRPM());
  }
}
