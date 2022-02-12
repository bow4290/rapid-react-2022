package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Turret.TurretCommand;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.*;

public class RobotContainer {
  public static Joystick joystickLeft;
  public static Joystick joystickRight;
  public static Joystick xboxController;

  public Limelight limelight;

  public TurretSubsystem turretSubsystem;

  public RobotContainer() {
    limelight = new Limelight();

    turretSubsystem = new TurretSubsystem();
    turretSubsystem.setDefaultCommand(new TurretCommand(limelight, turretSubsystem));
  }
}
