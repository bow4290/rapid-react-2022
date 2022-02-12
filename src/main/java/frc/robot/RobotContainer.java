package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Intake.*;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.RevColorSensor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
  public static Joystick joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);
  public static Joystick xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

  public DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  public RevColorSensor redBallColorSensor = new RevColorSensor(80, 180, 50, 80, 15, 40, 0, 2048);
  public RevColorSensor blueBallColorSensor = new RevColorSensor(10, 70, 50, 100, 40, 100, 0, 2048);

  public BallIdentification ball = new BallIdentification(redBallColorSensor, blueBallColorSensor);


  public RobotContainer() {
    drivetrainSubsystem.setDefaultCommand(new Drive(() -> -joystickLeft.getY(), () -> -joystickRight.getY(), drivetrainSubsystem));
    // .perpetually() 'duplicates' the given command but makes .isFinished() always return false
    intakeSubsystem.setDefaultCommand(new IntakeStop(intakeSubsystem).perpetually());

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown(drivetrainSubsystem));
    setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp(drivetrainSubsystem));
    setJoystickButtonWhenPressed(xboxController, 1, new IntakeToggle(intakeSubsystem));
    setJoystickButtonWhenHeld(xboxController, 2, new IntakeIn(intakeSubsystem));
  }
  /* Xbox Controller Button Binding:
    Buttons:
      1 - A           6 - RightBump
      2 - B           7 - Back
      3 - X           8 - Start
      4 - Y           9 - LeftStickIn
      5 - LeftBump   10 - RightStickIn

    Axes:
      0 - LeftX       3 - RightTrig
      1 - LeftY       4 - RightX
      2 - LeftTrig    5 - RightY
  */

  public Command getAutonomousCommand() { return null; }

  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

 /**
   * WhileHeld constantly starts the command and repeatedly schedules while the
   * button is held. Cancels when button is released.
   */
  private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

  /**
   * WhenHeld starts the command once when the button is first pressed. Command
   * runs until button is released or command interrupted.
   */
  private void setJoystickButtonWhenHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenHeld(command);
  }


  private void setJoystickButtonToggleWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).toggleWhenPressed(command);
  }
}
