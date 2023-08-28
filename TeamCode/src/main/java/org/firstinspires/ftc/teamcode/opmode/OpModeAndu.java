package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.outoftheboxrobotics.photoncore.PhotonCore;
import com.qualcomm.hardware.lynx.LynxModule;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.HardwareAndu;

public class OpModeAndu extends CommandOpMode {

    private HardwareAndu robot = HardwareAndu.getInstance();
    private DriveTrain drive;
    double loopTime=0;


    @Override
    public void initialize(){
        CommandScheduler.getInstance().reset();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.enabled = true;
        robot.init(hardwareMap, telemetry);

        /*PhotonCore.EXPANSION_HUB.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        PhotonCore.experimental.setMaximumParallelCommands(8);
        PhotonCore.enable();*/
    }


    @Override
    public void run(){
        //am creeat o poitie cu toate cele 3 valori ridicate la a 3-a, ca sa fie mai smooth miscarea)
        //are numele asa lung pt ca am folosit Pose2d de la RR, nu de la FTCLib
        com.acmerobotics.roadrunner.geometry.Pose2d Pose = new com.acmerobotics.roadrunner.geometry.Pose2d(Math.pow(gamepad1.left_stick_x, 3), Math.pow(-gamepad1.left_stick_y, 3), Math.pow(gamepad1.right_stick_x, 3));

        CommandScheduler.getInstance().run();

        robot.loop(Pose, drive);// in loop punem pozitia la care vrem sa mearga robotul
        robot.write(drive);
        double loop = System.nanoTime();
        telemetry.addData("hz ", 1000000000 / (loop - loopTime));
        telemetry.addData("X: ", Pose.getX());
        telemetry.addData("Y: ", Pose.getY());
        telemetry.addData("H: ", Pose.getHeading());
        loopTime = loop;
        telemetry.update();

        //PhotonCore.EXPANSION_HUB.clearBulkCache();
        //PhotonCore.CONTROL_HUB.clearBulkCache();
    }
}
