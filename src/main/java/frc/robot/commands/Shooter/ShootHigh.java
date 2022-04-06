package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class ShootHigh extends CommandBase {
  private Limelight limelight;
  private ShooterSubsystem shooterSubsystem;
  private TurretSubsystem turretSubsystem;

  public ShootHigh(Limelight limelight, ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
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
    if(turretSubsystem.isTurretReady()) {
      double distance = limelight.getDistance();
      double calculatedRPM = calculateShooterSpeedRPM(distance);
      shooterSubsystem.shoot(calculatedRPM);
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
    return (0.00003293*Math.pow(distance,3.669)+3269);
  }
}
