package org.firstinspires.ftc.teamcode.Goonies.Common;

public interface IShooter {
    void setHood(double position);
    void setPower(double power);
    void stop();
    void setRPM(double targetRPM);
    double getTicksPerSec();
    double getRPM();
    double getHood();
}