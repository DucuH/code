package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystem.AxonSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.BratSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.BratSubsystem2;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.HardwareAndu;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem2;

abstract public class OpModeTemplate extends CommandOpMode {

    protected HardwareAndu robot = new HardwareAndu();
    protected SampleMecanumDrive mecanumDrive;
    protected ElapsedTime timer;
    protected BratSubsystem2 brat;
    protected DriveSubsystem drive;
    protected IntakeSubsystem2 intake;
    protected AxonSubsystem axon;
    protected GamepadEx driverGamepad;
    protected GamepadEx secondaryGamepad;
    boolean rumble = false;
    protected double vit;

    protected void initHardware(boolean isAuto)
    {
        //poti sa adaugi isAuto in paranteza pantru a face niste actiuni dora in autonom
        // Example: bart= new BratSubsystem(robot, isAuto);
        robot.init(hardwareMap, telemetry);
        mecanumDrive = new SampleMecanumDrive(hardwareMap);
        brat = new BratSubsystem2(robot);
        intake = new IntakeSubsystem2(robot);
        axon = new AxonSubsystem(robot);

        register(brat, intake, axon);

        driverGamepad = new GamepadEx(gamepad1);
        secondaryGamepad = new GamepadEx(gamepad2);
    }
}
