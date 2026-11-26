package org.firstinspires.ftc.teamcode.Goonies.Common;

import com.qualcomm.hardware.dfrobot.HuskyLens;

public interface ICamera {
    boolean isConnected();

    void selectAlgorithm(HuskyLens.Algorithm algorithm);
    HuskyLens.Algorithm getAlgorithm();

    HuskyLens.Block[] getBlocks();
    HuskyLens.Block[] getBlocks(int id);
    HuskyLens.Block getLargestBlock();
    HuskyLens.Block getLargestBlock(int id);

    HuskyLens.Arrow[] getArrows();
    HuskyLens.Arrow[] getArrows(int id);

    boolean hasTarget();
    boolean hasTarget(int id);

    double getHorizontalOffset(HuskyLens.Block block);
}
