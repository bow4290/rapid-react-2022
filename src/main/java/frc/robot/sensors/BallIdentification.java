
package frc.robot.sensors;

import edu.wpi.first.wpilibj.DriverStation;

public class BallIdentification {

    private RevColorSensor redBallColorSensor;
    private RevColorSensor blueBallColorSensor;
    private Color ballColor;
    private Color allianceColor;

    public static enum Color { RED, BLUE, NEITHER }

    public BallIdentification(RevColorSensor redBallColorSensor, RevColorSensor blueBallColorSensor) {
        this.redBallColorSensor = redBallColorSensor;
        this.blueBallColorSensor = blueBallColorSensor;
    }

    public boolean isBallTeamColor() {
        allianceColor = getAllianceColor();
        ballColor = getBallColor();
        return (allianceColor == ballColor);
    }

    public Color getBallColor() {
        if(redBallColorSensor.isTarget() && !blueBallColorSensor.isTarget()) {
            return Color.RED;
        } else if (blueBallColorSensor.isTarget() && !redBallColorSensor.isTarget()) {
            return Color.BLUE;
        } else {
            return Color.NEITHER;
        }
    }

    private Color getAllianceColor(){
        switch(DriverStation.getAlliance()) {
            case Red:     return Color.RED;
            case Blue:    return Color.BLUE;
            case Invalid: return Color.NEITHER;
            default:      return Color.NEITHER;
        }
    }
}
