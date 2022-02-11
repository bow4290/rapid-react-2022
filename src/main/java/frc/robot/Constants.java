package frc.robot;

public final class Constants {
  public static final class DriveConstants {
    public static int leftMotor1Channel = 7;
    public static int leftMotor2Channel = 8;
    public static int rightMotor1Channel = 5;
    public static int rightMotor2Channel = 6;

    public static int gearShiftUpChannel = 0;
    public static int gearShiftDownChannel = 1;

    public static double encoderDistanceConversion = 1.0;
  }

  public static final class JoystickConstants {
    public static int LEFT_JOYSTICK = 0;
    public static int RIGHT_JOYSTICK = 1;
    public static int XBOX_CONTROLLER = 2;
  }

  public static final class IntakeConstants {
    public static int intakeMotorChannel = 0;
    public static int intakeUpChannel = 6;
    public static int intakeDownChannel = 7;
  }

  public static final class LimelightConstants {
    public static final double h1 = 0;  // Distance from ground to limelight
    public static final double h2 = 0;  // Distance from ground to target
    public static final double a1 = 0;  // Limelight mount angle
  }
}
