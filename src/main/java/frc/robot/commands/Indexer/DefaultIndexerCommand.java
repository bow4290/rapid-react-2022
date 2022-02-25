package frc.robot.commands.Indexer;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.IndexerConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class DefaultIndexerCommand extends CommandBase {
  private IndexerSubsystem indexerSubsystem;
  private BallIdentification ballUpper;
  private BallIdentification ballLower;
  private BooleanSupplier isIntakeButtonPressed;
  private ShooterSubsystem shooterSubsystem;

  public DefaultIndexerCommand(IndexerSubsystem indexerSubsystem, ShooterSubsystem shooterSubsystem, BallIdentification ballUpper, BallIdentification ballLower, BooleanSupplier isIntakeButtonPressed) {
    this.indexerSubsystem = indexerSubsystem;
    this.ballUpper = ballUpper;
    this.ballLower = ballLower;
    this.isIntakeButtonPressed = isIntakeButtonPressed;

    this.shooterSubsystem = shooterSubsystem;
  }

@Override
  public void initialize() {}

  @Override
  public void execute() {
    if(shooterSubsystem.isShooterReady()){
      indexerSubsystem.turnBothIndexMotors(IndexerConstants.bothShootingIndexSpeed);
      // when shooting, turn both motors at a constant pace

    } else if (isIntakeButtonPressed.getAsBoolean()) {
      if (!ballLower.isBallPresent() && !ballUpper.isBallPresent()){
        indexerSubsystem.turnBothIndexMotors(IndexerConstants.bothIntakingIndexSpeed);
        //if no ball is present turn both motors until ballUpper is true
      
      } else if (ballLower.isBallPresent() && !ballUpper.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(IndexerConstants.bothIntakingIndexSpeed);
        //if ball is in bottom indexer slot (& not upper slot) turn both motors until ballUpper true
      
      } else if (!ballLower.isBallPresent() && ballUpper.isBallPresent()) {
        indexerSubsystem.turnUpperIndexMotor(0);
        indexerSubsystem.turnLowerIndexMotor(IndexerConstants.lowerIntakingIndexSpeed);
        //if ball is in top indexer slot (& not lower slot) turn lower motor until ballLower is true

      } else if (ballLower.isBallPresent() && ballUpper.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(0);
        //if ball is in both indexer slots do nothing
      }
    } else {
      indexerSubsystem.turnBothIndexMotors(0);
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
