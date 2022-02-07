package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Drivetrain.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick joystickLeft;
  public static Joystick joystickRight;

  private DrivetrainSubsystem drivetrainSubsystem;

  public RobotContainer() {
    joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
    joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);

    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new Drive(joystickLeft, joystickRight, drivetrainSubsystem));

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown(drivetrainSubsystem));
    setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp(drivetrainSubsystem));
  }

  public Command getAutonomousCommand() { return null; }

  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }
}