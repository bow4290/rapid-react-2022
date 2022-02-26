
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TelescopeSubsystem;

public class ExtendTelescope extends CommandBase {
  private TelescopeSubsystem telescopeSubsystem;
  private double telescopeSpeed;
  
  public ExtendTelescope(double telescopeSpeed, TelescopeSubsystem telescopeSubsystem) {
    this.telescopeSubsystem = telescopeSubsystem;
    this.telescopeSpeed = telescopeSpeed;
    addRequirements(telescopeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { telescopeSubsystem.extendTelescope(telescopeSpeed); }

  @Override
  public void end(boolean interrupted) { telescopeSubsystem.stopTelescope(); }

  @Override
  public boolean isFinished() {
    return false;
  }
}
