package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.RevColorSensor;
import frc.robot.commands.Hood.DefaultHoodCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick xboxController;

  public RevColorSensor redBallColorSensor;
  public RevColorSensor blueBallColorSensor;

  public BallIdentification ball;

  public Limelight limelight = new Limelight();

  private HoodSubsystem hoodSubsystem = new HoodSubsystem();

  public RobotContainer() {
    xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

    redBallColorSensor = new RevColorSensor(80, 180, 50, 80, 15, 40, 0, 2048);
    blueBallColorSensor = new RevColorSensor(10, 70, 50, 100, 40, 100, 0, 2048);
    
    ball = new BallIdentification(redBallColorSensor, blueBallColorSensor);
  
    hoodSubsystem.setDefaultCommand(new DefaultHoodCommand(limelight, hoodSubsystem));

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
  }

  public Command getAutonomousCommand() { return null; }
}
