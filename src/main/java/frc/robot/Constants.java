package frc.robot;

public final class Constants {
  public static final class DriveConstants {
    public static int leftMotor1Channel = 0;
    public static int leftMotor2Channel = 1;
    public static int rightMotor1Channel = 2;
    public static int rightMotor2Channel = 3;

    public static int gearShiftUpChannel = 6;
    public static int gearShiftDownChannel = 9;

    public static double encoderDistanceConversion = 1.0;
  }

  public static final class JoystickConstants {
    public static int LEFT_JOYSTICK = 0;
    public static int RIGHT_JOYSTICK = 1;
    public static int XBOX_CONTROLLER = 2;
  }

  public static final class IntakeConstants {
    public static int intakeMotorChannel = 0;
    public static int intakeUpChannel = 0;
    public static int intakeDownChannel = 0;
  }

  public static final class HoodConstants {
    public static int hoodSolenoidExtendChannel = 1;
    public static int hoodSolenoidRetractChannel = 0;
    public static double hoodExtendDistance = 72;    // Distance in inches
  }

  public static final class LimelightConstants {
    public static final double h1 = 24;             // Distance from ground to limelight
    public static final double h2 = 8*12+8;         // Distance from ground to target (8'8")
    public static final double a1 = 30;             // Limelight mount angle (0 = facing forward, 90 = facing the ceiling)
  }
}
