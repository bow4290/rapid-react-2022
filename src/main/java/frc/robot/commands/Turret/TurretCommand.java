package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends CommandBase {

  public static enum TurretMode { SEARCH, TRACK, DISCARD }
  private static enum SearchMotorDirection { CW, CCW }

  private TurretSubsystem turretSubsystem;
  private Limelight limelight;
  //private ButtonWrapper leftLimitSwitch;
  //private ButtonWrapper rightLimitSwitch;
  private TurretMode turretMode = TurretMode.SEARCH;
  private SearchMotorDirection searchMotorDirection;

  // TODO discard button
  // add ButtonWrapper limit switches to constructor
  public TurretCommand(TurretSubsystem turretSubsystem, Limelight limelight) {
    this.turretSubsystem = turretSubsystem;
    this.limelight = limelight;
    //this.leftLimitSwitch = leftLimitSwitch;
    //this.rightLimitSwitch = rightLimitSwitch;
    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    turretMode = determineTurretMode();

    switch(turretMode) {
      case SEARCH:
        searchMode();
        break;
      case TRACK:
        trackMode();
        break;
      case DISCARD:
        discardMode();
        break;
      default:
        searchMode();
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }

  private TurretMode determineTurretMode(){

    /**
     * if limelight !isTarget() AND !DiscardButtonHeld AND isBallTeamColor
     *  set to SEARCH
     * else if limelight isTarget() AND !DiscardButtonHeld AND isBallTeamColor
     *  set to TRACK
     * else if DiscardButtonHeld OR !isBallTeamColor
     *  set to DISCARD
     */

    return TurretMode.SEARCH;
  }

  private void searchMode(){
    /**
     * double searchSpeed = turretConstants.motorSpeed
     * if leftLimitSwitch isPressed, set searchMotorDirection.CW
     * if rightLimitSwitch isPressed, set searchMotorDirection.CCW
     * if CW, TurretSubsystem.setSpeed(searchSpeed)
     * else if CCW, TurretSubsystem.setSpeed(-searchSpeed)
     * else, TurretSubsystem.setSpeed(0)
     */
  }

  private void trackMode(){}

  private void discardMode(){}
}
