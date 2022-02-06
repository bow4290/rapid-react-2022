package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Drive extends CommandBase {
  private DrivetrainSubsystem drivetrainSubsystem;
  private Joystick leftStick;
  private Joystick rightStick;

  public Drive(Joystick leftStick, Joystick rightStick, DrivetrainSubsystem drivetrainSubsystem) {
    this.leftStick = leftStick;
    this.rightStick = rightStick;
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() { drivetrainSubsystem.drive(leftStick.getY(), rightStick.getY()); }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() { return false; }
}