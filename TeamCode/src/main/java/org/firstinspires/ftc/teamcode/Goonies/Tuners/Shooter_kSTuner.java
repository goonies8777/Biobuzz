package org.firstinspires.ftc.teamcode.Goonies.Tuners;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Goonies.Common.Shooter;

@TeleOp(group = "Goonies", name = "Shooter kS Tuner")
public class Shooter_kSTuner extends OpMode {
    Shooter _shooter;
    public static double kS = 0;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};
    int incrementIndex = 4; //Start at 0.01
    @Override
    public void init(){
        _shooter = new Shooter(hardwareMap);
    }

    @Override
    public void loop(){
        if (gamepad1.dpadRightWasPressed() && incrementIndex < 4){
            incrementIndex++;
        } else if (gamepad1.dpadLeftWasPressed() && incrementIndex > 0){
            incrementIndex--;
        }

        double currentStep = increments[incrementIndex];

        if (gamepad1.dpadUpWasPressed()) {kS += currentStep;}
        if (gamepad1.dpadDownWasPressed()) {kS -= currentStep;}

        _shooter.setMotorPower(kS);

        telemetry.addData("kS", "%.6f", kS);
        telemetry.addData("RPM", _shooter.getRPM());
        telemetry.addData("Ticks Per Sec",_shooter.getTicksPerSec());
    }
}
