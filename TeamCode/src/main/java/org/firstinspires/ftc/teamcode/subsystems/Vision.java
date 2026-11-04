package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;

import com.pedropathing.ivy.Command;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Config;
import org.firstinspires.ftc.teamcode.robot.Robot;




import java.util.List;

public class Vision {
    private Limelight3A lime;
    private final Telemetry telemetry;

    public Vision(Robot robot) {
        telemetry = robot.telemetry;
        lime = robot.hardwareMap.get(Limelight3A.class, "limelight");
    }
    private double tx =0;
    private double ty =0;
    public double distanceX;
    public double distanceY;

    public void start() {
        lime.start();
    }
    public void stop() {
        lime.stop();
    }
    public void pause() {
        lime.pause();
    }





    public void pollenDetect() {
        List<LLResultTypes.DetectorResult> detections = lime.getLatestResult().getDetectorResults();
        for (LLResultTypes.DetectorResult detection : detections) {
            if (detection != null && "pollen".equalsIgnoreCase(detection.getClassName())) {
                double tx = detection.getTargetXDegrees();
                double ty = detection.getTargetYDegrees();
            }

        }
        double h = 2; // height in In(of lime)
        double distanceD = 1/(Math.tan(ty))*h;
        distanceX = Math.sin(tx) * distanceD;
        distanceY = Math.cos(tx) * distanceD;

    }

    public Command periodic() {
        return infinite(() ->{
            telemetry.addData("distance X (in):  ", distanceX);
            telemetry.addData("distance Y (in):  ", distanceY);

        });
    }
}

