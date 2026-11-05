package org.firstinspires.ftc.teamcode.Goonies.Tuners;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Goonies.Common.Shooter;

@TeleOp(group = "Goonies", name = "Shooter kP Tuner")
public class Shooter_kPTuner extends OpMode {
    Shooter _shooter;
    public static double kV = 0.000171;
    public static double kS = 0.09;
    public static double kP = 0.00045;
    public static double goalRPM = 3600;

    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01};
    int incrementIndex = 2;
    //Start at 0.01
    @Override
    public void init(){
        _shooter = new Shooter(hardwareMap);
    }

    @Override
    public void loop(){
        //changes the value that increments is accessed to
        if (gamepad1.dpadRightWasPressed() && incrementIndex < 4){
            incrementIndex++;
        } else if (gamepad1.dpadLeftWasPressed() && incrementIndex > 0){
            incrementIndex--;
        }

        //sets the robot's goal rpm
        if (gamepad1.a){
            goalRPM = 3600;
        }
        else if (gamepad1.b){
            goalRPM = 1800;
        }

        double currentStep = increments[incrementIndex];

        //increases/decreases the kP based off inputs
        if (gamepad1.dpadUpWasPressed()) {kP += currentStep;}
        if (gamepad1.dpadDownWasPressed()) {kP -= currentStep;}

        //sets the values of feedforward,feedback, and error for motor power
        double feedforward = (kV * goalRPM) + kS;
        double error = goalRPM - _shooter.getRPM();
        double feedback = error * kP;

        _shooter.setPower(feedforward + feedback);

        telemetry.addData("Step", "%.6f", currentStep);
        telemetry.addData("kP", "%.6f", kP);
        telemetry.addData("Error", error);
        telemetry.addData("RPM", _shooter.getRPM());
    }
}
