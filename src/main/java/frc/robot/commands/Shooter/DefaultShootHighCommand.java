package frc.robot.commands.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class DefaultShootHighCommand extends CommandBase {
  private DoubleSupplier shooterActiveDouble;
  private Limelight limelight;
  private ShooterSubsystem shooterSubsystem;
  private TurretSubsystem turretSubsystem;

  public DefaultShootHighCommand(DoubleSupplier shooterActiveDouble, Limelight limelight, ShooterSubsystem shooterSubsystem, TurretSubsystem turretSubsystem) {
    this.shooterActiveDouble = shooterActiveDouble;
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
    if(shooterActiveDouble.getAsDouble() > ShooterConstants.shooterTriggerbuffer 
        && turretSubsystem.isTurretReady()) {
      double distance = limelight.getDistance();
      double calculatedRPM = calculateShooterSpeedRPM(distance);
      shooterSubsystem.shoot(calculatedRPM);
    } else {
      shooterSubsystem.shoot(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private double calculateShooterSpeedRPM(double distance) {
    return (0.00003293*Math.pow(distance,3.669)+3269);
  }
}
