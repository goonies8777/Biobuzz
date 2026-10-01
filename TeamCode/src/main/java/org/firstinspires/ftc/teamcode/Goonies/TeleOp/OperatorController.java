package org.firstinspires.ftc.teamcode.Goonies.TeleOp;

import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.RobotState;

public class OperatorController implements IController{
    private final GamepadEx _gamePad;
    private final IRobot _robot;

    public OperatorController(IRobot robot, GamepadEx gamePad){
        _robot = robot;
        _gamePad = gamePad;
    }
    @Override
    public void HandleInput()
    {

    }
}
