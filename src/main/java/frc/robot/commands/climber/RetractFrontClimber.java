
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberFrontSubsystem;

public class RetractFrontClimber extends CommandBase {
  private ClimberFrontSubsystem climberFrontSubsystem;
  private double climberSpeed;
  
  public RetractFrontClimber(double climberSpeed, ClimberFrontSubsystem climberFrontSubsystem) {
    this.climberFrontSubsystem = climberFrontSubsystem;
    this.climberSpeed = climberSpeed;
    addRequirements(climberFrontSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { climberFrontSubsystem.retractFrontClimber(climberSpeed); }

  @Override
  public void end(boolean interrupted) { climberFrontSubsystem.stopFrontClimber(); }

  @Override
  public boolean isFinished() {
    return false;
  }
}
