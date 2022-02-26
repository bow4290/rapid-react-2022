package frc.robot;

public final class Constants {
  public static final class TelescopeConstants{
    public static int telescopeMotorChannel = 4;

    //TODO Find realistic softLimits (Most likely rotations)
    public static float telescopeExtendSoftLimit = 3000.0f; //Distance converted to inches 
    public static float telescopeRetractSoftLimit = -10.0f; //Extra inch allowance to compensate for encoder
    public static double telescopeEncoderDistanceConversion = 1.0;
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
