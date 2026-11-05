package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Shooter implements IShooter {
    private final DcMotorEx flyWheel;
    private final Servo hoodControl;

    private double encoderCPM = 28;
    private double gearRatio = 1;

    public static double kV = 0.000171;
    public static double kS = 0.085;
    public static double kP = 0.00045;

    public Shooter (HardwareMap hwMap){
        flyWheel = hwMap.get(DcMotorEx.class, "shooter");
        flyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flyWheel.setDirection(DcMotorSimple.Direction.FORWARD);

        hoodControl = hwMap.get(Servo.class, "hood");
        hoodControl.setPosition(.5);
        hoodControl.setPosition(.45);
    }

    public void setHood(double position)
    {
        hoodControl.setPosition(position);
    }

    public void setPower(double power){
        flyWheel.setPower(power);
    }

    public void stop(){
        flyWheel.setPower(0);
    }

    public void setRPM(double targetRPM){
        //call this every loop
        double error = targetRPM - getRPM();
        double feedForward = (kV * targetRPM) + kS;
        double feedBack = error * kP;
        double power = feedForward + feedBack;

        setPower(power);
    }

    public double getTicksPerSec(){
        return flyWheel.getVelocity();
    }

    public double getRPM(){
        return ((getTicksPerSec()/encoderCPM)*60)/gearRatio;
    }
}
