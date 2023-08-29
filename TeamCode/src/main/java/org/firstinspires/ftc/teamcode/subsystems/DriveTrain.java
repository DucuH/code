package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.drive.DriveConstants.TRACK_WIDTH;
import static org.firstinspires.ftc.teamcode.subsystems.HardwareAndu.TRACKWIDTH;
import static org.firstinspires.ftc.teamcode.subsystems.HardwareAndu.WHEEL_BASE;
import static org.firstinspires.ftc.teamcode.subsystems.SwerveModule.K_STATIC;

import static java.lang.Math.hypot;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.DRIVETRAIN;
import org.firstinspires.ftc.teamcode.hardware.AbsoluteAnalogEncoder;
@Config
public class DriveTrain implements DRIVETRAIN {
    private HardwareAndu robot;
    double R=Math.sqrt((WHEEL_BASE*WHEEL_BASE) + (TRACKWIDTH*TRACKWIDTH));
    public SwerveModule[] modules;

    public SwerveModule ModulFataDr, ModulFataSt, ModulSpateDr, ModulSpateSt;

    public DriveTrain(HardwareAndu robot) {
        this.robot = robot;
        ModulFataDr = new SwerveModule(robot.FataDr, robot.ServoFataDr, new AbsoluteAnalogEncoder(robot.EncoderFataDr, 3.3));
        ModulFataSt = new SwerveModule(robot.FataSt, robot.ServoFataSt, new AbsoluteAnalogEncoder(robot.EncoderFataSt, 3.3));
        ModulSpateDr = new SwerveModule(robot.SpateDr, robot.ServoSpateDr, new AbsoluteAnalogEncoder(robot.EncoderSpateDr, 3.3));
        ModulSpateSt = new SwerveModule(robot.SpateSt, robot.ServoSpateSt, new AbsoluteAnalogEncoder(robot.EncoderSpateSt, 3.3));

        modules = new SwerveModule[]{ModulFataDr, ModulFataSt, ModulSpateDr, ModulSpateSt};
        for (SwerveModule m : modules) m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    public void calculate(Pose2d Pose) // forward, strafe, rotating clockwise
    {
        double STR=Pose.getX(); //Strafe
        double FWD=Pose.getY(); //Forward
        double RCW=Pose.getHeading(); //Rotate Clockwise
        double a = STR-(RCW*(WHEEL_BASE/R));
        double b = STR+(RCW*(WHEEL_BASE/R));
        double c = FWD-(RCW*(TRACKWIDTH/R));
        double d = FWD+(RCW*(TRACKWIDTH/R));

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

    public void updateAllModules() {
        for (SwerveModule m : modules) m.update();
    }

    public static double max(double... args){
        double max = args[0];
        for(double d : args){
            if(d > max) max = d;
        }
        return max;
    }
}
