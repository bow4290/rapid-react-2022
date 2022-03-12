package frc.robot.commands.Indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class DefaultIndexerCommand extends CommandBase {
  private IndexerSubsystem indexerSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private BallIdentification ballUpper;
  private BallIdentification ballLower;

  public DefaultIndexerCommand(IndexerSubsystem indexerSubsystem, ShooterSubsystem shooterSubsystem, 
                               IntakeSubsystem intakeSubsystem, BallIdentification ballUpper, 
                               BallIdentification ballLower) {
    this.indexerSubsystem = indexerSubsystem;
    this.shooterSubsystem = shooterSubsystem;
    this.intakeSubsystem = intakeSubsystem;
    this.ballUpper = ballUpper;
    this.ballLower = ballLower;

    addRequirements(indexerSubsystem);
  }

@Override
  public void initialize() {}

  @Override
  public void execute() {
    if(shooterSubsystem.isShooterReady()){
      indexerSubsystem.turnBothIndexMotors(IndexerConstants.bothShootingIndexSpeed, IndexerConstants.bothShootingIndexSpeed);
      // when shooting, turn both motors at a constant pace

    } else if (intakeSubsystem.isIntakeSpinning()) {
      System.out.println(ballLower.isBallPresent());
      System.out.println(ballUpper.isBallPresent());
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
    } else {
      indexerSubsystem.turnBothIndexMotors(0, 0);
      //if not shooting and not intaking, do nothing.
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
