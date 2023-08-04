package org.firstinspires.ftc.teamcode.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    private HardwareAndu robot;
    private double y;
    private double x;
    private double rx;
    private double i=1;

    public DriveSubsystem(HardwareAndu robot) {
        this.robot = robot;
    }

    public void loop(){
        this.robot.FataSt.setPower((y + x + rx)*i);
        this.robot.FataDr.setPower((y - x - rx)*i);
        this.robot.SpateSt.setPower((y - x + rx)*i);
        this.robot.SpateDr.setPower((y + x - rx)*i);
    }

    public void write(){
        try {
            y=-gamepad1.left_stick_y;
            x=gamepad1.right_stick_x;
            rx=gamepad1.left_stick_x;

            if(gamepad1.dpad_left) i=0.3;
            if(gamepad1.dpad_up) i=0.5;
            if(gamepad1.dpad_right) i=1;

        } catch (Exception e) {}
    }
}
