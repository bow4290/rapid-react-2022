package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootLow extends CommandBase {
  private Limelight limelight;
  private ShooterSubsystem shooterSubsystem;

  public ShootLow(Limelight limelight, ShooterSubsystem shooterSubsystem) {
    this.limelight = limelight;
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    boolean isBallTeamColor = true;
    if(limelight.isTarget() && isBallTeamColor) {
      double distance = limelight.getDistance();
      double calculatedRPM = calculateShooterSpeedRPM(distance);
      shooterSubsystem.shoot(calculatedRPM);
    } else if(!isBallTeamColor) {
      shooterSubsystem.shoot(ShooterConstants.discardSpeedRPM);
    } else {
      shooterSubsystem.shoot(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.shoot(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double calculateShooterSpeedRPM(double distance) {
    return 2500;
  }
}
