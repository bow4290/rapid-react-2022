package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootLow;
import frc.robot.commands.Shooter.ShootManual;
import frc.robot.commands.Shooter.ShootStop;
import frc.robot.Constants.Flags;
import frc.robot.commands.Indexer.*;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.RevColorSensor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
  public static Joystick joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);
  public static Joystick xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);
  
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private IndexerSubsystem indexerSubsystem;

  public Limelight limelight = new Limelight();
  public RevColorSensor redBallColorSensor;
  public RevColorSensor blueBallColorSensor;

  public BallIdentification ballUpper;
  public BallIdentification ballLower;

  public RobotContainer() {

    shooterSubsystem.setDefaultCommand(new ShootStop(shooterSubsystem));

    indexerSubsystem = new IndexerSubsystem(ballUpper, ballLower);
    indexerSubsystem.setDefaultCommand(new DefaultIndexerCommand(indexerSubsystem, ballUpper, ballLower, () -> new JoystickButton(xboxController, 2).get()));

    if (Flags.colors) {
      redBallColorSensor = new RevColorSensor(80, 180, 50, 80, 15, 40, 0, 2048);
      blueBallColorSensor = new RevColorSensor(10, 70, 50, 100, 40, 100, 0, 2048);
      ballUpper = new BallIdentification(redBallColorSensor, blueBallColorSensor);
      ballLower = new BallIdentification(redBallColorSensor, blueBallColorSensor);
    }


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
    // setJoystickButtonWhileHeld(xboxController, 5, new ShootLow(ball, limelight, shooterSubsystem));
    // setJoystickButtonWhileHeld(xboxController, 6, new ShootHigh(ball, limelight, shooterSubsystem));
    setJoystickButtonWhileHeld(xboxController, 1, new ShootManual(shooterSubsystem));
  }


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
