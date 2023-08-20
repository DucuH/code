package org.firstinspires.ftc.teamcode.subsystem;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class IntakeSubsystem2 extends SubsystemBase {
    private HardwareAndu robot;

    private double IntakePosition;

    public IntakeSubsystem2(HardwareAndu robot){
        this.robot = robot;
        IntakePosition= robot.OPEN_POZ;
    }
    @Override
    public void periodic()
    {
        robot.Intake.setPosition(IntakePosition);
    }

    public void IntakeOpen()
    {
        IntakePosition= robot.OPEN_POZ;
    }
    public void IntakeClose()
    {
        IntakePosition= robot.ClOSED_POZ;
    }

}

