// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Drivetrain.Drive;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class RobotContainer {

  public static Joystick joystickLeft;
  public static Joystick joystickRight;

  private DrivetrainSubsystem drivetrainSubsystem;

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);


  public RobotContainer() {

    joystickLeft = new Joystick(JoystickConstants.LEFT_JOYSTICK);
    joystickRight = new Joystick(JoystickConstants.RIGHT_JOYSTICK);

    drivetrainSubsystem = new DrivetrainSubsystem();

    drivetrainSubsystem.setDefaultCommand(new Drive(getLeftY(), getRightY(), drivetrainSubsystem));
    
    configureButtonBindings();
  }

  public double getLeftY(){
    return joystickLeft.getY();   // Joystick Y axis provides -1 for forward, so invert this
  }

  public double getLeftX(){
    return joystickLeft.getX();
  }

  public double getRightY(){
    return joystickRight.getY();  // Joystick Y axis provides -1 for forward, so invert this
  }

  public double getRightX(){
    return joystickRight.getX();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
