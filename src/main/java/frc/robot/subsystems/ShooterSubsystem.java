package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private WPI_TalonFX shooterMotor1 = new WPI_TalonFX(ShooterConstants.shooterMotor1Channel);
  private WPI_TalonFX shooterMotor2 = new WPI_TalonFX(ShooterConstants.shooterMotor2Channel);

  public ShooterSubsystem() {
    shooterMotor1.setInverted(TalonFXInvertType.Clockwise);

    shooterMotor1.setNeutralMode(NeutralMode.Coast);
    shooterMotor2.setNeutralMode(NeutralMode.Coast);

    shooterMotor1.configVoltageCompSaturation(ShooterConstants.shooterMotorVoltage);
    shooterMotor2.configVoltageCompSaturation(ShooterConstants.shooterMotorVoltage);

    shooterMotor1.enableVoltageCompensation(true);
    shooterMotor2.enableVoltageCompensation(true);

    shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    shooterMotor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

    shooterMotor1.config_kF(0, ShooterConstants.kF);
    shooterMotor1.config_kP(0, ShooterConstants.kP);
    shooterMotor1.config_kI(0, ShooterConstants.kI);
    shooterMotor1.config_kD(0, ShooterConstants.kD);

    shooterMotor2.follow(shooterMotor1);

    shooterMotor2.setInverted(InvertType.OpposeMaster);
  }

  public void shoot(double shooterSpeedRPM) {
    // V = Pulses / 100 ms => 1000 ms / 1 s * 60 s / 1 min * 1 rev / 2048 pulses
    double velocityPer100ms = (shooterSpeedRPM*2048)/600;
    shooterMotor1.set(ControlMode.Velocity, velocityPer100ms);
  }

  private double getShooterVelocityRaw() { return shooterMotor1.getSelectedSensorVelocity(); }

  public double getShooterRPM() { return getShooterVelocityRaw()*600/2048; }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shooter Motors RPM", getShooterRPM());
  }
}
