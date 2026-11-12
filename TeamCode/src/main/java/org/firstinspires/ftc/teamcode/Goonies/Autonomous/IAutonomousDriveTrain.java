package org.firstinspires.ftc.teamcode.Goonies.Autonomous;

public interface IAutonomousDriveTrain {
    void driveForwardForMilliseconds(int milliseconds);
    void driveBackwardForMilliseconds(int milliseconds);
    void driveLeftForMilliseconds(int milliseconds);
    void driveRightForMilliseconds(int milliseconds);
    void strafeRightForMilliseconds(int milliseconds);
    void strafeLeftForMilliseconds(int milliseconds);
}
