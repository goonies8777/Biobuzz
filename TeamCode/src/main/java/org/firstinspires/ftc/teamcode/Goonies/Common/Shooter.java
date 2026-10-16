package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotorEx flyWheel;

    private double encoderCPM = 28;
    private double goalRPM = 3000;
    private double gearRatio = 1;
    private double kV, kS, kP;

    public Shooter (HardwareMap hwMap){
        flyWheel = hwMap.get(DcMotorEx.class, "shooter");
        flyWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        flyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setMotorPower(double power){
        flyWheel.setPower(power);
    }

    public double getTicksPerSec(){
        return flyWheel.getVelocity();
    }

    public double getRPM(){
        return ((getTicksPerSec()/encoderCPM)*60)/gearRatio;
    }
}
