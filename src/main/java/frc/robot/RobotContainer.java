package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.Flags;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootLow;
import frc.robot.commands.Shooter.ShootManual;
import frc.robot.commands.Shooter.ShootStop;
import frc.robot.commands.Indexer.*;
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
import frc.robot.commands.Turret.TurretCommand;

public class RobotContainer {
  public static Joystick joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
  public static Joystick joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);
  public static Joystick xboxController = new Joystick(JoystickConstants.XBOX_CONTROLLER);

  private HoodSubsystem hoodSubsystem;
  private IndexerSubsystem indexerSubsystem;
  public DrivetrainSubsystem drivetrainSubsystem;
  public TurretSubsystem turretSubsystem;

  private IntakeSubsystem intakeSubsystem;
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  public Limelight limelight = new Limelight();

  public RevColorSensor redBallColorSensorI2C;
  public RevColorSensor blueBallColorSensorI2C;
  public RevColorSensor redBallColorSensorMXP;
  public RevColorSensor blueBallColorSensorMXP;

  public RobotContainer() {
    if (Flags.drivetrain) {
      drivetrainSubsystem = new DrivetrainSubsystem();
      drivetrainSubsystem.setDefaultCommand(new Drive(() -> -joystickLeft.getY(), () -> -joystickRight.getY(), drivetrainSubsystem));
    }

    if (Flags.intake) {
      intakeSubsystem = new IntakeSubsystem();
    }

    if (Flags.indexer) {
      redBallColorSensorI2C  = new RevColorSensor(0.30, 1, 0.25, 0.425, 0.05, 0.25, 0, 2047, true);
      blueBallColorSensorI2C = new RevColorSensor(0.145, 0.275, 0.37, 0.47, 0.2, 1, 0, 2047, true);
      redBallColorSensorMXP  = new RevColorSensor(0.30, 1, 0.25, 0.425, 0.05, 0.25, 0, 2047, false);
      blueBallColorSensorMXP = new RevColorSensor(0.145, 0.275, 0.37, 0.47, 0.2, 1, 0, 2047, false);
      BallIdentification ballUpper = new BallIdentification(redBallColorSensorMXP, blueBallColorSensorMXP);
      BallIdentification ballLower = new BallIdentification(redBallColorSensorI2C, blueBallColorSensorI2C);
      indexerSubsystem = new IndexerSubsystem(ballUpper, ballLower);
      indexerSubsystem.setDefaultCommand(new DefaultIndexerCommand(indexerSubsystem, shooterSubsystem, ballUpper, ballLower, new JoystickButton(xboxController, 2)::get));
    }

    if (Flags.hood){
      hoodSubsystem.setDefaultCommand(new DefaultHoodCommand(limelight, hoodSubsystem));
      hoodSubsystem = new HoodSubsystem();
    }

    shooterSubsystem.setDefaultCommand(new ShootStop(shooterSubsystem));

    if (Flags.turret) {
      turretSubsystem = new TurretSubsystem();
      turretSubsystem.setDefaultCommand(new TurretCommand(limelight, turretSubsystem));
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
    if (Flags.drivetrain) {
      setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown(drivetrainSubsystem));
      setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp(drivetrainSubsystem));
    }

    if (Flags.intake) {
      setJoystickButtonWhenHeld(xboxController, 5, new IntakeIn(intakeSubsystem));
      setJoystickButtonWhenHeld(xboxController, 2, new IntakeToggle(intakeSubsystem));
    }

    if (Flags.indexer) {
      setJoystickButtonWhenHeld(xboxController, 1, new ManualIndexerCommand(indexerSubsystem));
    }

    setJoystickButtonWhenHeld(xboxController, 6, new ShootManual(shooterSubsystem));
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
