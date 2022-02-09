package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends CommandBase {

  private Limelight limelight;
  private TurretSubsystem turretSubsystem;
  private enum TurretState {INIT, SEARCH, TRACK};
  private TurretState turretState = TurretState.INIT;
  private TurretState newTurretState;
  private double setpoint = 0.0;
  private double defaultTrackRPM = 0.0;
  private double searchRPM = 0.3;

  public TurretCommand(Limelight limelight, TurretSubsystem turretSubsystem) {
    this.limelight = limelight;

    this.turretSubsystem = turretSubsystem;
    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    newTurretState = determineTurretState();

    if (newTurretState != turretState) {
      if (newTurretState == TurretState.SEARCH) {
        setpoint = searchRPM;
      } else {
        setpoint = defaultTrackRPM;
      }
      turretState = newTurretState;
    }

    if (turretState == TurretState.SEARCH) {
      updateSearchSetpoint();
    } else {
      updateTrackSetpoint();
    }

    turretSubsystem.turn(setpoint);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

  private void updateSearchSetpoint() {
    if (turretSubsystem.getHitLeftLimitSwitch() || turretSubsystem.getHitRightLimitSwitch()) {
      setpoint *= -1;
    }
  }

  private void updateTrackSetpoint() {
    setpoint = limelight.getXError()/10; 
  }

  private TurretState determineTurretState(){
    return limelight.isTarget() ? TurretState.TRACK : TurretState.SEARCH;
  }
}
