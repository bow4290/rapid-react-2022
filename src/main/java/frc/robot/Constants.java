package frc.robot;

public final class Constants {
  public static final class DriveConstants {
    public static final int leftMotor1Channel = 7;
    public static final int leftMotor2Channel = 8;
    public static final int rightMotor1Channel = 5;
    public static final int rightMotor2Channel = 6;

    public static final int gearShiftUpChannel = 0;
    public static final int gearShiftDownChannel = 1;

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

  public static final class IndexerConstants {
    public static int upperIndexMotorChannel = 0;
    public static int lowerIndexMotorChannel = 0;
    
    public static double bothShootingIndexSpeed = 0.5;
    public static double bothIntakingIndexSpeed = 0.5;
    public static double lowerIntakingIndexSpeed = 0.5;
  }

  public static final class JoystickConstants {
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int XBOX_CONTROLLER = 2;
  }

  public static final class IntakeConstants {
    public static final int intakeMotorChannel = 44;
    public static final int intakeUpChannel = 6;
    public static final int intakeDownChannel = 7;
  }

  public static final class LimelightConstants {
    public static final double h1 = 0;  // Distance from ground to limelight
    public static final double h2 = 0;  // Distance from ground to target
    public static final double a1 = 0;  // Limelight mount angle
  }

  public static final class Flags {
    public static final boolean drivetrain = true;
    public static final boolean intake = true;
    public static final boolean colors = true;
  }
}
