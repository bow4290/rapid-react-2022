package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Flags;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Shooter.ShootDiscard;
import frc.robot.commands.Shooter.ShootHigh;
import frc.robot.commands.Shooter.ShootManual;
import frc.robot.commands.Shooter.ShootStop;
import frc.robot.commands.Indexer.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Auto.AutoDriveForDistanceCommand;
import frc.robot.commands.Auto.AutoShootHigh;
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
import frc.robot.commands.Turret.DisableTurretCommand;
import frc.robot.commands.Turret.EnableTurretCommand;
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
  private ElevatorSubsystem elevatorSubsystem;

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
  private Command AutoPlop;
  private Command AutoTestingOnly;

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
      BallIdentification.Threshold red = new BallIdentification.Threshold(0.4, 1.0, 0.0, 0.18);
      BallIdentification.Threshold blueHigh = new BallIdentification.Threshold(0.0, 0.3, 0.2, 1.0);
      BallIdentification.Threshold blueLow = new BallIdentification.Threshold(0.0, 0.3, 0.26, 1.0);
      ballUpper = new BallIdentification(red, blueHigh, false);
      ballLower = new BallIdentification(red, blueLow, true);
      indexerSubsystem = new IndexerSubsystem();
      indexerSubsystem.setDefaultCommand(new DefaultIndexerCommand(indexerSubsystem, shooterSubsystem, intakeSubsystem, ballUpper, ballLower));
    }

    if (Flags.turret) {
      turretSubsystem = new TurretSubsystem(limelight);
      turretSubsystem.setDefaultCommand(new TurretCommand(limelight, turretSubsystem));
    }

    if (Flags.hood) {
      hoodSubsystem = new HoodSubsystem();
      hoodSubsystem.setDefaultCommand(new DefaultHoodCommand(limelight, hoodSubsystem, turretSubsystem));
    }

    if (Flags.elevator) elevatorSubsystem = new ElevatorSubsystem();

    autoCommands();

    chooser.setDefaultOption("3-Ball High Auto", AutoDriveCollectAndShoot2);
    chooser.addOption("2-Ball High Auto", AutoDriveCollectAndShoot);
    chooser.addOption("1-Ball High Auto", AutoDriveAndShoot);
    chooser.addOption("1-Ball Low Auto", AutoPlop);
    chooser.addOption("Drive and Collect", AutoDriveAndCollect);
    chooser.addOption("Drive Only", AutoDriveOnly);
    chooser.addOption("Do Nothing", AutoNothing);
    // chooser.addOption("TESTING ONLY", AutoTestingOnly);
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
    setJoystickButtonWhenHeld(xboxController, 6, new ShootHigh(limelight, shooterSubsystem, turretSubsystem));
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
    AutoPlop = 
    new SequentialCommandGroup(
      new ToggleTurretCommand(turretSubsystem),
      new ParallelRaceGroup(
          new ShootDiscard(shooterSubsystem),
          new WaitCommand(5)
        )
    );

    AutoDriveCollectAndShoot2 = 
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new IntakeDown(intakeSubsystem),
        new ParallelRaceGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, 40),
          new IntakeIn(intakeSubsystem)
        ),
        new ParallelRaceGroup(
          new AutoTurnRightAngleCommand(drivetrainSubsystem, 90.0 + 35.0),
          new IntakeIn(intakeSubsystem)
        ),
        new ParallelRaceGroup(
          new AutoShootHigh(limelight, shooterSubsystem, turretSubsystem, ballUpper, ballLower),
          new WaitCommand(2.5)
        ),
        // new AutoTurnLeftAngleCommand(drivetrainSubsystem, 25.0),
        new ParallelCommandGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, (117+6)),
          new ParallelRaceGroup(
            new IntakeIn(intakeSubsystem),
            new WaitCommand(2)
          )
        ),
        // new ShiftGearDown(drivetrainSubsystem),
        new AutoTurnRightAngleCommand(drivetrainSubsystem, 210.0 - (90.0 + 22.5)),
        new ParallelRaceGroup(
          new AutoShootHigh(limelight, shooterSubsystem, turretSubsystem, ballUpper, ballLower),
          new WaitCommand(1.5)
        )
        // new WaitCommand(0.20),
        // new AutoTurnLeftAngleCommand(drivetrainSubsystem, 135),
        // new WaitCommand(0.20),
        // new ShiftGearUp(drivetrainSubsystem),
        // new ParallelRaceGroup(
        //   new AutoDriveForDistanceCommand(drivetrainSubsystem, (130)),
        //   new IntakeIn(intakeSubsystem)
        // )
        );
    
    AutoDriveCollectAndShoot = 
    new SequentialCommandGroup(
      new ShiftGearDown(drivetrainSubsystem),
      new IntakeDown(intakeSubsystem),
      new ParallelRaceGroup(
        new AutoDriveForDistanceCommand(drivetrainSubsystem, 40),
        new IntakeIn(intakeSubsystem)
      ),
      new WaitCommand(0.20),
      new ParallelRaceGroup(
        new AutoTurnRightAngleCommand(drivetrainSubsystem, (150)),     
        new IntakeIn(intakeSubsystem)
      ),
      new WaitCommand(0.20),
      new ParallelRaceGroup(
        new ShootHigh(limelight, shooterSubsystem, turretSubsystem),
        new WaitCommand(5)
      )
    );

    AutoDriveAndShoot =
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new AutoDriveForDistanceCommand(drivetrainSubsystem, 40),
        new AutoTurnLeftAngleCommand(drivetrainSubsystem, 180),
        new ParallelRaceGroup(
          new ShootHigh(limelight, shooterSubsystem, turretSubsystem),
          new WaitCommand(5)
        )
      );
    
    AutoDriveAndCollect =
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new IntakeDown(intakeSubsystem),
        new ParallelRaceGroup(
          new AutoDriveForDistanceCommand(drivetrainSubsystem, 40),
          new IntakeIn(intakeSubsystem)
        )
      );
    
    AutoDriveOnly =
      new SequentialCommandGroup(
        new ShiftGearDown(drivetrainSubsystem),
        new AutoDriveForDistanceCommand(drivetrainSubsystem, 40)
      );
    
    AutoNothing = 
      null;

    AutoTestingOnly = 
    null;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }

  public Command teleopInitCommands(){
    return new ParallelCommandGroup(
            // new IntakeUp(inqtakeSubsystem),
            new ShiftGearDown(drivetrainSubsystem),
            new HoodRetractCommand(hoodSubsystem)
           );
  }

  public void periodic() {
    ballLower.update();
    ballUpper.update();
  }
}
