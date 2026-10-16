package org.firstinspires.ftc.teamcode.Goonies.Tuners;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Goonies.Common.Shooter;

@TeleOp(group = "Goonies", name = "Shooter kP Tuner")
public class Shooter_kPTuner extends OpMode {
    Shooter _shooter;
    public static double kV = 0.01;
    public static double kS = 0.01;
    public static double kP = 0.01;
    public static double goalRPM = 3000;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};
    int incrementIndex = 2;
    //Start at 0.01
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
            goalRPM = 3000;
        }
        else if (gamepad1.b){
            goalRPM = 1500;
        }

        double currentStep = increments[incrementIndex];

        if (gamepad1.dpadUpWasPressed()) {kP += currentStep;}
        if (gamepad1.dpadDownWasPressed()) {kP -= currentStep;}

        double feedforward = (kV * goalRPM) + kS;
        double error = goalRPM - _shooter.getRPM();
        double feedback = error * kP;

        _shooter.setMotorPower(feedforward + feedback);

        telemetry.addData("Step", "%.6f", currentStep);
        telemetry.addData("kP", "%.6f", kP);
        telemetry.addData("Error", error);
        telemetry.addData("RPM", _shooter.getRPM());
    }
}
