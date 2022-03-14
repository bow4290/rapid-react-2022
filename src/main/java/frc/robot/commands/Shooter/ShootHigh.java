package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HoodConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class ShootHigh extends CommandBase {
  private BallIdentification ball;
  private Limelight limelight;
  private ShooterSubsystem shooterSubsystem;
  private TurretSubsystem turretSubsystem;

  public ShootHigh(BallIdentification ball, Limelight limelight, ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem
  ) {
    this.ball = ball;
    this.limelight = limelight;
    this.shooterSubsystem = shooterSubsystem;
    this.turretSubsystem = turretSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(turretSubsystem.isTurretReady() && ball.isBallTeamColor()) {
      double distance = limelight.getDistance();
      double calculatedRPM = calculateShooterSpeedRPM(distance);
      shooterSubsystem.shoot(calculatedRPM);
    } else if(!ball.isBallTeamColor()) {
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
    if (distance < HoodConstants.hoodExtendDistance) {
      return (3538*(Math.pow(1.002, distance))-175);    // Hood Retracted Equation
    } else {
      return ((5.06*distance)+5030.4);                  // Hood Extended Equation
    }
  }
}
