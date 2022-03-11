// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class ToggleTurretCommand extends CommandBase {
  private TurretSubsystem turretSubsystem;

  /** Creates a new StopTurretCommand. */
  public ToggleTurretCommand(TurretSubsystem turretSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.

    this.turretSubsystem = turretSubsystem;
    addRequirements(turretSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    turretSubsystem.isTurretStopped = !turretSubsystem.isTurretStopped;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
