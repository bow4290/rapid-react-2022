
package frc.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.sensors.RevColorSensor;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private RevColorSensor redBallColorSensor;
  private RevColorSensor blueBallColorSensor;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    redBallColorSensor = new RevColorSensor(80, 180, 50, 80, 15, 40, 0, 2048);
    blueBallColorSensor = new RevColorSensor(10, 70, 50, 100, 40, 100, 0, 2048);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putBoolean("Is Red Ball?", redBallColorSensor.isTarget());
    SmartDashboard.putBoolean("Is Blue Ball?", blueBallColorSensor.isTarget());
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
    //if(DriverStation.getMatchTime() < 3){
    //  m_robotContainer.climberLockCommand.schedule();
    //}
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
