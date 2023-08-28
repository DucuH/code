package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.HardwareAndu.TRACKWIDTH;
import static org.firstinspires.ftc.teamcode.subsystems.HardwareAndu.WHEEL_BASE;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.geometry.Pose2d;

public class DriveTrain extends SubsystemBase {
    private HardwareAndu robot;
    double RADIUS=Math.sqrt((WHEEL_BASE*WHEEL_BASE) + (TRACKWIDTH*TRACKWIDTH));

    public void driveCalculator(Pose2d Pose) // forward, strafe, rotating clockwise
    {
        double STR=Pose.getX(); //Strafe
        double FWD=Pose.getY(); //Forward
        double RCW=Pose.getHeading(); //Rotate Clockwise
        double a = STR-(RCW*(WHEEL_BASE/RADIUS));
        double b = STR+(RCW*(WHEEL_BASE/RADIUS));
        double c = FWD-(RCW*(TRACKWIDTH/RADIUS));
        double d = FWD+(RCW*(TRACKWIDTH/RADIUS));

        double ws[]= {Math.sqrt(b*b+c*c), Math.sqrt(b*b+d*d), Math.sqrt(a*a+d*d), Math.sqrt(a*a+c*c)};
        double wa[]= {Math.atan2(b,c)*180/Math.PI, Math.atan2(b,d)*180/Math.PI, Math.atan2(a,d)*180/Math.PI, Math.atan2(a,c)*180/Math.PI};

        //facem ca puterea maxima sa nu fie mai mare de 1, fara sa stricam proportiile si o trimitem catre module
        double max = max(ws);
        for(int i=0; i<4; i++)
        {
            if(max>1)
                ws[i]=ws[i]/max;

        }

    }

    public static double max(double... args){
        double max = args[0];
        for(double d : args){
            if(d > max) max = d;
        }
        return max;
    }
}
