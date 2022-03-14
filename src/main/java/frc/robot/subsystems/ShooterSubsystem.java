package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  public static WPI_TalonFX shooterMotor = new WPI_TalonFX(ShooterConstants.shooterMotorChannel);

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

  /* SparkMAX motor controllers require a weird velocity unit.
   * To make this simple, let's provide an RPM value and allow the software to convert it.
   *
   *               Pulses
   * VelocityRaw = ------
   *               100 ms
   *
   *               Pulses     1000 ms     60 sec       1 rev
   * VelocityRPM = ------  *  -------  *  ------  *  -----------
   *               100 ms      1 sec      1 min      2048 pulses
   * 
   * VelocityRPM = VelocityControlMode * 600 / 2048
   * VelocityRaw = VelocityRPM * 2048 / 600
  */
  public void shoot(double shooterRPM) { 
    targetSpeed = shooterRPM*2048/600;
    shooterMotor.set(ControlMode.Velocity, targetSpeed);
  }

  public void manualShoot() {
    targetSpeed = ShooterConstants.manualShooterSpeedRPM*2048/600;
    shooterMotor.set(ControlMode.Velocity, targetSpeed);
  }

  private double getShooterVelocityRaw() {
    return shooterMotor.getSelectedSensorVelocity();
  }

  public double getShooterRPM() {
    return getShooterVelocityRaw()*600/2048;
  }

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
