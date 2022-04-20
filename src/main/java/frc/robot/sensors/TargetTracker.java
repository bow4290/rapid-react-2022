package frc.robot.sensors;

public class TargetTracker {
    // layout: x-error, distance, score
    private double[] buffer;
    private int selectedTarget;

    public TargetTracker(int targetCount) {
        buffer = new double[targetCount * 3];
    }

    public void update(boolean hasTarget, double xErr, double dist) {
        int greatest = 0;
        int least = 0;
        boolean foundExistingTarget = false;

        for (int i = 0; i < buffer.length / 3; i++) {
            if (buffer[i + 2] > 0.0) {
                // TODO: Should the xErr window change with distance?
                if (hasTarget && Math.abs(xErr - buffer[i]) < 5.0 && Math.abs(dist - buffer[i + 1]) < 5.0) {
                    foundExistingTarget = true;
                    buffer[i + 2] += 2.0;
                    if (buffer[i + 2] > 30.0) buffer[i + 2] = 30.0;

                    buffer[i] = (0.75 * xErr) + (0.25 * buffer[i]);
                    buffer[i + 1] = (0.75 * dist) + (0.25 * buffer[i + 1]);

                    if (buffer[i + 2] >= buffer[greatest + 2]) greatest = i;
                } else {
                    buffer[i + 2] -= 1.0;

                    if (buffer[i + 2] > buffer[greatest + 2]) greatest = i;
                }
            }

            if (buffer[i + 2] < buffer[least + 2]) least = i;
        }

        if (hasTarget && !foundExistingTarget) {
            buffer[least] = xErr;
            buffer[least + 1] = dist;
            buffer[least + 2] = 10.0;

            if (buffer[least + 2] >= buffer[greatest + 2]) greatest = least;
        }

        selectedTarget = greatest;
    }

    public boolean hasTarget() {
        return buffer[selectedTarget + 2] > 0.0;
    }

    public double getXError() {
        return buffer[selectedTarget];
    }

    public double getXErrorWithOffset(double targetOffsetDistance) {
        return getXError() + Math.toDegrees(Math.atan2(targetOffsetDistance, getDistance()));
    }

    public double getDistance() {
        return buffer[selectedTarget + 1];
    }
}
