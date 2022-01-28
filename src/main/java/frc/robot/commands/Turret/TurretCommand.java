package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.Constants.TurretConstants;

public class TurretCommand extends CommandBase {

  public static enum TurretMode { SEARCH, TRACK, DISCARD }
  
  private BallIdentification ball;
  private Limelight limelight;
  // TODO discard button?
  private TurretSubsystem turretSubsystem;

  private TurretMode turretMode = TurretMode.SEARCH;
  private double turretMotorAngle = 0.0;
  private int turretDirection = 1;  // TODO: make enum

  public TurretCommand(BallIdentification ball, Limelight limelight, TurretSubsystem turretSubsystem) {
    this.ball = ball;
    this.limelight = limelight;

    this.turretSubsystem = turretSubsystem;
    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    determineTurretMode();

    switch(turretMode) {
      case TRACK:
        trackModeCalcRpmAndDir();
        break;
      case DISCARD:
        discardModeCalcRpmAndDir();
        break;
      case SEARCH:
      default:
        searchModeCalcRpmAndDir();
    }

    turretSubsystem.turn(turretMotorAngle);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

  private void determineTurretMode() {
    // TODO: placeholder
    boolean discardButtonHeld = false;

    if (!limelight.isTarget() && !discardButtonHeld && ball.isBallTeamColor()) {
      turretMode = TurretMode.SEARCH;
    }
    else if (limelight.isTarget() && !discardButtonHeld && ball.isBallTeamColor()) {
      turretMode = TurretMode.TRACK;
    }
    else if (discardButtonHeld || !ball.isBallTeamColor()) {
      turretMode = TurretMode.DISCARD;
    }
  }

  private void searchModeCalcRpmAndDir() {
    
    if (turretSubsystem.getHitLeftLimitSwitch() || turretSubsystem.getHitRightLimitSwitch()) {
      turretDirection *= -1;
    }
    
    turretMotorAngle += TurretConstants.searchAngleStep * turretDirection;
  }

  private void trackModeCalcRpmAndDir() {
    
  }

  private void discardModeCalcRpmAndDir() {
    
  }
}
