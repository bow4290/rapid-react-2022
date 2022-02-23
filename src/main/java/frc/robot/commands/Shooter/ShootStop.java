package frc.robot.commands.Shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootStop extends CommandBase {
  private ShooterSubsystem shooterSubsystem;

  public ShootStop(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    //shooterSubsystem.shoot(0.0);
  }

  @Override
  public void execute() {
    ShooterSubsystem.shooterMotor.set(ControlMode.Velocity, 0);
  }
  
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}