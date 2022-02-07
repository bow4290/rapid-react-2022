package frc.robot;

public final class Constants {
    public static final class DriveConstants {
        public static int leftMotor1Channel = 7;
        public static int leftMotor2Channel = 8;
        public static int rightMotor1Channel = 5;
        public static int rightMotor2Channel = 6;
    
        public static int gearShiftUpChannel = 6;
        public static int gearShiftDownChannel = 9;
    
        public static double targetLinearDistance = 60;
        private static double stage1Ratio = 60/12;
        private static double stage2Ratio = 28/28;
        private static double lowSpreadRatio = 44/22;
        private static double highSpreadRatio = 34/32;
        private static double wheelDiameter = 3.75;
        private static double wheelCircum = wheelDiameter*Math.PI;
        private static int encoderCPR = 2048;
        private static double lowCountsPerWheelRev = stage1Ratio * stage2Ratio * lowSpreadRatio * encoderCPR;
        private static double highCountsPerWheelRev = stage1Ratio * stage2Ratio * highSpreadRatio * encoderCPR;
        private static double lowGearTargetCounts = lowCountsPerWheelRev * targetLinearDistance * wheelCircum;
        private static double highGearTargetCounts = highCountsPerWheelRev * targetLinearDistance * wheelCircum;
        public static double encoderLowDistanceConversion = wheelCircum/(stage1Ratio * stage2Ratio * lowSpreadRatio * encoderCPR);
        public static double encoderHighDistanceConversion = wheelCircum/(stage1Ratio * stage2Ratio * highSpreadRatio * encoderCPR);
      }
    
    public static final class JoystickConstants {
        public static int LEFT_JOYSTICK = 0;
        public static int RIGHT_JOYSTICK = 1;
    }
}
