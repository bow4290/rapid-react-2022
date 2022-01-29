package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.Constants.TurretConstants;

public class TurretCommand extends CommandBase {
  
  private Limelight limelight;
  private TurretSubsystem turretSubsystem;

  private double turretMotorAngle = 0.0;
  private int turretDirection = 1;  // TODO: make enum

  public TurretCommand(Limelight limelight, TurretSubsystem turretSubsystem) {
    this.limelight = limelight;

    this.turretSubsystem = turretSubsystem;
    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    if (limelight.isTarget()) {
      trackModeCalcAngle();
    } else {
      searchModeCalcAngle();
    }

    turretSubsystem.turn(turretMotorAngle);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

  private void searchModeCalcAngle() {
    
    if (turretSubsystem.getHitLeftLimitSwitch() || turretSubsystem.getHitRightLimitSwitch()) {
      turretDirection *= -1;
    }
    
    turretMotorAngle += TurretConstants.searchAngleStep * turretDirection;
  }

  private void trackModeCalcAngle() {
    // TODO
  }
}
