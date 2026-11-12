package org.firstinspires.ftc.teamcode.Goonies.Tuners;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Goonies.Common.Shooter;

@TeleOp(group = "Tuners", name = "Shooter kV Tuner")
public class Shooter_kVTuner extends OpMode {
    Shooter _shooter;
    public static double kV = 0.000171;
    public static double kS = 0.09;
    public static double goalRPM = 3600;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};
    int incrementIndex = 1;
    //Start at 0.00001
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

        if (gamepad1.a){
            goalRPM = 3600;
        }
        else if (gamepad1.b){
            goalRPM = 1500;
        }

        double currentStep = increments[incrementIndex];

        if (gamepad1.dpadUpWasPressed()) {kV += currentStep;}
        if (gamepad1.dpadDownWasPressed()) {kV -= currentStep;}

        double power = (kV * goalRPM) + kS;
        _shooter.setPower(power);

        telemetry.addData("Step", "%.6f", currentStep);
        telemetry.addData("kV", "%.6f", kV);
        telemetry.addData("RPM", _shooter.getRPM());
        telemetry.addData("Ticks Per Sec",_shooter.getTicksPerSec());
    }
}
