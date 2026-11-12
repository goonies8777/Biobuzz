package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.drivebase.RobotDrive;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.Goonies.Autonomous.IAutonomousDriveTrain;

import java.util.concurrent.TimeUnit;

public class MecanumDriveTrain implements IDriveTrain, IAutonomousDriveTrain {
    DcMotor _leftFront;
    DcMotor _leftBack;
    DcMotor _rightFront;
    DcMotor _rightBack;

    DriveMode _driveMode;

    MecanumDrive _robotDrive;

    public MecanumDriveTrain(HardwareMap hardwareMap){
        _leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        _leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        _rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        _rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        _leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        _leftFront.setPower(0);
        _leftBack.setPower(0);
        _rightFront.setPower(0);
        _rightBack.setPower(0);

        _leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        _leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        _rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        _rightBack.setDirection(DcMotorSimple.Direction.FORWARD);

        _driveMode = DriveMode.ROBOT_CENTRIC;
    }

    @Override
    public void setDriveMode(DriveMode driveMode) {
        _driveMode = driveMode;
    }

    @Override
    public DriveMode getDriveMode() {
        return _driveMode;
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

    @Override
    public RobotDrive getRobotDrive(){
        return _robotDrive;
    }

    public void driveForwardForMilliseconds(int milliseconds){
        Timing.Timer timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        timer.start();

        while(!timer.done()) {
            setPower(RobotConstants.DRIVE_SPEED);
        }

        setPower(0);
    }

    @Override
    public void driveBackwardForMilliseconds(int milliseconds){
        Timing.Timer timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        timer.start();

        while(!timer.done()) {
            setPower(-RobotConstants.DRIVE_SPEED);
        }

        setPower(0);
    }

    @Override
    public void driveLeftForMilliseconds(int milliseconds){
        Timing.Timer timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        timer.start();

        while(!timer.done()) {
            setPower(
                    -RobotConstants.TURN_SPEED,
                    -RobotConstants.TURN_SPEED,
                    RobotConstants.TURN_SPEED,
                    RobotConstants.TURN_SPEED);
        }

        setPower(0);
    }

    @Override
    public void driveRightForMilliseconds(int milliseconds){
        Timing.Timer timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        timer.start();

        while(!timer.done()) {
            setPower(
                    RobotConstants.TURN_SPEED,
                    RobotConstants.TURN_SPEED,
                    -RobotConstants.TURN_SPEED,
                    -RobotConstants.TURN_SPEED);
        }
        setPower(0);
    }

    @Override
    public void strafeRightForMilliseconds(int milliseconds){
        Timing.Timer timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        timer.start();

        while(!timer.done()) {
            setPower(
                    RobotConstants.DRIVE_SPEED,
                    -RobotConstants.DRIVE_SPEED,
                    -RobotConstants.DRIVE_SPEED,
                    RobotConstants.DRIVE_SPEED);
        }
        setPower(0);
    }

    @Override
    public void strafeLeftForMilliseconds(int milliseconds){
        Timing.Timer timer = new Timing.Timer(milliseconds, TimeUnit.MILLISECONDS);

        timer.start();

        while(!timer.done()) {
            setPower(
                    -RobotConstants.DRIVE_SPEED,
                    RobotConstants.DRIVE_SPEED,
                    RobotConstants.DRIVE_SPEED,
                    -RobotConstants.DRIVE_SPEED);
        }
        setPower(0);
    }
}
