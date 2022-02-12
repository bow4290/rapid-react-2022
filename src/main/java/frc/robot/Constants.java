package frc.robot;

public final class Constants {
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
    public static int XBOX_CONTROLLER = 2;
  }

  public static final class LimelightConstants {
    public static final double h1 = 0;  // Distance from ground to limelight
    public static final double h2 = 0;  // Distance from ground to target
    public static final double a1 = 0;  // Limelight mount angle
  }
}
