
package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.ClimberRearSubsystem;

public class ExtendRearClimber extends CommandBase {
  private ClimberRearSubsystem climberRearSubsystem;
  private double climberSpeed;
  
  public ExtendRearClimber(double climberSpeed, ClimberRearSubsystem climberRearSubsystem) {
    this.climberRearSubsystem = climberRearSubsystem;
    this.climberSpeed = climberSpeed;
    addRequirements(climberRearSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { climberRearSubsystem.extendRearClimber(climberSpeed); }

  @Override
  public void end(boolean interrupted) { climberRearSubsystem.stopRearClimber(); }

  @Override
  public boolean isFinished() {
    return climberRearSubsystem.getRearClimberCalculatedPosition()>=DriveConstants.rearClimberExtendDistance;
  }
}
