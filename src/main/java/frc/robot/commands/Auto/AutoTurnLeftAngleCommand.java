package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoTurnLeftAngleCommand extends CommandBase {
  DrivetrainSubsystem drivetrainSubsystem;
  double degrees;

  public AutoTurnLeftAngleCommand(DrivetrainSubsystem drivetrainSubsystem, double degrees) {
    this.drivetrainSubsystem = drivetrainSubsystem;
    this.degrees = degrees;
    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {
    System.out.println("AutoDriveTurn Started!");
    drivetrainSubsystem.resetDriveEncoders();
    System.out.println(drivetrainSubsystem.getLeftCalculatedPosition());
  }

  @Override
  public void execute() {
    System.out.println(drivetrainSubsystem.getLeftCalculatedPosition());
    drivetrainSubsystem.drive(-DriveConstants.autonomousTurnSpeed, DriveConstants.autonomousTurnSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Interrupt?: " + interrupted);
    System.out.println("AutoDriveTurn DONE!");
    drivetrainSubsystem.drive(0,0);
  }

  @Override
  public boolean isFinished() {
    System.out.println("Finished?: " + (drivetrainSubsystem.getTurnLeftDegrees() >= degrees));
    return (drivetrainSubsystem.getTurnLeftDegrees() >= degrees);
  }
}
