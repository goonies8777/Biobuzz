package org.firstinspires.ftc.teamcode.Goonies.TeleOp;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Goonies.Common.DriveMode;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;

public class DriverController implements IController {

    private final GamepadEx _gamePad;
    private final IRobot _robot;
    private final Follower _follower;

    public DriverController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
        _follower = _robot.getDriveTrain().getFollower();
    }

    @Override
    public void HandleInput() {
        //Stop automated following if the follower is done
        if (_robot.isAutomated())
        {
            HandleAutomated();
        }
        else
        {
            HandleManual();
        }
    }

    private void HandleAutomated()
    {

    }

    private void HandleManual()
    {

    }
}
