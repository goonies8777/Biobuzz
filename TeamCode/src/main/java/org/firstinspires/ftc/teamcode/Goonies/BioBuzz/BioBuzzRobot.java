package org.firstinspires.ftc.teamcode.Goonies.BioBuzz;

import com.pedropathing.math.Pose;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.Goonies.Common.IDriveTrain;
import org.firstinspires.ftc.teamcode.Goonies.Common.IIntake;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.IShooter;
import org.firstinspires.ftc.teamcode.Goonies.Common.ITransfer;
import org.firstinspires.ftc.teamcode.Goonies.Common.IndicatorManager;
import org.firstinspires.ftc.teamcode.Goonies.Common.Intake;
import org.firstinspires.ftc.teamcode.Goonies.Common.LEDIndicator;
import org.firstinspires.ftc.teamcode.Goonies.Common.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.Goonies.Common.Ramp;
import org.firstinspires.ftc.teamcode.Goonies.Common.RobotState;
import org.firstinspires.ftc.teamcode.Goonies.Common.Shooter;
import org.firstinspires.ftc.teamcode.Goonies.Common.ShooterConstants;

public class BioBuzzRobot implements IRobot {
    private final HardwareMap _hardwareMap;
    private IDriveTrain _mecanumDriveTrain;
    private IShooter _shooter;
    private IndicatorManager _indicatorManager;
    private IMU _imu;
    private IIntake _intake;
    private ITransfer _ramp;

    private RobotState _state;
    private boolean _automated = false;

    public BioBuzzRobot(HardwareMap hardwareMap){
        _hardwareMap = hardwareMap;
    }

    @Override
    public void Initialize(boolean forAutonomous){
        Pose startingPose = new Pose(0,0,Math.toRadians(0));
        Initialize(forAutonomous, startingPose);
    }

    @Override
    public void Initialize(boolean forAutonomous, Pose startingPose) {
        _state = RobotState.Driving;

        _mecanumDriveTrain = new MecanumDriveTrain(_hardwareMap);
        _shooter = new Shooter(_hardwareMap);

        _imu = _hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        _imu.initialize(new IMU.Parameters(orientationOnRobot));

        //Create the LED Indicator Array for displaying the driver state.
        _indicatorManager = new IndicatorManager();//Create the LED Indicator Array for displaying the driver state.
        _indicatorManager = new IndicatorManager();
        _indicatorManager.addIndicator(
                new LEDIndicator(
                        _hardwareMap.get(DigitalChannel.class, "red1"),
                        _hardwareMap.get(DigitalChannel.class, "green1")
                )
        );

        _indicatorManager.addIndicator(
                new LEDIndicator(
                        _hardwareMap.get(DigitalChannel.class, "red2"),
                        _hardwareMap.get(DigitalChannel.class, "green2")
                )
        );

        _intake = new Intake(_hardwareMap);
        _ramp = new Ramp(_hardwareMap);

        if (!forAutonomous) {
            _automated = false;
        }

        if (forAutonomous)
        {
            _automated = true;
        }
    }

    @Override
    public void handleState(){
        switch(_state){
            case  Driving:
                _intake.stop();
                _shooter.setRPM(0);
                _shooter.setPower(0);
                _shooter.setHood(ShooterConstants.POSITION_1);
                _ramp.closeGate();
                _ramp.unkick();
                break;
            case Shooting:
                _intake.stop();
                break;

            case Intake:
                _shooter.setRPM(0);
                _shooter.setPower(0);
                _ramp.closeGate();
                _intake.start();
                _ramp.unkick();
                _shooter.setHood(ShooterConstants.POSITION_1);
                break;
        }
    }

    @Override
    public IDriveTrain getDriveTrain() {
        return _mecanumDriveTrain;
    }

    @Override
    public IShooter getShooter() { return _shooter; }

    @Override
    public ITransfer getTransfer() { return _ramp; }

    @Override
    public IIntake getIntake() { return _intake; }

    @Override
    public void updateIndicators()
    {
        _indicatorManager.update(_state);
    }

    @Override
    public RobotState getState(){ return _state; }

    @Override
    public void setState(RobotState state)
    {
        _state = state;
    }

    @Override
    public boolean isAutomated(){
        return _automated;
    }

    @Override
    public void setAutomating(boolean isAutomating) {
        _automated = isAutomating;
    }

    @Override
    public IMU getIMU(){
        return _imu;
    }
}
