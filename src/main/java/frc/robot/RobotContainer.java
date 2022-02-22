package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootLow;
import frc.robot.commands.Shooter.ShootManual;
import frc.robot.commands.Shooter.ShootStop;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.RevColorSensor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick xboxController;

  private Limelight limelight = new Limelight();

  public BallIdentification ball;

  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  public RobotContainer() {
    xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

    shooterSubsystem.setDefaultCommand(new ShootStop(shooterSubsystem));

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
    setJoystickButtonWhenHeld(xboxController, 1, new ShootManual(shooterSubsystem));
  }

  public Command getAutonomousCommand() { return null; }

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
