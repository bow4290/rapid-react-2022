package frc.robot;

public final class Constants {
  public static final class JoystickConstants {
    public static final int XBOX_CONTROLLER = 0;
    public static final int LEFT_JOYSTICK = 1;
    public static final int RIGHT_JOYSTICK = 2;
  }

  public static final class DriveConstants {
    public static final int rightMotor1Channel = 5;
    public static final int rightMotor2Channel = 6;
    public static final int leftMotor1Channel = 7;
    public static final int leftMotor2Channel = 8;
 

    public static final int gearShiftUpChannel = 4;
    public static final int gearShiftDownChannel = 5;

    public static final double targetLinearDistance = 60;
    private static final double stage1Ratio = 60/12;
    private static final double stage2Ratio = 28/28;
    private static final double lowSpreadRatio = 44/22;
    private static final double highSpreadRatio = 34/32;
    private static final double wheelDiameter = 3.75;
    private static final double wheelCircum = wheelDiameter*Math.PI;
    private static final int encoderCPR = 2048;
    private static final double lowCountsPerWheelRev = stage1Ratio * stage2Ratio * lowSpreadRatio * encoderCPR;
    private static final double highCountsPerWheelRev = stage1Ratio * stage2Ratio * highSpreadRatio * encoderCPR;
    private static final double lowGearTargetCounts = lowCountsPerWheelRev * targetLinearDistance * wheelCircum;
    private static final double highGearTargetCounts = highCountsPerWheelRev * targetLinearDistance * wheelCircum;
    public static final double encoderLowDistanceConversion = wheelCircum/(stage1Ratio * stage2Ratio * lowSpreadRatio * encoderCPR);
    public static final double encoderHighDistanceConversion = wheelCircum/(stage1Ratio * stage2Ratio * highSpreadRatio * encoderCPR);
  }

  public static final class ElevatorConstants {
    public static int elevatorClimbMotorChannel = 0;

    public static int elevatorLockChannel = 4;
    public static int elevatorUnlockChannel = 5; 

    public static double elevatorSpeed = 0.2;
    //elevatorSpeed is measured in percent output
  }

  public static final class HoodConstants {
    public static int hoodSolenoidExtendChannel = 2;
    public static int hoodSolenoidRetractChannel = 3;
    public static double hoodExtendDistance = 125;    // Distance in inches
  }

  public static final class IndexerConstants {
    public static int upperIndexMotorChannel = 0;
    public static int lowerIndexMotorChannel = 1;
    
    public static double bothShootingIndexSpeed = 0.5;
    public static double bothIntakingIndexSpeed = 0.5;
    public static double lowerIntakingIndexSpeed = 0.5;
  }

  public static final class IntakeConstants {
    public static int intakeMotorChannel = 13;

    public static int intakeUpChannel = 0;
    public static int intakeDownChannel = 1;
  }

  public static final class ShooterConstants {
    public static int shooterMotorChannel = 2;

    public static int shooterMotorVoltage = 11;

    // public static double discardSpeedRPM = 2500.0;

    public static double kF = 1023.0/20330.0;
    public static double kP = 0.01;
    public static double kI = 0;
    public static double kD = 0;
  }

  public static final class TurretConstants {
    public static int deviceID = 11;
    public static int forwardRotations = 30;
    public static int reverseRotations = -30;
  }

  public static final class LimelightConstants {
    public static final double h1 = 24;             // Distance from ground to limelight
    public static final double h2 = 8*12+8;         // Distance from ground to target (8'8")
    public static final double a1 = 30;             // Limelight mount angle (0 = facing forward, 90 = facing the ceiling)
  }

  public static final class Flags {
    public static final boolean drivetrain = true;
    public static final boolean intake = false;
    public static final boolean indexer = false;
    public static final boolean turret = false;
    public static final boolean hood = false;
  }
}
