
package frc.robot.sensors;

import edu.wpi.first.wpilibj.DriverStation;

public class BallIdentification {

    private RevColorSensor redBallColorSensor;
    private RevColorSensor blueBallColorSensor;
    private Color ballColor = getAllianceColor();

    public static enum Color { RED, BLUE, NEITHER }

    public BallIdentification(RevColorSensor redBallColorSensor, RevColorSensor blueBallColorSensor) {
        this.redBallColorSensor = redBallColorSensor;
        this.blueBallColorSensor = blueBallColorSensor;
    }

    public boolean isBallTeamColor() { return (getAllianceColor() == getBallColor()); }

    public boolean isBallPresent() { return (redBallColorSensor.isTarget() || blueBallColorSensor.isTarget()); }

    public Color getBallColor() {
        if(redBallColorSensor.isTarget() && !blueBallColorSensor.isTarget()) {
            ballColor = Color.RED;
        } else if (blueBallColorSensor.isTarget() && !redBallColorSensor.isTarget()) {
            ballColor = Color.RED;
        }
        return ballColor;
    }

    private Color getAllianceColor() {
        switch(DriverStation.getAlliance()) {
            case Red:     return Color.RED;
            case Blue:    return Color.BLUE;
            case Invalid: return Color.NEITHER;
            default:      return Color.NEITHER;
        }
    }
}
