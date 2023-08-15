package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TeleOp")
public class OpModeSavu extends OpModeTemplate {
    @Override
    public void initialize() {
        initHardware(false);
        new GamepadButton(secondaryGamepad, GamepadKeys.Button.DPAD_UP).whenPressed(axon::increaseTilt);
        new GamepadButton(secondaryGamepad, GamepadKeys.Button.DPAD_DOWN).whenPressed(axon::decreaseTilt);
        new Trigger(() -> gamepad1.dpad_down).whenActive(axon::decreaseTilt);
        new Trigger(() -> gamepad1.dpad_up).whenActive(axon::increaseTilt);

        //new GamepadButton(secondaryGamepad, GamepadKeys.Button.LEFT_BUMPER).whenPressed(() -> );
    }

    @Override
    public void run() {
        super.run();

        if (timer == null) {

            timer = new ElapsedTime();
            try {
                robot.reset();
            } catch (Exception e) {
            }
        }

        //robot.read(brat, drive, intake);

        if (timer.seconds() < 45 && timer.seconds() > 30 && rumble) {
            gamepad1.rumble(500);
            gamepad2.rumble(500);
            rumble = false;
        } else if (timer.seconds() < 30 & !rumble) {
            gamepad1.rumble(2000);
            gamepad1.rumble(2000);
            rumble = true;
        }

        if(gamepad1.a) brat.up();
        if(gamepad1.b) brat.down();


        //robot.loop(brat, drive, intake);

        //robot.write(brat, drive, intake, axon);

        /*mecanumDrive.setDrivePower(
                new Pose2d(-gamepad1.left_stick_y,
                        -gamepad1.left_stick_x,
                        -gamepad1.right_stick_x));

        mecanumDrive.updatePoseEstimate();*/

        mecanumDrive.setWeightedDrivePower(
                new Pose2d(
                        gamepad1.left_stick_y/vit,
                        gamepad1.right_stick_x/vit,
                        gamepad1.left_stick_x/vit
                )
        );
        mecanumDrive.update();
        Pose2d poseEstimate=mecanumDrive.getPoseEstimate();
    }
}
