package org.firstinspires.ftc.teamcode.Goonies.BioBuzz;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Goonies.Common.IDriveTrain;
import org.firstinspires.ftc.teamcode.Goonies.Common.IRobot;
import org.firstinspires.ftc.teamcode.Goonies.Common.IndicatorManager;
import org.firstinspires.ftc.teamcode.Goonies.Common.RobotState;

public class BioBuzzRobot implements IRobot {
    private final HardwareMap _hardwareMap;
    private IDriveTrain _mecanumDriveTrain;
    private IndicatorManager _indicatorManager;

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

        //Create the LED Indicator Array for displaying the driver state.
        _indicatorManager = new IndicatorManager();

        if (!forAutonomous) {
            _automated = false;
        }

        if (forAutonomous)
        {
            _automated = true;
        }
    }

    @Override
    public IDriveTrain getDriveTrain() {
        return _mecanumDriveTrain;
    }


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
}
