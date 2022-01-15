package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Drivetrain.Drive;
import frc.robot.commands.Drivetrain.ShiftGearDown;
import frc.robot.commands.Drivetrain.ShiftGearUp;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {

  public static Joystick joystickLeft;
  public static Joystick joystickRight;

  private DrivetrainSubsystem drivetrainSubsystem;

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);


  public RobotContainer() {

    joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
    joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);

    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new Drive(getLeftY(), getRightY(), drivetrainSubsystem));
    
    configureButtonBindings();
  }

  public double getLeftY(){
    return joystickLeft.getY();   // Joystick Y axis provides -1 for forward, so invert this
  }

  public double getLeftX(){
    return joystickLeft.getX();
  }

  public double getRightY(){
    return joystickRight.getY();  // Joystick Y axis provides -1 for forward, so invert this
  }

  public double getRightX(){
    return joystickRight.getX();
  }

  private void configureButtonBindings() {
    setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown());
    setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp());
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
}


