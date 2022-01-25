package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootLow;
import frc.robot.commands.Shooter.ShootStop;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.RevColorSensor;
import frc.robot.commands.Drivetrain.*;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick joystickLeft;
  public static Joystick joystickRight;
  public static Joystick xboxController;

  private Limelight limelight = new Limelight();

  public RevColorSensor redBallColorSensor;
  public RevColorSensor blueBallColorSensor;

  public BallIdentification ball;

  private ShooterSubsystem shooterSubsystem;
  private DrivetrainSubsystem drivetrainSubsystem;
  private IntakeSubsystem intakeSubsystem;

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public RobotContainer() {
    joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
    joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);
    xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

    redBallColorSensor = new RevColorSensor(80, 180, 50, 80, 15, 40, 0, 2048);
    blueBallColorSensor = new RevColorSensor(10, 70, 50, 100, 40, 100, 0, 2048);
    
    ball = new BallIdentification(redBallColorSensor, blueBallColorSensor);

    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new Drive(getLeftY(), getRightY(), drivetrainSubsystem));
    intakeSubsystem.setDefaultCommand(new IntakeStop(intakeSubsystem));
    shooterSubsystem.setDefaultCommand(new ShootStop(shooterSubsystem));

    configureButtonBindings();
  }

  public double getLeftY() { return joystickLeft.getY(); }

  public double getLeftX() { return joystickLeft.getX(); }

  public double getRightY() { return joystickRight.getY(); }

  public double getRightX() { return joystickRight.getX(); }

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
    setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown(drivetrainSubsystem));
    setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp(drivetrainSubsystem));
    setJoystickButtonWhenPressed(xboxController, 1, new IntakeToggle(intakeSubsystem));
    setJoystickButtonWhileHeld(xboxController, 2, new IntakeIn(intakeSubsystem));
    setJoystickButtonWhileHeld(xboxController, 5, new ShootLow(ball, limelight, shooterSubsystem));
    setJoystickButtonWhileHeld(xboxController, 6, new ShootHigh(ball, limelight, shooterSubsystem));
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
