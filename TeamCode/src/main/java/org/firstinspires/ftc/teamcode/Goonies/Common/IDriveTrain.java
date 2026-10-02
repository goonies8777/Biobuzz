package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;

public interface IDriveTrain {
    void setDriveMode(DriveMode driveMode);
    DriveMode getDriveMode();
    Follower getFollower();
    Pose getPose();
    void setStartingPose(Pose startingPose);
    void setPose(Pose pose);
    void setPower(double lf, double lb,double rf, double rb);
    void setPower(double power);
}
