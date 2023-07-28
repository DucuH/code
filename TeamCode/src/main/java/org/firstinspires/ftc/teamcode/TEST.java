package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="test")
public class TEST extends LinearOpMode {
    HardwareAndu robot= new HardwareAndu();
    double POZ=0;
    public void runOpMode(){
        robot.init(hardwareMap, telemetry);
        waitForStart();
        while(opModeIsActive()){
            POZ= Range.clip(POZ-(gamepad1.right_stick_y/100), 0, 1);
            robot.Intake.setPosition(POZ);
            telemetry.addData("Pozitie Brat: ", robot.Brat.getCurrentPosition());
            telemetry.addData("Pozitie Intake: ", POZ);
            telemetry.update();
        }
    }
}
