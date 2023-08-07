package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class HardwareAndu {
    public DcMotorEx FataSt;
    public DcMotorEx FataDr;
    public DcMotorEx SpateDr;
    public DcMotorEx SpateSt;
    public CRServo ServoFataSt;
    public CRServo ServoFataDr;
    public CRServo ServoSpateSt;
    public CRServo ServoSpateDr;
    public DcMotorEx Brat;
    public Servo Intake;
    public Servo AxonServo;

    private static HardwareAndu instance = null;
    public boolean enabled;
    public int UP_POZ=400;//////////////
    public int DOWN_POZ=0;///////////////

    public double OPEN_POZ=0.235;//////////////

    public double ClOSED_POZ=0.5;//////////////

    public double AXON_DOWN_POZ=0.245;

    public double AXON_UP_POZ=0;

    public static HardwareAndu getInstance() {
        if (instance == null) {
            instance = new HardwareAndu();
        }
        instance.enabled = true;
        return instance;
    }

    public void init(HardwareMap hardwareMap, Telemetry telemetry)
    {
        FataSt= hardwareMap.get(DcMotorEx.class, "FataSt");
        FataSt.setDirection(DcMotorSimple.Direction.REVERSE);

        FataDr= hardwareMap.get(DcMotorEx.class, "FataDr");

        SpateSt= hardwareMap.get(DcMotorEx.class, "SpateSt");
        SpateSt.setDirection(DcMotorSimple.Direction.REVERSE);

        SpateDr= hardwareMap.get(DcMotorEx.class, "SpateDr");

        ServoFataSt= hardwareMap.get(CRServo.class, "ServoFataSt");
        ServoFataDr= hardwareMap.get(CRServo.class, "ServoFataDr");
        ServoSpateSt= hardwareMap.get(CRServo.class, "ServoSpateSt");
        ServoSpateDr= hardwareMap.get(CRServo.class, "ServoSpateDr");

        Brat = hardwareMap.get(DcMotorEx.class, "Brat");
        Brat.setDirection(DcMotorSimple.Direction.REVERSE);

        Intake = hardwareMap.get(Servo.class, "Intake");
        AxonServo = hardwareMap.get(Servo.class, "AxonServo");
    }

    public void loop(BratSubsystem brat, DriveSubsystem drive, IntakeSubsystem intake){
        try {
            brat.loop();
        } catch (Exception ignored) {}
    }

    public void read(BratSubsystem brat, DriveSubsystem drive, IntakeSubsystem intake){
        try {
            brat.read();
        } catch (Exception ignored) {}
    }

    public void write(BratSubsystem brat, DriveSubsystem drive, IntakeSubsystem intake, AxonSubsystem axon){
        try {
            brat.write();
        } catch (Exception ignored) {}
        try {
            intake.write();
        } catch (Exception e){}
        //try{
            //axon.write();
        //}catch (Exception ignored){}

    }

    public void reset() {
        try {
            Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } catch (Exception e) {}
    }
}
