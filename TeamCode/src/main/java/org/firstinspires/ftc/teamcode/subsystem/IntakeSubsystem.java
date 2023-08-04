package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private HardwareAndu robot;

    private double IntakePosition;

    public IntakeSubsystem(HardwareAndu robot){
        this.robot = robot;
        IntakePosition= robot.OPEN_POZ;
    }

    public void write(){
        try {
            if(gamepad1.y) IntakePosition= robot.OPEN_POZ;
            if(gamepad1.x) IntakePosition= robot.ClOSED_POZ;
            robot.Intake.setPosition(IntakePosition);
        } catch (Exception e) {}
    }
}
