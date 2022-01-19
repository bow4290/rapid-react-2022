package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Drivetrain.ShiftGearDown;
import frc.robot.commands.Drivetrain.ShiftGearUp;
import frc.robot.commands.Intake.IntakeToggle;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {

  public static Joystick joystickLeft;
  public static Joystick joystickRight;
  public static Joystick xboxController;

  private DrivetrainSubsystem drivetrainSubsystem;

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);


  public RobotContainer() {

    joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
    joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);
    xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new Drive(getLeftY(), getRightY(), drivetrainSubsystem));
    
    configureButtonBindings();
  }

  public double getLeftY(){
    return joystickLeft.getY();
  }

  public double getLeftX(){
    return joystickLeft.getX();
  }

  public double getRightY(){
    return joystickRight.getY();
  }

  public double getRightX(){
    return joystickRight.getX();
  }

/* Xbox Controller Button Binding: 
Left Bumper= 5 Right Bumper= 6 
Stick Left= 9 Stick Right= 10 
A= 1 B= 2 X= 3 Y= 4 
Back= 7 Start= 8

Axis
Left X= 0 Right X= 4 Left Y= 1 Right Y= 5
Left Trigger= 2 Right Trigger= 3
*/
  private void configureButtonBindings() {
    setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown());
    setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp());
    setJoystickButtonToggleWhenPressed(xboxController, 1, new IntakeToggle());
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }

   /** WhenPressed runs the command once at the moment the button is pressed. */
  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  /** WhileHeld constantly starts the command and repeatedly schedules while the button is held. Cancels when button is released. */
  private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whileHeld(command);
  }

  /** WhenHeld starts the command once when the button is first pressed. Command runs until button is released or command interrupted. */
  private void setJoystickButtonWhenHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenHeld(command);
  }

  private void setJoystickButtonToggleWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).toggleWhenPressed(command);
  }
}


