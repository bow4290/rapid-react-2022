// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class ClimberFrontSubsystem extends SubsystemBase {
  private CANSparkMax frontClimberMotorChannel = new CANSparkMax(DriveConstants.frontClimberMotorChannel, MotorType.kBrushless);
  
  /** Creates a new ClimberFrontSubsystem. */
  public ClimberFrontSubsystem() {frontClimberMotorChannel.setInverted(false);}

  public void extendFrontClimber(double climberSpeed) {frontClimberMotorChannel.set(climberSpeed);}

  public void retractFrontClimber(double climberSpeed) {frontClimberMotorChannel.set(-climberSpeed);}

  public void stopFrontClimber() {frontClimberMotorChannel.set(0);}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
