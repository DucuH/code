package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

//Clasa pt un modul de swerve
public class ModuleSubsystem {
    private DcMotorEx motor;
    private CRServo servo;
    private AnalogInput encoder;

    private double startPositionServo=0;
    private int startPositionMotor=0;
    public static double MAX_MOTOR=1, MAX_SERVO=1;

    public ModuleSubsystem(DcMotorEx m, CRServo s, AnalogInput e){
        motor=m;
        servo=s;
        encoder=e;

        MotorConfigurationType motorConfigurationType = motor.getMotorType().clone();
        motorConfigurationType.setAchieveableMaxRPMFraction(MAX_MOTOR);
        motor.setMotorType(motorConfigurationType);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ((CRServoImplEx) servo).setPwmRange(new PwmControl.PwmRange(500, 2500, 5000));
    }

}
