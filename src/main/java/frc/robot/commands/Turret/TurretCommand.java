package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private double setpoint = 0.0;
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
    if (turretSubsystem.isTurretStopped == false){
      newTurretState = determineTurretState();

      if (newTurretState != turretState) {
        if (newTurretState == TurretState.SEARCH) {
          setpoint = localSearchSpeed;
        } else {
          setpoint = localTrackSpeed;
        }
        turretState = newTurretState;
      }

      if (turretState == TurretState.SEARCH) {
        updateSearchSetpoint();
      } else {
        updateTrackSetpoint();
      } 
      
      // turretSubsystem.turn(setpoint);
      turretSubsystem.turnManual(setpoint);
    } else if (turretSubsystem.isTurretStopped == true) {
      turretSubsystem.stopTurret();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

  private void updateSearchSetpoint() {
    if (turretSubsystem.getHitLeftLimitSwitch()) { setpoint = Math.abs(setpoint); }
    else if (turretSubsystem.getHitRightLimitSwitch()) { setpoint = -1 * Math.abs(setpoint); }
  }

  private void updateTrackSetpoint() {
    setpoint = limelight.getXError()*TurretConstants.turretKP; 
  }

  private TurretState determineTurretState(){
    return limelight.isTarget() ? TurretState.TRACK : TurretState.SEARCH;
  }
}
