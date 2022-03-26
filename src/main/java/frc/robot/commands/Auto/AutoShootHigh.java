package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HoodConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class AutoShootHigh extends CommandBase {
  private Limelight limelight;
  private ShooterSubsystem shooterSubsystem;
  private TurretSubsystem turretSubsystem;
  private int counter = 10;

  private BallIdentification high, low;

  public AutoShootHigh(Limelight limelight, ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem, BallIdentification high, BallIdentification low) {
    this.limelight = limelight;
    this.shooterSubsystem = shooterSubsystem;
    this.turretSubsystem = turretSubsystem;
    this.high = high;
    this.low = low;
    
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(turretSubsystem.isTurretReady()) {
      double distance = limelight.getDistance();
      double calculatedRPM = calculateShooterSpeedRPM(distance);
      shooterSubsystem.shoot(calculatedRPM);
    } else {
      shooterSubsystem.shoot(0);
    }
    if (!high.isBallPresent() && !low.isBallPresent()) {
      counter -= 1;
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.shoot(0);
  }

  @Override
  public boolean isFinished() {
    return counter == 0;
  }

  private double calculateShooterSpeedRPM(double distance) {
    if (distance < HoodConstants.hoodExtendDistance) {
      return (16.62*distance+2392+ShooterConstants.adjustNearRPM);        // Hood Retracted Equation
    } else {
      return (40.32*distance-2201.6+ShooterConstants.adjustFarRPM);       // Hood Extended Equation
    }
  }
}
