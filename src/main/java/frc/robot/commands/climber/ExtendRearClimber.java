// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberRearSubsystem;

public class ExtendRearClimber extends CommandBase {
  private ClimberRearSubsystem climberRearSubsystem;
  private double climberSpeed;
  
  /** Creates a new ExtendRearClimber. */
  public ExtendRearClimber(double climberSpeed, ClimberRearSubsystem climberRearSubsystem) {
    this.climberRearSubsystem = climberRearSubsystem;
    this.climberSpeed = climberSpeed;
    addRequirements(climberRearSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {climberRearSubsystem.extendRearClimber(climberSpeed);}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {climberRearSubsystem.stopRearClimber();}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
