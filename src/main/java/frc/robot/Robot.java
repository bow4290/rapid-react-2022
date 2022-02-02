// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.*;

public class Robot extends TimedRobot {
  Joystick xboxController = new Joystick(0);
  JoystickButton xboxAButton = new JoystickButton(xboxController, 1);
  JoystickButton xboxBButton = new JoystickButton(xboxController, 2);
  JoystickButton xboxXButton = new JoystickButton(xboxController, 3);
  JoystickButton xboxYButton = new JoystickButton(xboxController, 4);

  WPI_TalonFX myTalon = new WPI_TalonFX(DriveConstants.myFalconChannel);

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  double kF = 1023.0/20330.0;
  double kP = 0.01;
  double kI = 0;
  double kD = 0;

  @Override
  public void robotInit() {
    myTalon.configFactoryDefault();
    myTalon.configVoltageCompSaturation(11);
    myTalon.enableVoltageCompensation(true);
    myTalon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    myTalon.setNeutralMode(NeutralMode.Coast);
    myTalon.configNominalOutputForward(0);
    myTalon.configNominalOutputReverse(0);
    myTalon.configPeakOutputForward(1);
    myTalon.configPeakOutputReverse(-1);
    myTalon.setInverted(TalonFXInvertType.Clockwise);

    myTalon.config_kF(0, kF);
    myTalon.config_kP(0, kP);
    myTalon.config_kI(0, kI);
    myTalon.config_kD(0, kD);

    SmartDashboard.putNumber("kF", kF);
    SmartDashboard.putNumber("kP", kP);
    SmartDashboard.putNumber("kI", kI);
    SmartDashboard.putNumber("kD", kD);

    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Talon Velocity (RPM): ", myTalon.getSelectedSensorVelocity()*600/2048);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    double f = SmartDashboard.getNumber("kF", kF);
    double p = SmartDashboard.getNumber("kP", kP);
    double i = SmartDashboard.getNumber("kI", kI);
    double d = SmartDashboard.getNumber("kD", kD);

    if (f != kF) { myTalon.config_kF(0, f); kF = f; }
    if (p != kP) { myTalon.config_kP(0, p); kP = p; }
    if (i != kI) { myTalon.config_kI(0, i); kI = i; }
    if (d != kD) { myTalon.config_kD(0, d); kD = d; }

    double motorRPM = 0;

    if(xboxAButton.get()) {
      motorRPM = 4000.0;
    } else if(xboxBButton.get()) {
      motorRPM = 3000.0;
    } else if(xboxXButton.get()) {
      motorRPM = 2000.0;
    } else if(xboxYButton.get()) {
      motorRPM = 1000.0;
    }

    myTalon.set(TalonFXControlMode.Velocity, motorRPM*2048/600);

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
