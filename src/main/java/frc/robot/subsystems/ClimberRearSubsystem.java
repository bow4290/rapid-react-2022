package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.DriveConstants;

public class ClimberRearSubsystem extends SubsystemBase {
  private WPI_TalonFX rearClimberMotorChannel = new WPI_TalonFX(DriveConstants.rearClimberMotorChannel);
  private final DoubleSolenoid rearClimberSolenoid;
  public enum ClimberStatus { EXTENDED, RETRACTED }
  public static ClimberStatus climberStatus = ClimberStatus.RETRACTED;

  public ClimberRearSubsystem() { 
    rearClimberMotorChannel.setInverted(false);
    rearClimberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, DriveConstants.rearClimberSolenoidUpChannel, DriveConstants.rearClimberSolenoidDownChannel);
  
    rearClimberMotorChannel.setNeutralMode(NeutralMode.Brake);

    rearClimberMotorChannel.configOpenloopRamp(0.5);

    rearClimberMotorChannel.configVoltageCompSaturation(11);

    rearClimberMotorChannel.enableVoltageCompensation(true);

    rearClimberMotorChannel.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
  }

  public void extendRearClimber(double climberSpeed) { rearClimberMotorChannel.set(climberSpeed); }

  public void extendRearClimberSolenoid() {
    rearClimberSolenoid.set(DoubleSolenoid.Value.kForward);
    climberStatus = ClimberStatus.EXTENDED;
  }

  public void retractRearClimber(double climberSpeed) { rearClimberMotorChannel.set(-climberSpeed); }

  public void retractRearClimberSolenoid() {
    rearClimberSolenoid.set(DoubleSolenoid.Value.kReverse);
    climberStatus = ClimberStatus.RETRACTED;
  }

  public void stopRearClimber() { rearClimberMotorChannel.set(0); }

  public static ClimberStatus getRearClimberSolenoidPosition() { return climberStatus; }

  public double getRearClimberCalculatedPosition() {
    return (rearClimberMotorChannel.getSelectedSensorPosition() * (DriveConstants.rearClimberEncoderDistanceConversion));
  }

  public void resetRearClimberEncoders() {
    rearClimberMotorChannel.setSelectedSensorPosition(0);

  }

  @Override
  public void periodic() {
  }
}
