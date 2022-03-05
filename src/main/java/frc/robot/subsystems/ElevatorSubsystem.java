// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  private WPI_TalonFX elevatorClimbMotor = new WPI_TalonFX(ElevatorConstants.elevatorClimbMotorChannel);
  private final DoubleSolenoid elevatorSolenoid;

  public enum ElevatorStatus { LOCKED, UNLOCKED }
  public static ElevatorStatus elevatorStatus;
  public ElevatorSubsystem() {
    elevatorClimbMotor.setNeutralMode(NeutralMode.Brake);
    elevatorClimbMotor.setInverted(TalonFXInvertType.Clockwise);

    elevatorSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ElevatorConstants.elevatorLockChannel, ElevatorConstants.elevatorUnlockChannel);
    elevatorStatus = ElevatorStatus.UNLOCKED;
  }

  public void extendElevator(double climbSpeed){

  }

  public void retractElevator(double climbSpeed){

  }

  public void stopElevator(double climbSpeed){

  }

  public void lockElevator(boolean isElevatorLocked){

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
