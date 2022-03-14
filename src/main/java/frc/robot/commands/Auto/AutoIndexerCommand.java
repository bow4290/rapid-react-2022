package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class AutoIndexerCommand extends CommandBase {
  private IndexerSubsystem indexerSubsystem;
  private BallIdentification ballUpper;
  private BallIdentification ballLower;
  private ShooterSubsystem shooterSubsystem;

  public AutoIndexerCommand(IndexerSubsystem indexerSubsystem, ShooterSubsystem shooterSubsystem, BallIdentification ballUpper, BallIdentification ballLower) {
    this.indexerSubsystem = indexerSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    this.ballUpper = ballUpper;
    this.ballLower = ballLower;

    addRequirements(indexerSubsystem);
  }

@Override
  public void initialize() {}

  @Override
  public void execute() {
    if(shooterSubsystem.isShooterReady()){
      indexerSubsystem.turnBothIndexMotors(IndexerConstants.upperShootingIndexSpeed, IndexerConstants.lowerShootingIndexSpeed);
      // when shooting, turn both motors at a constant pace

    } else {
      if (!ballLower.isBallPresent() && !ballUpper.isBallPresent()){
        indexerSubsystem.turnBothIndexMotors(IndexerConstants.upperIntakingIndexSpeed, IndexerConstants.upperIntakingIndexSpeed);
        //if no ball is present turn both motors until ballUpper is true
      
      } else if (ballLower.isBallPresent() && !ballUpper.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(IndexerConstants.upperIntakingIndexSpeed, IndexerConstants.upperIntakingIndexSpeed);
        //if ball is in bottom indexer slot (& not upper slot) turn both motors until ballUpper true
      
      } else if (!ballLower.isBallPresent() && ballUpper.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(0, IndexerConstants.upperIntakingIndexSpeed);

        //if ball is in top indexer slot (& not lower slot) turn lower motor until ballLower is true

      } else if (ballLower.isBallPresent() && ballUpper.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(0, 0);
        //if ball is in both indexer slots do nothing
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexerSubsystem.turnBothIndexMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
