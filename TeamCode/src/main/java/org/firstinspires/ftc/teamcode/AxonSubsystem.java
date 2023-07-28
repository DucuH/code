package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.Range;

public class AxonSubsystem extends SubsystemBase {

    private HardwareAndu robot;

    private double AxonPosition;

    public AxonSubsystem(HardwareAndu robot){
        this.robot = robot;
        AxonPosition=robot.AXON_DOWN_POZ;
    }
    public void write() {
        try {
            if (gamepad1.right_bumper) AxonPosition = robot.AXON_DOWN_POZ;
            if (gamepad1.left_bumper) AxonPosition = robot.AXON_UP_POZ;
            AxonPosition= Range.clip(AxonPosition+(gamepad1.right_trigger/100), 0, 0.245);
            AxonPosition= Range.clip(AxonPosition-(gamepad1.left_trigger/100), 0, 0.245);
            robot.AxonServo.setPosition(AxonPosition);
        } catch (Exception e) {}
    }
}
