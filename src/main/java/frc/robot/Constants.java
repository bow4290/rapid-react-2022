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

  public static final class ClimberConstants{
    public static int frontClimberMotorChannel = 4;
    public static int rearClimberMotorChannel = 10;

    public static int rearClimberSolenoidUpChannel = 1;
    public static int rearClimberSolenoidDownChannel = 0;

    public static double rearClimberExtendDistance = 30; //Distance converted to inches 
    public static double rearClimberRetractDistance = 1; //Extra inch allowance to compensate for encoder
    public static double rearClimberEncoderDistanceConversion = 1.0;
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

  public static final class LimelightConstants {
    public static final double h1 = 0;  // Distance from ground to limelight
    public static final double h2 = 0;  // Distance from ground to target
    public static final double a1 = 0;  // Limelight mount angle
  }
}
