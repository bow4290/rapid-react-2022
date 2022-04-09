package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends CommandBase {

  private Limelight limelight;
  private TurretSubsystem turretSubsystem;
  private enum TurretState {INIT, SEARCH, TRACK};
  private TurretState turretState = TurretState.INIT;
  private TurretState newTurretState;
  private double turretSpeed = 0.0;
  private double localTrackSpeed = TurretConstants.defaultTrackSpeed;
  private double localSearchSpeed = TurretConstants.defaultSearchSpeed;

  public TurretCommand(Limelight limelight, TurretSubsystem turretSubsystem) {
    this.limelight = limelight;

    this.turretSubsystem = turretSubsystem;
    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    //if (Flags.turret) m_robotContainer.turretSubsystem.encoder.setPosition(0);
  }

  @Override
  public void execute() {
    if (turretSubsystem.isTurretStopped == true){
      if (turretSubsystem.encoder.getPosition() > 4) {
        turretSubsystem.turnTurret(-TurretConstants.turretHomingSpeed);      // We may need to swap signs here, will need to test.
      } else if (turretSubsystem.encoder.getPosition() < -4){
        turretSubsystem.turnTurret(TurretConstants.turretHomingSpeed);
      } else {
        turretSubsystem.stopTurret();
      }
    } else {
      newTurretState = determineTurretState();

      if (newTurretState != turretState) {
        if (newTurretState == TurretState.SEARCH) {
          turretSpeed = localSearchSpeed;
        } else {
          turretSpeed = localTrackSpeed;
        }
        turretState = newTurretState;
      }

      if (turretState == TurretState.SEARCH) {
        updateSearchSetpoint();
      } else {
        updateTrackSetpoint();
      }

      turretSubsystem.turnTurret(turretSpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private void updateSearchSetpoint() {
    if (turretSubsystem.getHitLeftLimitSwitch()) {
      turretSpeed = Math.abs(turretSpeed);
    } else if (turretSubsystem.getHitRightLimitSwitch()) {
      turretSpeed = -Math.abs(turretSpeed);
    }
  }

  private void updateTrackSetpoint() {
    if(turretSubsystem.getHitLeftLimitSwitch() || turretSubsystem.getHitRightLimitSwitch()){
      turretSpeed = 0; 
    }else {
      turretSpeed = limelight.getXErrorWithOffset(TurretConstants.aimOffsetDistance)*TurretConstants.turretKP;
    }
    
    if (turretSubsystem.getHitLeftLimitSwitch()) {
      turretSpeed = Math.abs(turretSpeed);
    } else if (turretSubsystem.getHitRightLimitSwitch()) {
      turretSpeed = -Math.abs(turretSpeed);
    } 
  }

  private TurretState determineTurretState(){
    return limelight.isTarget() ? TurretState.TRACK : TurretState.SEARCH;
  }
}
