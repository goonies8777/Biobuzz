package org.firstinspires.ftc.teamcode.Goonies.BioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.teamcode.Goonies.Autonomous.IAutonomousDriveTrain;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Goonies.Common.RobotConstants;
import org.firstinspires.ftc.teamcode.Goonies.Common.ShooterConstants;

import java.util.concurrent.TimeUnit;

@Autonomous(name="Pollenator: Garden Shoot and Park", group="Goonies", preselectTeleOp="Pollenator300")
public class Biobuzz_GardenSide_ShootPark extends LinearOpMode {
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

        _robot.getShooter().setRPM(ShooterConstants.AUTO_RPM/3.5);
        _robot.getShooter().setHood(ShooterConstants.POSITION_4);

        driveTrain.driveForwardForMilliseconds(500);

        _robot.getTransfer().openGate();

        for(int i = 0; i < 4; i++) {
            Timing.Timer timer1 = new Timing.Timer(500, TimeUnit.MILLISECONDS);
            Timing.Timer timer2 = new Timing.Timer(500, TimeUnit.MILLISECONDS);
            _robot.getTransfer().kick();
            timer1.start();

            while (!timer1.done()) {}
            _robot.getTransfer().unkick();

            timer2.start();

            while (!timer2.done()) {}
        }

        driveTrain.strafeLeftForMilliseconds(2000);

        driveTrain.driveForwardForMilliseconds(3000);

        driveTrain.strafeLeftForMilliseconds(500);
    }
}
