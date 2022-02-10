package frc.robot.commands.Hood;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HoodConstants;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.HoodSubsystem;

public class DefaultHoodCommand extends CommandBase {
  private Limelight limelight;
  private HoodSubsystem hoodSubsystem;
  
  public DefaultHoodCommand(Limelight limelight, HoodSubsystem hoodSubsystem) {
    this.limelight = limelight;
    this.hoodSubsystem = hoodSubsystem;
    addRequirements(hoodSubsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    if(limelight.isTarget()){
      if((limelight.getDistance() > HoodConstants.hoodExtendDistance) && !hoodSubsystem.isHoodExtended()){
        hoodSubsystem.extendHoodSolenoid();
      } else if ((limelight.getDistance() < HoodConstants.hoodExtendDistance) && hoodSubsystem.isHoodExtended()){
        hoodSubsystem.retractHoodSolenoid();
      }
    }
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
