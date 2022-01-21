
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberRearSubsystem;

public class StopRearClimber extends CommandBase {
  private ClimberRearSubsystem climberRearSubsystem;

  public StopRearClimber(ClimberRearSubsystem climberRearSubsystem) {
    this.climberRearSubsystem = climberRearSubsystem;
    addRequirements(climberRearSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { climberRearSubsystem.stopRearClimber(); }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
