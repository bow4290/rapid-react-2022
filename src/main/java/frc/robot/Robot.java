// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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

  WPI_TalonFX myTalon1 = new WPI_TalonFX(DriveConstants.myFalcon1Channel);
  WPI_TalonFX myTalon2 = new WPI_TalonFX(DriveConstants.myFalcon2Channel);

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    myTalon1.configFactoryDefault();
    myTalon1.configVoltageCompSaturation(11);
    myTalon1.enableVoltageCompensation(true);
    myTalon1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    myTalon1.setNeutralMode(NeutralMode.Coast);
    myTalon1.configNominalOutputForward(0);
    myTalon1.configNominalOutputReverse(0);
    myTalon1.configPeakOutputForward(1);
    myTalon1.configPeakOutputReverse(-1);
    myTalon1.setInverted(TalonFXInvertType.Clockwise);
    
    myTalon1.config_kF(0, 1023.0/20330.0);
    myTalon1.config_kP(0, 0);
    myTalon1.config_kI(0, 0);
    myTalon1.config_kD(0, 0);

    myTalon2.configFactoryDefault();
    myTalon2.configVoltageCompSaturation(11);
    myTalon2.enableVoltageCompensation(true);
    myTalon2.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
    myTalon2.setNeutralMode(NeutralMode.Coast);
    myTalon2.configNominalOutputForward(0);
    myTalon2.configNominalOutputReverse(0);
    myTalon2.configPeakOutputForward(1);
    myTalon2.configPeakOutputReverse(-1);
    myTalon2.setInverted(TalonFXInvertType.Clockwise);
    
    myTalon2.config_kF(0, 1023.0/20330.0);
    myTalon2.config_kP(0, 0);
    myTalon2.config_kI(0, 0);
    myTalon2.config_kD(0, 0);
    
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Talon Velocity (RPM): ", myTalon1.getSelectedSensorVelocity()*600/2048);
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

    double motorRPM = 0;

    if(xboxAButton.get()) {
      //motorRPM = 2000.0;
    } else if(xboxBButton.get()) {
      //motorRPM = 1000.0;
    } else if(xboxXButton.get()) {
      motorRPM = 500.0;
    } else if(xboxYButton.get()) {
      motorRPM = -500.0;
    }

    myTalon1.set(TalonFXControlMode.Velocity, motorRPM*2048/600);
    myTalon2.set(TalonFXControlMode.Velocity, motorRPM*2048/600);

  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
