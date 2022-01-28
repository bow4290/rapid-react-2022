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

  public static final class TurretConstants {
    public static int deviceID = 1;
    public static int forwardRotations = 15;
    public static int reverseRotations = 15;
    public static int searchAngleStep = 5;
  }

  public static final class LimelightConstants {
    public static final double h1 = 0;  // Distance from ground to limelight
    public static final double h2 = 0;  // Distance from ground to target
    public static final double a1 = 0;  // Limelight mount angle
  }
}
