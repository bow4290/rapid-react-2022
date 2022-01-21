// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

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

  TalonFX myTalon = new TalonFX(DriveConstants.myFalconChannel);

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    myTalon.set(ControlMode.PercentOutput, 0.0);
    myTalon.setInverted(false);
    myTalon.setSensorPhase(false);
    myTalon.configVoltageCompSaturation(11);
    myTalon.enableVoltageCompensation(true);
    
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

    double motorPercentOutput = 0;

    if(xboxAButton.get()) {
      motorPercentOutput = 1.0;
    } else if(xboxBButton.get()) {
      motorPercentOutput = 0.75;
    } else if(xboxXButton.get()) {
      motorPercentOutput = 0.5;
    } else if(xboxYButton.get()) {
      motorPercentOutput = 0.25;
    }

    myTalon.set(ControlMode.PercentOutput, motorPercentOutput);

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
