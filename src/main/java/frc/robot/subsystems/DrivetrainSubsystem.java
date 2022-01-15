package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX; // Falcon500 onboard speed controller
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.InvertType;
import frc.robot.Constants.DriveConstants;

public class DrivetrainSubsystem extends SubsystemBase {
  private WPI_TalonFX leftMotor1 = new WPI_TalonFX(DriveConstants.leftMotor1Channel);
  private WPI_TalonFX leftMotor2 = new WPI_TalonFX(DriveConstants.leftMotor2Channel);
  private WPI_TalonFX rightMotor1 = new WPI_TalonFX(DriveConstants.rightMotor1Channel);
  private WPI_TalonFX rightMotor2 = new WPI_TalonFX(DriveConstants.rightMotor2Channel);

  private final DifferentialDrive drivetrain = new DifferentialDrive(leftMotor1, rightMotor1);

  public DrivetrainSubsystem() {
    // NOTE: Should we use NeutralMode.Brake?
	// see https://github.com/Yeti-Robotics/olaf-java-2021/blob/79e1bbd6b482c05b18b97d5423d9376dac4c2597/src/main/java/frc/robot/subsystems/DrivetrainSubsystem.java#L51

    leftMotor1.setInverted(false);

    leftMotor2.follow(leftMotor1);
    leftMotor2.setInverted(InvertType.FollowMaster);

    rightMotor1.setInverted(true);

    rightMotor2.follow(rightMotor1);
    rightMotor2.setInverted(InvertType.FollowMaster);
  }

  public void drive(double forward, double rotation) {
    drivetrain.arcadeDrive(forward, rotation);
  }

  public void stopDrive(){
    drive(0,0);
  }
}
