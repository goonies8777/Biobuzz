package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HuskyLensCamera implements ICamera {
    public static final String HARDWARE_NAME = "huskyLens";
    public static final int SCREEN_WIDTH = 320;
    public static final int SCREEN_HEIGHT = 240;
    public static final double CENTER_X = SCREEN_WIDTH / 2.0;
    public static final double CENTER_Y = SCREEN_HEIGHT / 2.0;

    private final HuskyLens _huskyLens;
    private HuskyLens.Algorithm _algorithm = HuskyLens.Algorithm.NONE;

    public HuskyLensCamera(HardwareMap hwMap) {
        _huskyLens = hwMap.get(HuskyLens.class, HARDWARE_NAME);
    }

    public HuskyLensCamera(HardwareMap hwMap, HuskyLens.Algorithm algorithm) {
        this(hwMap);
        selectAlgorithm(algorithm);
    }

    @Override
    public boolean isConnected() {
        return _huskyLens.knock();
    }

    @Override
    public void selectAlgorithm(HuskyLens.Algorithm algorithm) {
        if (algorithm != null && algorithm != HuskyLens.Algorithm.NONE) {
            _huskyLens.selectAlgorithm(algorithm);
            _algorithm = algorithm;
        }
    }

    @Override
    public HuskyLens.Algorithm getAlgorithm() {
        return _algorithm;
    }

    @Override
    public HuskyLens.Block[] getBlocks() {
        HuskyLens.Block[] blocks = _huskyLens.blocks();
        return blocks != null ? blocks : new HuskyLens.Block[0];
    }

    @Override
    public HuskyLens.Block[] getBlocks(int id) {
        HuskyLens.Block[] blocks = _huskyLens.blocks(id);
        return blocks != null ? blocks : new HuskyLens.Block[0];
    }

    @Override
    public HuskyLens.Block getLargestBlock() {
        return findLargestBlock(getBlocks());
    }

    @Override
    public HuskyLens.Block getLargestBlock(int id) {
        return findLargestBlock(getBlocks(id));
    }

    @Override
    public HuskyLens.Arrow[] getArrows() {
        HuskyLens.Arrow[] arrows = _huskyLens.arrows();
        return arrows != null ? arrows : new HuskyLens.Arrow[0];
    }

    @Override
    public HuskyLens.Arrow[] getArrows(int id) {
        HuskyLens.Arrow[] arrows = _huskyLens.arrows(id);
        return arrows != null ? arrows : new HuskyLens.Arrow[0];
    }

    @Override
    public boolean hasTarget() {
        return getBlocks().length > 0;
    }

    @Override
    public boolean hasTarget(int id) {
        return getBlocks(id).length > 0;
    }

    @Override
    public double getHorizontalOffset(HuskyLens.Block block) {
        if (block == null) {
            return 0.0;
        }
        return block.x - CENTER_X;
    }

    private HuskyLens.Block findLargestBlock(HuskyLens.Block[] blocks) {
        HuskyLens.Block largest = null;
        int maxArea = -1;

        for (HuskyLens.Block block : blocks) {
            int area = block.width * block.height;
            if (area > maxArea) {
                maxArea = area;
                largest = block;
            }
        }

        return largest;
    }
}
