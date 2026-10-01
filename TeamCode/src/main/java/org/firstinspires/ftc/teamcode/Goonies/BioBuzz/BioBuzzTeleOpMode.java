package org.firstinspires.ftc.teamcode.Goonies.BioBuzz;


import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.TeleOp.DriverController;
import org.firstinspires.ftc.teamcode.Goonies.TeleOp.IController;
import org.firstinspires.ftc.teamcode.Goonies.TeleOp.OperatorController;

@TeleOp(group = "Goonies", name = "BioBuzz")
public class BioBuzzTeleOpMode extends LinearOpMode {
    IRobot _robot;
    IController _driverController;
    IController _operatorController;

    @Override
    public void runOpMode() {
        _robot = new BioBuzzRobot(hardwareMap);

        Pose startingPose =  new Pose(0, Math.toRadians(0));

        if (_robot != null) {
            _robot.Initialize(false, startingPose);
            _driverController = new DriverController(_robot, new GamepadEx(this.gamepad1));
            _operatorController = new OperatorController(_robot, new GamepadEx(this.gamepad2));
        }else {
            telemetry.addLine("Robot was not Instantiated");
        }

        if (_operatorController != null) {
            telemetry.addLine("Operator Controlled Initialized");
        }

        if (_driverController != null) {
            telemetry.addLine("Driver Controlled Initialized");
        }

        if (_robot != null) {
            _robot.updateIndicators();
        }

        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (_robot != null && (_driverController != null || _operatorController != null)) {
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                if (_driverController != null) {
                    _driverController.HandleInput();
                }

                if (_operatorController != null) {
                    _operatorController.HandleInput();
                }

                _robot.updateIndicators();
            }
        } else {
            telemetry.addLine("No GamePads Instantiated");
            telemetry.update();
        }
    }

}
