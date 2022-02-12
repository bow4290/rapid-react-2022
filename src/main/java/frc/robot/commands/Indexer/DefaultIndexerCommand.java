// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Indexer;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.sensors.BallIdentification;
import frc.robot.subsystems.IndexerSubsystem;

public class DefaultIndexerCommand extends CommandBase {
  private IndexerSubsystem indexerSubsystem;
  private BallIdentification ballUpper;
  private BallIdentification ballLower;
  private BooleanSupplier isIntakeButtonPressed;

  private Boolean isShooterReady;
  // TODO: make the above into a global variable, setting it true when the shooter reaches firing speed.

  /** Creates a new DefaultIndexerCommad. */
  public DefaultIndexerCommand(IndexerSubsystem indexerSubsystem, BallIdentification ballUpper, BallIdentification ballLower, BooleanSupplier isIntakeButtonPressed) {
    this.indexerSubsystem = indexerSubsystem;
    this.ballUpper = ballUpper;
    this.ballLower = ballLower;
    this.isIntakeButtonPressed = isIntakeButtonPressed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    isShooterReady = false;
    // FIXME: The local variable "isShooterReady" is set false. See above TODO   
    if(isShooterReady){
      indexerSubsystem.turnBothIndexMotors(Constants.IndexerConstants.indexerSpeed, Constants.IndexerConstants.indexerSpeed);
      // when shooting, turn both motors at a constant pace

    } else if (isIntakeButtonPressed.getAsBoolean()) {
      if (!ballUpper.isBallPresent() && !ballLower.isBallPresent()){
        indexerSubsystem.turnBothIndexMotors(Constants.IndexerConstants.indexerSpeed, Constants.IndexerConstants.indexerSpeed);
        //if no ball is present turn both motors until ballUpper is true
      
      } else if (ballLower.isBallPresent() && !ballUpper.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(Constants.IndexerConstants.indexerSpeed, Constants.IndexerConstants.indexerSpeed);
        //if ball is in bottom indexer slot (& not upper slot) turn both motors until ballUpper true
      
      } else if (ballUpper.isBallPresent() && !ballLower.isBallPresent()) {
        indexerSubsystem.turnBothIndexMotors(0, Constants.IndexerConstants.indexerSpeed);
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
