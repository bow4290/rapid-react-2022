// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorUpCommand extends CommandBase {
  /** Creates a new ElevatorUpCommand. */
  private ElevatorSubsystem elevatorSubsystem;

  public ElevatorUpCommand(ElevatorSubsystem elevatorSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.elevatorSubsystem = elevatorSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevatorSubsystem.getEncoderposition();
    
    if(elevatorSubsystem.getEncoderposition() >= ElevatorConstants.forwardSoftLimit){
      elevatorSubsystem.stopElevator();
    } else {    
      elevatorSubsystem.extendElevator(ElevatorConstants.elevatorSpeed);
    }
    // elevatorClimbMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    // elevatorClimbMotor.getSelectedSensorPosition
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {      
    elevatorSubsystem.stopElevator();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
