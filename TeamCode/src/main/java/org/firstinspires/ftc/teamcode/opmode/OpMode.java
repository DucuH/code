package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystem.HardwareAndu;
import org.firstinspires.ftc.teamcode.subsystem.AxonSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.BratSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

@TeleOp(name="OpMode")
public class OpMode extends CommandOpMode {
    private HardwareAndu robot = new HardwareAndu();
    private ElapsedTime timer;
    private BratSubsystem brat;
    private DriveSubsystem drive;
    private IntakeSubsystem intake;
    private AxonSubsystem axon;
    private boolean rumble = true;
    //testGit
    @Override
    public void initialize(){

        robot.init(hardwareMap, telemetry);

        brat= new BratSubsystem(robot);

        drive= new DriveSubsystem(robot);

        intake= new IntakeSubsystem(robot);

        axon= new AxonSubsystem(robot);
    }

    @Override
    public void run() {
        if (timer == null) {

            timer = new ElapsedTime();
            try {
                robot.reset();
            } catch (Exception e) {
            }
        }

        robot.read(brat, drive, intake);

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


        robot.loop(brat, drive, intake);

        robot.write(brat, drive, intake, axon);
    }
}