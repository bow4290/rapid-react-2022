package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Indexer.*;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.RevColorSensor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick xboxController;

  public RevColorSensor redBallColorSensor;
  public RevColorSensor blueBallColorSensor;

  public BallIdentification ballUpper;
  public BallIdentification ballLower;

  private IndexerSubsystem indexerSubsystem = new IndexerSubsystem();

  public RobotContainer() {
    xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

    redBallColorSensor = new RevColorSensor(80, 180, 50, 80, 15, 40, 0, 2048);
    blueBallColorSensor = new RevColorSensor(10, 70, 50, 100, 40, 100, 0, 2048);
    
    ballUpper = new BallIdentification(redBallColorSensor, blueBallColorSensor);
    ballLower = new BallIdentification(redBallColorSensor, blueBallColorSensor);

    indexerSubsystem.setDefaultCommand(new DefaultIndexerCommand(indexerSubsystem, ballUpper, ballLower, () -> new JoystickButton(xboxController, 2).get()));
    
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
  private void configureButtonBindings() {}

  public Command getAutonomousCommand() { return null; }
}
