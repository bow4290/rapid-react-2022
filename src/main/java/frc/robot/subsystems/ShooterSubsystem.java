package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  public static WPI_TalonFX shooterMotor = new WPI_TalonFX(ShooterConstants.shooterMotorChannel);
  public static ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  
  private NetworkTableEntry kFEntry = tab.add("Shooter Motor kF", getkF()) .getEntry();
  private NetworkTableEntry kPEntry = tab.add("Shooter Motor kP", getkP()) .getEntry();
  private NetworkTableEntry kIEntry = tab.add("Shooter Motor kI", getkI()) .getEntry(); 
  private NetworkTableEntry kDEntry = tab.add("Shooter Motor kD", getkD()) .getEntry(); 

  // private NetworkTableEntry shooterRPMEntry = tab.add("Shooter Motor RPM", getShooterRPM()) .getEntry(); 
  double RPMSpeed = 6000;

  public ShooterSubsystem() {
    SmartDashboard.putNumber("RPM Shooter Speed", RPMSpeed);

    shooterMotor.configFactoryDefault();
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

  public void shoot(double shooterRPM) {
    // V = Pulses / 100 ms => 1000 ms / 1 s * 60 s / 1 min * 1 rev / 2048 pulses
    double velocityPer100ms = (shooterRPM*2048)/600;
    shooterMotor.set(ControlMode.Velocity, velocityPer100ms);
  }

  public void manualShoot() {
    // V = Pulses / 100 ms => 1000 ms / 1 s * 60 s / 1 min * 1 rev / 2048 pulses
    double RPM = SmartDashboard.getNumber("RPM Shooter Speed", RPMSpeed);
    if (RPM != RPMSpeed) RPMSpeed = RPM;
    double velocityPer100ms = (RPM*2048)/600;
    shooterMotor.set(ControlMode.Velocity, velocityPer100ms);
  }

  private double getShooterVelocityRaw() { return shooterMotor.getSelectedSensorVelocity(); }

  public double getShooterRPM() { return getShooterVelocityRaw()*600/2048; }

  public boolean isShooterReady() { 
    double targetSpeed = SmartDashboard.getNumber("RPM Shooter Speed", RPMSpeed);
    double actualSpeed = getShooterRPM();

    double errorRatio = actualSpeed/targetSpeed;
    if (errorRatio > 0.9) {
      return true;
    } else {
      return false;
    }
  }

  public double getkF() { return ShooterConstants.kF; }
  public double getkP() { return ShooterConstants.kP; }
  public double getkI() { return ShooterConstants.kI; }
  public double getkD() { return ShooterConstants.kD; }

  @Override
  public void periodic() {
    //SmartDashboard.putNumber("Shooter Motors RPM", getShooterRPM());
    //SmartDashboard.putNumber("Shooter Motors kF", ShooterConstants.kF);
    //SmartDashboard.putNumber("Shooter Motors kP", ShooterConstants.kP);
    //SmartDashboard.putNumber("Shooter Motors kI", ShooterConstants.kI);
    //SmartDashboard.putNumber("Shooter Motors kD", ShooterConstants.kD);

    kFEntry.setDouble(ShooterConstants.kF);
    kPEntry.setDouble(ShooterConstants.kP);
    kIEntry.setDouble(ShooterConstants.kI);
    kDEntry.setDouble(ShooterConstants.kD);
    // shooterRPMEntry.setDouble(getShooterRPM());

    SmartDashboard.putNumber("Talon Velocity (RPM): ", shooterMotor.getSelectedSensorVelocity()*600/2048);  
  }
}
