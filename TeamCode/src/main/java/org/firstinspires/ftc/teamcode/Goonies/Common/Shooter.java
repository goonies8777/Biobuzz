package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter implements IShooter {
    private final DcMotorEx flyWheel;
    private final Servo hoodControl;

    public Shooter (HardwareMap hwMap){
        flyWheel = hwMap.get(DcMotorEx.class, "shooter");
        flyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flyWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        hoodControl = hwMap.get(Servo.class, "hood");
        hoodControl.setPosition(ShooterConstants.POSITION_4+0.1);
        hoodControl.setPosition(ShooterConstants.POSITION_3);
    }

    public void setHood(double position)
    {
        hoodControl.setPosition(position);
    }

    public double getHood() { return hoodControl.getPosition(); }

    public void setPower(double power){
        flyWheel.setPower(power);
    }

    public void stop(){
        flyWheel.setPower(0);
    }

    public void setRPM(double targetRPM){
        //call this every loop
        double error = targetRPM - getRPM();
        double feedForward = (ShooterConstants.kV * targetRPM) + ShooterConstants.kS;
        double feedBack = error * ShooterConstants.kP;
        double power = feedForward + feedBack;

        setPower(power);
    }

    public double getTicksPerSec(){
        return flyWheel.getVelocity();
    }

    public double getRPM(){
        return ((getTicksPerSec()/ShooterConstants.ENCODER_CPM)*60)/ShooterConstants.GEAR_RATIO;
    }
}
