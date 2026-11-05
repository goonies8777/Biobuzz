package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Ramp implements ITransfer{
    private final Servo _gate;
    private final Servo _kicker;

    public Ramp(HardwareMap hwMap){
        _gate = hwMap.get(Servo.class, "gate");
        _kicker = hwMap.get(Servo.class, "kicker");
        _gate.setPosition(.7);
        _kicker.setPosition(1);
    }

    @Override
    public void openGate() {
        _gate.setPosition(0);
    }

    @Override
    public void closeGate() {
        _gate.setPosition(.7);
    }

    @Override
    public void kick() {
        _kicker.setPosition(0);

    }

    @Override
    public void unkick(){
        _kicker.setPosition(1);
    }

}
