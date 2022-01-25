package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class TurretSubsystem extends SubsystemBase {

  public static enum TurretRotation { CLOCKWISE, COUNTERCLOCKWISE }

  //TODO add motor

  public TurretSubsystem() {
  }

  public void turn(double turretSpeedRPM, TurretRotation rotation) {
    // TODO: Set motor
  }

  @Override
  public void periodic() {
  }
}
