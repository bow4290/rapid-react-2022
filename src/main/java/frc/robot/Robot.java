package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ShooterConstants;

import frc.robot.Constants.Flags;
import frc.robot.Constants.IndexerConstants;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  Joystick xboxController = new Joystick(0);
  JoystickButton xboxAButton = new JoystickButton(xboxController, 1);
  JoystickButton xboxBButton = new JoystickButton(xboxController, 2);
  JoystickButton xboxXButton = new JoystickButton(xboxController, 3);
  JoystickButton xboxYButton = new JoystickButton(xboxController, 4);

  double RPMSpeedA = 0;
  private WPI_VictorSPX victorMotor1 = new WPI_VictorSPX(IndexerConstants.upperIndexMotorChannel);
  private WPI_VictorSPX victorMotor2 = new WPI_VictorSPX(IndexerConstants.lowerIndexMotorChannel);

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    SmartDashboard.putNumber("RPM Speed A", RPMSpeedA);
    victorMotor1.configFactoryDefault();
    victorMotor1.setInverted(true);    
    victorMotor1.configVoltageCompSaturation(11);
    victorMotor1.enableVoltageCompensation(true);
    victorMotor2.configFactoryDefault();
    victorMotor2.setInverted(true);
    victorMotor2.configVoltageCompSaturation(11);    
    victorMotor2.enableVoltageCompensation(true);
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // SmartDashboard.putBoolean("Is Ball Red?", m_robotContainer.redBallColorSensor.isTarget());
    // SmartDashboard.putBoolean("Is Ball Blue?", m_robotContainer.blueBallColorSensor.isTarget());
    // SmartDashboard.putBoolean("Is Ball Team Color?", m_robotContainer.ball.isBallTeamColor());
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
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
