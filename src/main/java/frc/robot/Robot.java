
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
import frc.robot.Constants.ShooterConstants;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  Joystick xboxController = new Joystick(0);
  JoystickButton xboxAButton = new JoystickButton(xboxController, 1);
  JoystickButton xboxBButton = new JoystickButton(xboxController, 2);
  JoystickButton xboxXButton = new JoystickButton(xboxController, 3);
  JoystickButton xboxYButton = new JoystickButton(xboxController, 4);

  WPI_TalonFX myTalon = new WPI_TalonFX(ShooterConstants.myFalconChannel);

  double speedA = 0;
  double speedB = 0;
  double speedX = 0;
  double speedY = 0;

  private RobotContainer m_robotContainer;

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

    myTalon.config_kF(0, speedA);
    myTalon.config_kP(0, speedB);
    myTalon.config_kI(0, speedX);
    myTalon.config_kD(0, speedY);

    SmartDashboard.putNumber("Speed A", speedA);
    SmartDashboard.putNumber("Speed B", speedB);
    SmartDashboard.putNumber("Speed x", speedX);
    SmartDashboard.putNumber("Speed Y", speedY);

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
    double A = SmartDashboard.getNumber("Speed A", speedA);
    double B = SmartDashboard.getNumber("Speed B", speedB);
    double X = SmartDashboard.getNumber("Speed X", speedX);
    double Y = SmartDashboard.getNumber("Speed Y", speedY);

    if (A != speedA) speedA = A;
    if (B != speedB) speedB = B;
    if (X != speedX) speedX = X;
    if (Y != speedY) speedY = Y;

    // NOTE: Not actually RPM!
    double motorRPM = 0;

    if(xboxAButton.get()) {
      motorRPM = speedA;
    } else if(xboxBButton.get()) {
      motorRPM = speedB;
    } else if(xboxXButton.get()) {
      motorRPM = speedX;
    } else if(xboxYButton.get()) {
      motorRPM = speedY;
    }

    myTalon.set(TalonFXControlMode.PercentOutput, motorRPM);
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
