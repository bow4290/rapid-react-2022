
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberRearSubsystem;

public class RetractRearClimber extends CommandBase {
  private ClimberRearSubsystem climberRearSubsystem;
  private double climberSpeed;
  
  public RetractRearClimber(double climberSpeed, ClimberRearSubsystem climberRearSubsystem) {
    this.climberRearSubsystem = climberRearSubsystem;
    this.climberSpeed = climberSpeed;
    addRequirements(climberRearSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { climberRearSubsystem.retractRearClimber(climberSpeed); }

  @Override
  public void end(boolean interrupted) { climberRearSubsystem.stopRearClimber(); }

  @Override
  public boolean isFinished() {
    return false;
  }
}
