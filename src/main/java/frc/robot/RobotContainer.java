package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Climber.*;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick xboxController;

  private ClimberFrontSubsystem climberFrontSubsystem =  new ClimberFrontSubsystem();
  private ClimberRearSubsystem climberRearSubsystem = new ClimberRearSubsystem();

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //public final ExtendRearClimberSolenoid climberLockCommand = new ExtendRearClimberSolenoid(climberRearSubsystem);

  public RobotContainer() {
    xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

    climberFrontSubsystem.setDefaultCommand(new StopFrontClimber(climberFrontSubsystem));
    climberRearSubsystem.setDefaultCommand(new StopRearClimber(climberRearSubsystem));

    configureButtonBindings();
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
  private void configureButtonBindings() {
    setJoystickButtonWhenHeld(xboxController, 3, new ExtendFrontClimber(0.75, climberFrontSubsystem));
    setJoystickButtonWhenHeld(xboxController, 4, new RetractFrontClimber(0.4, climberFrontSubsystem));
    setJoystickButtonWhenHeld(xboxController, 5, new RetractRearClimber(0.5, climberRearSubsystem));
    setJoystickButtonWhenHeld(xboxController, 6, new ExtendRearClimber(0.5, climberRearSubsystem));
    setJoystickButtonWhenPressed(xboxController, 9, new ExtendRearClimberSolenoid(climberRearSubsystem));
  }

  public Command getAutonomousCommand() { return m_autoCommand; }

  /** WhenPressed runs the command once at the moment the button is pressed. */
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
