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

    public DriverController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
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
        double x = _gamePad.getLeftX();
        double y = _gamePad.getLeftY();
        double turn = _gamePad.getRightX();

        double theta = Math.atan2(y, x);
        double power = Math.hypot(x,y);

        double sin = Math.sin(theta - Math.PI/4);
        double cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));

        double lFront = power * cos/max + turn;
        double lBack = power * sin/max + turn;
        double rFront = power * sin/max - turn;
        double rBack = power * cos/max - turn;

        if (power + Math.abs(turn) > 1){
            lFront  /= power + turn;
            lBack  /= power + turn;
            rFront  /= power + turn;
            rBack  /= power + turn;
        }

        _robot.getDriveTrain().setPower(lFront, lBack, rFront, rBack);
    }
}
