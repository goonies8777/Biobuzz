package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake implements IIntake{

    private final DcMotor _intakeMotor;

    public Intake(HardwareMap hwMap){
        _intakeMotor = hwMap.get(DcMotor.class, "intake");
        _intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _intakeMotor.setPower(0);
        _intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    @Override
    public void start() {
        _intakeMotor.setPower(-0.9);
    }

    @Override
    public void stop() {
        _intakeMotor.setPower(0);
    }

    @Override
    public void vomit() {
        _intakeMotor.setPower(0.9);
    }
}
