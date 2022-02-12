package frc.robot;

public final class Constants {
  public static final class JoystickConstants {
    public static int XBOX_CONTROLLER = 2;
  }

  public static final class HoodConstants {
    public static int hoodSolenoidExtendChannel = 6;
    public static int hoodSolenoidRetractChannel = 7;
    public static double hoodExtendDistance = 125;    // Distance in inches
  }

  public static final class LimelightConstants {
    public static final double h1 = 24;             // Distance from ground to limelight
    public static final double h2 = 8*12+8;         // Distance from ground to target (8'8")
    public static final double a1 = 30;             // Limelight mount angle (0 = facing forward, 90 = facing the ceiling)
  }
}
