// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private WPI_VictorSPX victorMotor1 = new WPI_VictorSPX(DriveConstants.VictorSPX1CANChannel);
  private WPI_VictorSPX victorMotor2 = new WPI_VictorSPX(DriveConstants.VictorSPX2CANChannel);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    victorMotor1.configFactoryDefault();
    victorMotor1.setInverted(true);    
    victorMotor1.configVoltageCompSaturation(11);
    victorMotor1.enableVoltageCompensation(true);
    victorMotor2.configFactoryDefault();
    victorMotor2.setInverted(true);
    victorMotor2.configVoltageCompSaturation(11);    
    victorMotor2.enableVoltageCompensation(true);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // SmartDashboard.putNumber("N Velocity (RPM): ", myTalon.getSelectedSensorVelocity()*600/2048);
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
    if (xboxYButton.get()) {
      victorMotor1.set(ControlMode.PercentOutput, 1.0); 
      victorMotor2.set(ControlMode.PercentOutput, 1.0);
    } else if (xboxXButton.get()) {
      victorMotor1.set(ControlMode.PercentOutput, 0.75); 
      victorMotor2.set(ControlMode.PercentOutput, 0.75);
    }else if (xboxBButton.get()) {
      victorMotor1.set(ControlMode.PercentOutput, 0.25); 
      victorMotor2.set(ControlMode.PercentOutput, 0.25);
    } else if (xboxAButton.get()) {
      victorMotor1.set(ControlMode.PercentOutput, 0.5); 
      victorMotor2.set(ControlMode.PercentOutput, 0.5);
    } else {
      victorMotor1.set(ControlMode.PercentOutput, 0); 
      victorMotor2.set(ControlMode.PercentOutput, 0);
    }
    
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
