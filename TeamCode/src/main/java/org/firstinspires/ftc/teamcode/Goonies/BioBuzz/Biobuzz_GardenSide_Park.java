package org.firstinspires.ftc.teamcode.Goonies.BioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Goonies.Autonomous.IAutonomousDriveTrain;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.MecanumDriveTrain;

@Autonomous(name="Pollenator: Garden Park", group="Goonies", preselectTeleOp="Pollenator300")
public class Biobuzz_GardenSide_Park extends LinearOpMode {
    IRobot _robot;

    @Override
    public void runOpMode() throws InterruptedException {
        _robot = new BioBuzzRobot(hardwareMap);

        com.pedropathing.math.Pose startingPose =  new com.pedropathing.math.Pose(0, Math.toRadians(0));

        if (_robot != null) {
            _robot.Initialize(false, startingPose);
            telemetry.addLine("Robot was Instantiated");
        }else {
            telemetry.addLine("Robot was not Instantiated");
        }

        if (_robot != null) {
            _robot.updateIndicators();
        }

        telemetry.update();

        waitForStart();

        IAutonomousDriveTrain driveTrain = (IAutonomousDriveTrain)_robot.getDriveTrain();
        driveTrain.driveForwardForMilliseconds(1000);

        driveTrain.strafeLeftForMilliseconds(2000);

        driveTrain.driveForwardForMilliseconds(2500);

        driveTrain.strafeLeftForMilliseconds(700);
    }
}
