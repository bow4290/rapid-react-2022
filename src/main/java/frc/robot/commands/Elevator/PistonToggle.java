package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem.PistonStatus;

public class PistonToggle extends CommandBase {
  private ElevatorSubsystem elevatorSubsystem;

  public PistonToggle(ElevatorSubsystem elevatorSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;

    addRequirements(elevatorSubsystem);
  }

  @Override
  public void initialize() {   
    if (ElevatorSubsystem.getPistonStatus() == PistonStatus.UP) {
      elevatorSubsystem.extendPiston();
    } else {
     elevatorSubsystem.retractPiston();
   }
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
