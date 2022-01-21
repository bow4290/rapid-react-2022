
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberFrontSubsystem;

public class StopFrontClimber extends CommandBase {
  private ClimberFrontSubsystem climberFrontSubsystem;
  
  public StopFrontClimber(ClimberFrontSubsystem climberFrontSubsystem) {
    this.climberFrontSubsystem = climberFrontSubsystem;
    addRequirements(climberFrontSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { climberFrontSubsystem.stopFrontClimber(); }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
