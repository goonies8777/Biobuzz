package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.math.Pose;

public interface IRobot {
    void Initialize(boolean forAutonomous);
    void Initialize(boolean forAutonomous, Pose startingPose);

    IDriveTrain getDriveTrain();

    void updateIndicators();
    RobotState getState();
    void setState(RobotState state);
    boolean isAutomated();
    void setAutomating(boolean isAutomating);
}
