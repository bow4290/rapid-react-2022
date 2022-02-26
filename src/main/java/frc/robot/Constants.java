package frc.robot;

public final class Constants {
  public static final class ElevatorConstants { 
    public static int elevatorClimbMotorChannel = 0;

    public static int elevatorPivotMotorChannel = 1;
    public static float pivotSoftLimit = 1f; //this value is measured in rotations
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
