package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class ToggleTurretCommand extends CommandBase {
  private TurretSubsystem turretSubsystem;

  public ToggleTurretCommand(TurretSubsystem turretSubsystem) {
    this.turretSubsystem = turretSubsystem;

    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turretSubsystem.isTurretStopped = !turretSubsystem.isTurretStopped;
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
