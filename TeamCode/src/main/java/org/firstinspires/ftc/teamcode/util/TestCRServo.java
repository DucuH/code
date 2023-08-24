package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogInputController;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.C;

@TeleOp(name="Servo")
public class TestCRServo extends LinearOpMode {

    private Encoder enc;
    private CRServo crs;
    private AnalogInput e;

    double power;
    @Override
    public void runOpMode() throws InterruptedException {

        crs = hardwareMap.get(CRServo.class, "csr");
        enc = new Encoder(hardwareMap.get(DcMotorEx.class, "enc"));
        AnalogInput e = hardwareMap.get(AnalogInput.class, "analog input");


        waitForStart();
        while(opModeIsActive())
        {
            power=gamepad1.right_stick_y;

            crs.setPower(power);

            telemetry.addData("Current Angle", e.getVoltage());
            telemetry.addData("Servo Power", crs.getPower());
            telemetry.update();
        }
    }
}
