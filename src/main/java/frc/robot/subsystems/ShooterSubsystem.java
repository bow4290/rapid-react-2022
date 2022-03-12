package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  public static WPI_TalonFX shooterMotor = new WPI_TalonFX(ShooterConstants.shooterMotorChannel);
  public static ShuffleboardTab tab = Shuffleboard.getTab("Shooter");

  double RPMSpeed = 6000;
  double targetSpeed = 0;

  public ShooterSubsystem() {
    shooterMotor.configFactoryDefault();
    shooterMotor.setInverted(TalonFXInvertType.Clockwise);
    shooterMotor.setNeutralMode(NeutralMode.Coast);
    shooterMotor.configVoltageCompSaturation(ShooterConstants.shooterMotorVoltage);
    shooterMotor.enableVoltageCompensation(true);
    shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    shooterMotor.configOpenloopRamp(0.3);

    shooterMotor.config_kF(0, ShooterConstants.kF);
    shooterMotor.config_kP(0, ShooterConstants.kP);
    shooterMotor.config_kI(0, ShooterConstants.kI);
    shooterMotor.config_kD(0, ShooterConstants.kD);
  }

  public void shoot(double shooterRPM) {
    // V = Pulses / 100 ms => 1000 ms / 1 s * 60 s / 1 min * 1 rev / 2048 pulses
    targetSpeed = (shooterRPM*2048)/600;
    shooterMotor.set(ControlMode.Velocity, targetSpeed);
  }

  public void manualShoot() {
    // V = Pulses / 100 ms => 1000 ms / 1 s * 60 s / 1 min * 1 rev / 2048 pulses
    targetSpeed = (RPMSpeed*2048)/600;
    shooterMotor.set(ControlMode.Velocity, targetSpeed);
  }

  private double getShooterVelocityRaw() { return shooterMotor.getSelectedSensorVelocity(); }

  public double getShooterRPM() { return getShooterVelocityRaw()*600/2048; }

  public boolean isShooterReady() { 
    double actualSpeed = getShooterVelocityRaw();

    if (targetSpeed == 0){
      return false;
    } else {
      double errorRatio = actualSpeed/targetSpeed;
      if (errorRatio > 0.975 && errorRatio < 1.05) {
        return true;
      } else {
        return false;
      }
    }
  }

  @Override
  public void periodic() { 
  }
}
