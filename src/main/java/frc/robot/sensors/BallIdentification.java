package frc.robot.sensors;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DriverStation;

public class BallIdentification implements Sendable {
  private RevColorSensor redBallColorSensor;
  private RevColorSensor blueBallColorSensor;
  
  private Color ballColor = getAllianceColor();

  public static enum Color { RED, BLUE, NEITHER }

  public BallIdentification(RevColorSensor redBallColorSensor, RevColorSensor blueBallColorSensor) {
    this.redBallColorSensor = redBallColorSensor;
    this.blueBallColorSensor = blueBallColorSensor;
  }

  // If the sensor senses a red ball or a blue ball, then a ball is present.
  public boolean isBallPresent() {
    return (redBallColorSensor.isTarget() || blueBallColorSensor.isTarget());
  }

  public Color getBallColor() {
    if (redBallColorSensor.isTarget() && !blueBallColorSensor.isTarget()) {
      ballColor = Color.RED;
    } else if (blueBallColorSensor.isTarget() && !redBallColorSensor.isTarget()) {
      ballColor = Color.BLUE;
    }
    return ballColor;
  }

  private Color getAllianceColor() {
    switch (DriverStation.getAlliance()) {
      case Red:
        return Color.RED;
      case Blue:
        return Color.BLUE;
      case Invalid:
        return Color.NEITHER;
      default:
        return Color.NEITHER;
    }
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addBooleanProperty("Is there a ball?", this :: isBallPresent, null);
  }
}
