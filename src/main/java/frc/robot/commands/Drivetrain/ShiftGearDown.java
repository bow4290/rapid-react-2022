package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Command to shift the gears down.
 */
public class ShiftGearDown extends CommandBase {
  private DrivetrainSubsystem drivetrainSubsystem;

  public ShiftGearDown() {
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drivetrainSubsystem.shiftDown();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
