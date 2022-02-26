
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TelescopeSubsystem;

public class StopTelescope extends CommandBase {
  private TelescopeSubsystem telescopeSubsystem;
  
  public StopTelescope(TelescopeSubsystem telescopeSubsystem) {
    this.telescopeSubsystem = telescopeSubsystem;
    addRequirements(telescopeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { telescopeSubsystem.stopTelescope(); }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
