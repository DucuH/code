package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.Range;

public class AxonSubsystem extends SubsystemBase {

    private HardwareAndu robot;

    private double AxonPosition;

    private boolean isFastMode=false;

    public AxonSubsystem(HardwareAndu robot){
        this.robot = robot;
        AxonPosition=robot.AXON_DOWN_POZ;
    }
    /*public void write() {
        try {
            if (gamepad1.right_bumper) AxonPosition = robot.AXON_DOWN_POZ;
            if (gamepad1.left_bumper) AxonPosition = robot.AXON_UP_POZ;
            AxonPosition= Range.clip(AxonPosition+(gamepad1.right_trigger/100), 0, 0.245);
            AxonPosition= Range.clip(AxonPosition-(gamepad1.left_trigger/100), 0, 0.245);
            robot.AxonServo.setPosition(AxonPosition);
        } catch (Exception e) {}
    }*/
    @Override
    public void periodic()
    {
        robot.AxonServo.setPosition(AxonPosition);
    }
    public void increaseTilt() {
        AxonPosition = Range.clip(
                AxonPosition + getIncrement(),
                robot.AXON_DOWN_POZ,
                robot.AXON_UP_POZ);
    }

    public void decreaseTilt() {
        AxonPosition = Range.clip(
                AxonPosition - getIncrement(),
                robot.AXON_DOWN_POZ,
                robot.AXON_UP_POZ);
    }

    private double getIncrement() {
        return isFastMode ? 0.025 : 0.005;
    }
}
