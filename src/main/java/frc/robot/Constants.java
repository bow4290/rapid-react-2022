package frc.robot;

public final class Constants {
  public static final class JoystickConstants {
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int XBOX_CONTROLLER = 2;
  }

  public static final class IndexerConstants {
    public static int upperIndexMotorChannel = 0;
    public static int lowerIndexMotorChannel = 0;
    
    public static double bothShootingIndexSpeed = 0.5;
    public static double bothIntakingIndexSpeed = 0.5;
    public static double lowerIntakingIndexSpeed = 0.5;
  }

  public static final class IntakeConstants {
    public static int intakeMotorChannel = 44;
    public static int intakeUpChannel = 6;
    public static int intakeDownChannel = 7;
  }

  public static final class ShooterConstants {
    public static int shooterMotorChannel = 7;
    public static int shooterMotorVoltage = 11;

    // public static double discardSpeedRPM = 2500.0;

    public static double kF = 1023.0/20330.0;
    public static double kP = 0.01;
    public static double kI = 0;
    public static double kD = 0;
  }

  public static final class LimelightConstants {
    public static final double h1 = 0;  // Distance from ground to limelight
    public static final double h2 = 0;  // Distance from ground to target
    public static final double a1 = 0;  // Limelight mount angle
  }

  public static final class Flags {
    // public static final boolean drivetrain = false;
    // public static final boolean intake = false;
    public static final boolean colors = false;
  }
}
