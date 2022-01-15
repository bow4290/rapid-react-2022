package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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

  private final DoubleSolenoid gearShiftSolenoid;

  public enum GearShiftStatus{
    UP, DOWN
  }

  public static GearShiftStatus gearShiftStatus;

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

    leftMotor1.configVoltageCompSaturation(11);
    leftMotor2.configVoltageCompSaturation(11);
    rightMotor1.configVoltageCompSaturation(11);
    rightMotor2.configVoltageCompSaturation(11);

    leftMotor1.enableVoltageCompensation(true); 
    leftMotor2.enableVoltageCompensation(true); 
    rightMotor1.enableVoltageCompensation(true); 
    rightMotor2.enableVoltageCompensation(true);

    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

    drivetrain = new DifferentialDrive(leftMotor1, rightMotor1);

    gearShiftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, DriveConstants.gearShiftUpChannel, DriveConstants.gearShiftDownChannel);
  }

  public GearShiftStatus getSolenoid(){
    return gearShiftStatus;
  }

  public void drive(double leftSpeed, double rightSpeed) { 
    drivetrain.tankDrive(leftSpeed, rightSpeed, true);
  }

  public void resetDriveEncoders() {
    leftMotor1.setSelectedSensorPosition(0);
    rightMotor1.setSelectedSensorPosition(0);
  }

  // add distance conversion for shifting
  public double getLeftCalculatedPosition() {
    return (getLeftRawEncoderPosition() * (DriveConstants.encoderDistanceConversion));
  }

  public double getRightCalculatedPosition() {
    return (getRightRawEncoderPosition() * (DriveConstants.encoderDistanceConversion));
  }

  private double getLeftRawEncoderPosition() {
    return leftMotor1.getSelectedSensorPosition();
  }

  private double getRightRawEncoderPosition() {
    return rightMotor1.getSelectedSensorPosition();
  }


  @Override
  public void periodic(){
    SmartDashboard.putNumber("Left Drive Position", getLeftCalculatedPosition());
    SmartDashboard.putNumber("Right Drive Position", getRightCalculatedPosition());
    SmartDashboard.putNumber("Left Raw Drive Position", getLeftRawEncoderPosition());
    SmartDashboard.putNumber("Right Raw Drive Position", getRightRawEncoderPosition());
  } 
}