package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Flags;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootManual;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Auto.AutoDriveForDistanceCommand;
import frc.robot.commands.Auto.AutoTurnLeftAngleCommand;
import frc.robot.commands.Auto.AutoTurnRightAngleCommand;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Elevator.*;
import frc.robot.sensors.BallIdentification;
import frc.robot.sensors.Limelight;
import frc.robot.sensors.RevColorSensor;
import frc.robot.commands.Hood.DefaultHoodCommand;
import frc.robot.commands.Hood.HoodRetractCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Turret.ToggleTurretCommand;
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
  private ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();

  public Limelight limelight = new Limelight();

  public RevColorSensor redBallColorSensorI2C;
  public RevColorSensor blueBallColorSensorI2C;
  public RevColorSensor redBallColorSensorMXP;
  public RevColorSensor blueBallColorSensorMXP;

  public BallIdentification ballUpper;
  public BallIdentification ballLower;

  private Command AutoDriveCollectAndShoot2;
  private Command AutoDriveCollectAndShoot;
  private Command AutoDriveAndShoot;
  private Command AutoDriveAndCollect;
  private Command AutoDriveOnly;
  private Command AutoNothing;

  SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    if (Flags.drivetrain) {
      drivetrainSubsystem = new DrivetrainSubsystem();
      drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(() -> -joystickLeft.getY(), () -> -joystickRight.getY(), drivetrainSubsystem));
    }

    if (Flags.intake) {
      intakeSubsystem = new IntakeSubsystem();
    }

    if (Flags.indexer) {
      // Color values are from 0.00 - 1.00 (0% to 100% of the measured color).
      redBallColorSensorI2C  = new RevColorSensor(0.45, 1.00, 0.00, 1.00, 0.00, 0.15, 0, 2047, true);
      blueBallColorSensorI2C = new RevColorSensor(0.00, 0.26, 0.00, 1.00, 0.275, 1.00, 0, 2047, true);
      redBallColorSensorMXP  = new RevColorSensor(0.45, 1.00, 0.00, 1.00, 0.00, 0.15, 0, 2047, false);
      blueBallColorSensorMXP = new RevColorSensor(0.00, 0.26, 0.00, 1.00, 0.275, 1.00, 0, 2047, false);
      ballUpper = new BallIdentification(redBallColorSensorMXP, blueBallColorSensorMXP);
      ballLower = new BallIdentification(redBallColorSensorI2C, blueBallColorSensorI2C);
      indexerSubsystem = new IndexerSubsystem();
      indexerSubsystem.setDefaultCommand(new DefaultIndexerCommand(indexerSubsystem, shooterSubsystem, intakeSubsystem, ballUpper, ballLower));
    }

    if (Flags.turret) {
      turretSubsystem = new TurretSubsystem(limelight);
      turretSubsystem.setDefaultCommand(new TurretCommand(limelight, turretSubsystem));
    }

    if (Flags.hood){
      hoodSubsystem = new HoodSubsystem();
      hoodSubsystem.setDefaultCommand(new DefaultHoodCommand(limelight, hoodSubsystem, turretSubsystem));
    }

    autoCommands();

    chooser.setDefaultOption("FULL AUTO", AutoDriveCollectAndShoot);
    chooser.addOption("FULLER AUTO", AutoDriveCollectAndShoot2);
    chooser.addOption("Drive and Shoot", AutoDriveAndShoot);
    chooser.addOption("Drive and Collect", AutoDriveAndCollect);
    chooser.addOption("Drive Only", AutoDriveOnly);
    chooser.addOption("Do Nothing", AutoNothing);
    SmartDashboard.putData(chooser);

    configureButtonBindings();
  }


  /* Xbox Controller Button Bindings:
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
    // setJoystickButtonWhenHeld(xboxController, 5, new ManualTurretClockwiseCommand(turretSubsystem));
    // setJoystickButtonWhenHeld(xboxController, 6, new ManualTurretCounterClockwiseCommand(turretSubsystem));

    if (Flags.drivetrain) {
      setJoystickButtonWhenPressed(joystickLeft, 1, new ShiftGearDown(drivetrainSubsystem));
      setJoystickButtonWhenPressed(joystickRight, 1, new ShiftGearUp(drivetrainSubsystem));
    }

    if (Flags.intake) {
      setJoystickButtonWhenHeld(xboxController, 5, new IntakeIn(intakeSubsystem));
      setJoystickButtonWhenHeld(xboxController, 2, new IntakeToggle(intakeSubsystem));
    }

    if (Flags.indexer) {
      setJoystickButtonWhenHeld(xboxController, 9, new ManualIndexerCommand(indexerSubsystem));
      setJoystickButtonWhenHeld(xboxController, 10, new ReverseIndexerCommand(indexerSubsystem));
    }

    setJoystickButtonWhenHeld(xboxController, 3, new ToggleTurretCommand(turretSubsystem));

    setJoystickButtonWhenHeld(xboxController, 4, new ElevatorUpCommand(elevatorSubsystem));
    setJoystickButtonWhenHeld(xboxController, 1, new ElevatorDownCommand(elevatorSubsystem));

    setJoystickButtonWhenHeld(xboxController, 8, new ShootManual(shooterSubsystem));
    setJoystickButtonWhenHeld(xboxController, 6, new ShootHigh(ballUpper, limelight, shooterSubsystem, turretSubsystem));
  }

  private void setJoystickButtonWhenPressed(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenPressed(command);
  }

  /*
   * WhileHeld starts the command. If the command ends whilst the button is still pressed,
   * the command is rescheduled. The command is cancelled when button is released.
   */
  // private void setJoystickButtonWhileHeld(Joystick joystick, int button, CommandBase command) {
  //   new JoystickButton(joystick, button).whileHeld(command);
  // }

  /**
   * WhenHeld starts the command. If the command ends whilst the button is still pressed,
   * the command is not rescheduled. The command is cancelled when button is released.
   */
  private void setJoystickButtonWhenHeld(Joystick joystick, int button, CommandBase command) {
    new JoystickButton(joystick, button).whenHeld(command);
  }

  private void autoCommands(){
    AutoDriveCollectAndShoot2 = 
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new IntakeDown(intakeSubsystem),
        new ParallelRaceGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, 40),
          new IntakeIn(intakeSubsystem)
        ),
        new WaitCommand(0.20),
        new AutoTurnRightAngleCommand(drivetrainSubsystem, 90.0 + 32.25 + 15.0),
        new ParallelRaceGroup(
          new ShootHigh(ballUpper, limelight, shooterSubsystem, turretSubsystem),
          new WaitCommand(2.5)
        ),
        new AutoTurnLeftAngleCommand(drivetrainSubsystem, 15.0),
        new ParallelRaceGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, 117),
          new IntakeIn(intakeSubsystem)
        ),
        new WaitCommand(0.20),
        new AutoTurnRightAngleCommand(drivetrainSubsystem, 180.0 - (90.0 + 32.25)),
        new ParallelRaceGroup(
          new ShootHigh(ballUpper, limelight, shooterSubsystem, turretSubsystem),
          new WaitCommand(5)
        )
      );
    
    AutoDriveCollectAndShoot = 
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new IntakeDown(intakeSubsystem),
        new ParallelRaceGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, 60),
          new IntakeIn(intakeSubsystem)
        ),
        new WaitCommand(0.20),
        new AutoTurnLeftAngleCommand(drivetrainSubsystem, 180),
        new ParallelRaceGroup(
          new ShootHigh(ballUpper, limelight, shooterSubsystem, turretSubsystem),
          new WaitCommand(5)
        )
      );

    AutoDriveAndShoot =
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new AutoDriveForDistanceCommand(drivetrainSubsystem, 60),
        new AutoTurnLeftAngleCommand(drivetrainSubsystem, 180),
        new ParallelRaceGroup(
          new ShootHigh(ballUpper, limelight, shooterSubsystem, turretSubsystem),
          new WaitCommand(5)
        )
      );
    
    AutoDriveAndCollect =
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new IntakeDown(intakeSubsystem),
        new ParallelRaceGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, 60),
          new IntakeIn(intakeSubsystem)
        )
      );
    
    AutoDriveOnly =
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new AutoDriveForDistanceCommand(drivetrainSubsystem, 60)
      );
    
    AutoNothing = null;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }

  public Command teleopInitCommands(){
    return new ParallelCommandGroup(
            new IntakeUp(intakeSubsystem),
            new ShiftGearDown(drivetrainSubsystem),
            new HoodRetractCommand(hoodSubsystem)
           );
  }
}
