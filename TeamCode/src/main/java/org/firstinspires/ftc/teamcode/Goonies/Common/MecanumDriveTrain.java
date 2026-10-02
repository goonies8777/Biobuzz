package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveTrain implements IDriveTrain{
    DcMotor _leftFront;
    DcMotor _leftBack;
    DcMotor _rightFront;
    DcMotor _rightBack;

    public MecanumDriveTrain(HardwareMap hardwareMap){
        _leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        _leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        _rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        _rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        _leftFront.setPower(0);
        _leftBack.setPower(0);
        _rightFront.setPower(0);
        _rightBack.setPower(0);

        _leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        _leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        _rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        _rightBack.setDirection(DcMotorSimple.Direction.FORWARD);

        _leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void setDriveMode(DriveMode driveMode) {

    }

    @Override
    public DriveMode getDriveMode() {
        return null;
    }

    @Override
    public Follower getFollower() {
        return null;
    }

    @Override
    public Pose getPose() {
        return null;
    }

    @Override
    public void setStartingPose(Pose startingPose) {

    }

    @Override
    public void setPose(Pose pose) {

    }

    @Override
    public void setPower(double lf, double lb,double rf, double rb){
        _leftFront.setPower(lf);
        _leftBack.setPower(lb);
        _rightFront.setPower(rf);
        _rightBack.setPower(rb);
    }

    @Override
    public void setPower(double power){
        setPower(power, power, power, power);
    }
}
