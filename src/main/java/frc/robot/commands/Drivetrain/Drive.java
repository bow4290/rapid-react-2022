package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

/** Command class to drive the robot. */
public class Drive extends CommandBase {
  private DrivetrainSubsystem drivetrainSubsystem;
  private double leftSpeed;
  private double rightSpeed;


  public Drive(double leftSpeed, double rightSpeed, DrivetrainSubsystem drivetrainSubsystem) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {}
  
  @Override
  public void execute() {
    drivetrainSubsystem.drive(leftSpeed, rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
