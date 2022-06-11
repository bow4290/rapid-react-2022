package frc.robot.commands.Drivetrain;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DefaultDriveCommand extends CommandBase {
  private DrivetrainSubsystem drivetrainSubsystem;
  private DoubleSupplier leftSpeed;
  private DoubleSupplier rightSpeed;
  private DoubleSupplier thrustSpeed;

  public DefaultDriveCommand(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed, DoubleSupplier thrustSpeed, DrivetrainSubsystem drivetrainSubsystem) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    this.thrustSpeed = thrustSpeed;
    this.drivetrainSubsystem = drivetrainSubsystem;

    addRequirements(drivetrainSubsystem);
  }

  @Override
  public void initialize() {
  }

  private final double minSpeed = 0.1;

  @Override
  public void execute() {
    double left = leftSpeed.getAsDouble();
    // if (Math.abs(left) < 0.1) left = 0.0;
    double right = rightSpeed.getAsDouble();
    // if (Math.abs(right) < 0.1) right = 0.0;
    // double thrust = thrustSpeed.getAsDouble();
    // if (thrust < 0.1) thrust = 0.0;
    // thrust = thrust * thrust;
    drivetrainSubsystem.drive(Math.copySign(Math.pow(Math.abs(left), 2.5), left), Math.copySign(right * right, right));
    // drivetrainSubsystem.drive(Math.signum(left) * (minSpeed + ((1 - minSpeed) * thrust)), Math.signum(right) * (minSpeed + ((1 - minSpeed) * thrust)));
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
