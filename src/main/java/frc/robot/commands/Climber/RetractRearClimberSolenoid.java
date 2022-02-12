package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberRearSubsystem;

public class RetractRearClimberSolenoid extends CommandBase {
  private ClimberRearSubsystem climberRearSubsystem;

  public RetractRearClimberSolenoid(ClimberRearSubsystem climberRearSubsystem) {
    this.climberRearSubsystem = climberRearSubsystem;
    addRequirements(climberRearSubsystem);
  }

  @Override
  public void initialize() {
    //climberRearSubsystem.retractRearClimberSolenoid();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
