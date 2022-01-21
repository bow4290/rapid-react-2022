// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class ClimberRearSubsystem extends SubsystemBase {
  private CANSparkMax rearClimberMotorChannel = new CANSparkMax(DriveConstants.rearClimberMotorChannel, MotorType.kBrushless);
  
  /** Creates a new ClimberRearSubsystem. */
  public ClimberRearSubsystem() {rearClimberMotorChannel.setInverted(false);}

  public void extendRearClimber(double climberSpeed) {rearClimberMotorChannel.set(climberSpeed);}

  public void retractRearClimber(double climberSpeed) {rearClimberMotorChannel.set(-climberSpeed);}

  public void stopRearClimber() {rearClimberMotorChannel.set(0);}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
