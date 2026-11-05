package org.firstinspires.ftc.teamcode.Goonies.TeleOp;

import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.RobotConstants;
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
        if (_gamePad.getButton(GamepadKeys.Button.LEFT_BUMPER) && _gamePad.getButton(GamepadKeys.Button.A)){
            _robot.setState(RobotState.Shooting);
        }

        if (_gamePad.getButton(GamepadKeys.Button.LEFT_BUMPER) && _gamePad.getButton(GamepadKeys.Button.B)){
            _robot.setState(RobotState.Driving);
        }

        if (_gamePad.getButton(GamepadKeys.Button.LEFT_BUMPER) && _gamePad.getButton(GamepadKeys.Button.X)){
            _robot.setState(RobotState.Intake);
            _robot.getIntake().start();
        }

        switch(_robot.getState())
        {
            case Driving:
                _robot.getShooter().setRPM(0);
                _robot.getIntake().stop();
                _robot.getTransfer().closeGate();
                HandleDrivingState();
                break;

            case Shooting:
                _robot.getIntake().stop();
                HandleShootingState();
                break;

            case Intake:
                _robot.getShooter().setRPM(0);
                _robot.getTransfer().closeGate();
                HandleIntakeState();
                break;
        }
    }

    private void HandleShootingState(){

        if (_gamePad.getButton(GamepadKeys.Button.A)){
            _robot.getShooter().setHood(.5);
        }

        if (_gamePad.getButton(GamepadKeys.Button.B)){
            _robot.getShooter().setHood(.35);
        }

        if (_gamePad.getButton(GamepadKeys.Button.X)){
            _robot.getShooter().setHood(.4);
        }

        if (_gamePad.getButton(GamepadKeys.Button.Y)){
            _robot.getShooter().setHood(.45);
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_DOWN)){
            _robot.getTransfer().openGate();
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_UP)){
            _robot.getTransfer().closeGate();
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_LEFT)){
            _robot.getTransfer().kick();
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_RIGHT)){
            _robot.getTransfer().unkick();
        }

        _robot.getShooter().setRPM(RobotConstants.ShooterRPM);
    }

    private void HandleDrivingState(){

    }

    private void HandleIntakeState(){
        _robot.getShooter().setRPM(0);

        if (_gamePad.getButton(GamepadKeys.Button.A)){
            _robot.getIntake().start();
        }

        if (_gamePad.getButton(GamepadKeys.Button.B)){
            _robot.getIntake().stop();
        }

        if (_gamePad.getButton(GamepadKeys.Button.DPAD_DOWN)){
            _robot.getIntake().vomit();
        }
    }
}