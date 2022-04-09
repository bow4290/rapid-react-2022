package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootHardcodedDiscard extends CommandBase {
  private ShooterSubsystem shooterSubsystem;

  public ShootHardcodedDiscard(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    shooterSubsystem.shoot(2000.0);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.shoot(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
