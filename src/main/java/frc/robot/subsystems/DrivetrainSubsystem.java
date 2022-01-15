package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX; // Falcon500 onboard speed controller
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import frc.robot.Constants.DriveConstants;

public class DrivetrainSubsystem extends SubsystemBase {
  private WPI_TalonFX leftMotor1 = new WPI_TalonFX(DriveConstants.leftMotor1Channel);
  private WPI_TalonFX leftMotor2 = new WPI_TalonFX(DriveConstants.leftMotor2Channel);
  private WPI_TalonFX rightMotor1 = new WPI_TalonFX(DriveConstants.rightMotor1Channel);
  private WPI_TalonFX rightMotor2 = new WPI_TalonFX(DriveConstants.rightMotor2Channel);

  private final DifferentialDrive drivetrain;

  public DrivetrainSubsystem() {
    leftMotor1.setInverted(TalonFXInvertType.Clockwise);
    leftMotor2.follow(leftMotor1);
    leftMotor2.setInverted(InvertType.FollowMaster);

    rightMotor1.setInverted(TalonFXInvertType.CounterClockwise);
    rightMotor2.follow(rightMotor1);
    rightMotor2.setInverted(InvertType.FollowMaster);

    leftMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);

    leftMotor1.configOpenloopRamp(0.5);
    leftMotor2.configOpenloopRamp(0.5);
    rightMotor1.configOpenloopRamp(0.5);
    rightMotor2.configOpenloopRamp(0.5);

    drivetrain = new DifferentialDrive(leftMotor1, rightMotor1);
  }

  public void drive(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive(leftSpeed, rightSpeed, true);
  }

}
