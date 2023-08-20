package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class BratSubsystem2 extends SubsystemBase {
    private HardwareAndu robot;

    private PIDController controller;

    private int liftPosition;

    private double BratPower = 0.0;

    private int targetPosition = 0;

    public enum BratState {
        UP,
        DOWN
    }
    private final ElapsedTime timer;

    public static double P=0;
    public static double I=0;
    public static double D=0;

    private double ImputController;

    private boolean withinTolerance = false;

    private int LIFT_ERROR_TOLERANCE=20;

    public BratSubsystem2(HardwareAndu robot) {
        this.robot = robot;
        this.controller = new PIDController(P,I,D);
        this.timer = new ElapsedTime();
    }
    @Override
    public void periodic()
    {
        this.controller.setPID(P, I, D);

        //withinTolerance = Math.abs(getPos() - getTargetPos()) < LIFT_ERROR_TOLERANCE;

        setTargetPos(targetPosition);

        BratPower = Range.clip(-controller.calculate(liftPosition, targetPosition), -1, 1);

        liftPosition = robot.Brat.getCurrentPosition();

        robot.Brat.setPower(BratPower);
        //ImputController=gamepad2.right_stick_y;
        targetPosition= Range.clip(targetPosition+(int)ImputController*10 , robot.DOWN_POZ , robot.UP_POZ);
    }

    public double getPos() {
        return liftPosition;
    }

    public void setTargetPos(int targetPosition) {
        this.targetPosition = targetPosition;
    }

    public int getTargetPos() {
        return targetPosition;
    }
    public void BratUp() {
        targetPosition=robot.UP_POZ;
    }

    public void BratDown() {
        targetPosition=robot.DOWN_POZ;
    }
    public void BratController(double ImputController)
    {
        this.ImputController=ImputController;
    }

}

