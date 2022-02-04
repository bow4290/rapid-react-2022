package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HoodConstants;

public class HoodSubsystem extends SubsystemBase {
  private final DoubleSolenoid hoodSolenoid;
  private enum HoodStatus { EXTENDED, RETRACTED }
  private static HoodStatus hoodStatus;

  public HoodSubsystem() {
    hoodSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, HoodConstants.hoodSolenoidExtendChannel, HoodConstants.hoodSolenoidRetractChannel);
    hoodStatus = null;
  }

  public void extendHoodSolenoid() {
    hoodSolenoid.set(DoubleSolenoid.Value.kForward);
    hoodStatus = HoodStatus.EXTENDED;
  }

  public void retractHoodSolenoid() {
    hoodSolenoid.set(DoubleSolenoid.Value.kReverse);
    hoodStatus = HoodStatus.RETRACTED;
  }

  public static HoodStatus getHoodSolenoidPosition() { return hoodStatus; }

  @Override
  public void periodic() {
  }
}
