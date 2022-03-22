// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class EnableTurretCommand extends CommandBase {
  private TurretSubsystem turretSubsystem;

  public EnableTurretCommand(TurretSubsystem turretSubsystem) {
    this.turretSubsystem = turretSubsystem;

    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {    
    turretSubsystem.isTurretStopped = false;
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
