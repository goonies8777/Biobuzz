package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.IMU;

public interface IRobot {
    void Initialize(boolean forAutonomous);
    void Initialize(boolean forAutonomous, Pose startingPose);

    IDriveTrain getDriveTrain();
    IShooter getShooter();
    IMU getIMU();
    ITransfer getTransfer();
    IIntake getIntake();

    void updateIndicators();
    RobotState getState();
    void setState(RobotState state);
    void handleState();
    boolean isAutomated();
    void setAutomating(boolean isAutomating);
}
