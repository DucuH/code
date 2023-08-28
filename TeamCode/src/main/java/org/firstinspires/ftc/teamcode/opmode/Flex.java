package org.firstinspires.ftc.teamcode.opmode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.HardwareAndu;
@TeleOp(name="flex")
public class Flex extends LinearOpMode {
    HardwareAndu robot;

    public void runOpMode(){
        robot.init(hardwareMap, telemetry);
        waitForStart();
        while(opModeIsActive()){
            robot.FataDr.setPower(1);
            robot.FataSt.setPower(1);
            robot.SpateDr.setPower(1);
            robot.SpateSt.setPower(1);
            robot.ServoFataSt.setPower(1);
            robot.ServoSpateSt.setPower(1);
            robot.ServoFataDr.setPower(1);
            robot.ServoSpateDr.setPower(1);
        }
    }
}
