package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootLow;
import frc.robot.commands.Shooter.ShootManual;
import frc.robot.commands.Shooter.ShootStop;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Drivetrain.*;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.RevColorSensor;
import frc.robot.commands.Hood.DefaultHoodCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  public static Joystick joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
  public static Joystick joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);
  public static Joystick xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

  public BallIdentification ballUpper;
  public BallIdentification ballLower;
  
  DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private HoodSubsystem hoodSubsystem = new HoodSubsystem();
  private IndexerSubsystem indexerSubsystem = new IndexerSubsystem(ballUpper, ballLower);

  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
 
  public Limelight limelight = new Limelight();

  public RevColorSensor redBallColorSensorI2C;
  public RevColorSensor blueBallColorSensorI2C;
  public RevColorSensor redBallColorSensorMXP;
  public RevColorSensor blueBallColorSensorMXP;

  public RobotContainer() {
    drivetrainSubsystem.setDefaultCommand(new Drive(() -> -joystickLeft.getY(), () -> -joystickRight.getY(), drivetrainSubsystem));
    hoodSubsystem.setDefaultCommand(new DefaultHoodCommand(limelight, hoodSubsystem));
    indexerSubsystem.setDefaultCommand(new DefaultIndexerCommand(indexerSubsystem, ballUpper, ballLower, new JoystickButton(xboxController, 2)::get));
    intakeSubsystem.setDefaultCommand(new IntakeStop(intakeSubsystem));
    shooterSubsystem.setDefaultCommand(new ShootStop(shooterSubsystem));

    redBallColorSensorI2C  = new RevColorSensor(0.30, 1, 0.25, 0.425, 0.05, 0.25, 0, 2047, true);
    blueBallColorSensorI2C = new RevColorSensor(0.145, 0.275, 0.37, 0.47, 0.2, 1, 0, 2047, true);
    redBallColorSensorMXP  = new RevColorSensor(0.30, 1, 0.25, 0.425, 0.05, 0.25, 0, 2047, false);
    blueBallColorSensorMXP = new RevColorSensor(0.145, 0.275, 0.37, 0.47, 0.2, 1, 0, 2047, false);
    ballUpper = new BallIdentification(redBallColorSensorMXP, blueBallColorSensorMXP);
    ballLower = new BallIdentification(redBallColorSensorI2C, blueBallColorSensorI2C);
    
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
    setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown(drivetrainSubsystem));
    setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp(drivetrainSubsystem));
    setJoystickButtonWhileHeld(xboxController, 6, new ShootManual(shooterSubsystem));
    setJoystickButtonWhileHeld(xboxController, 1, new ManualIndexerCommand(indexerSubsystem));
    setJoystickButtonWhileHeld(xboxController, 5, new IntakeIn(intakeSubsystem));  
    setJoystickButtonWhileHeld(xboxController, 2, new IntakeToggle(intakeSubsystem));
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
