package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.pedropathing.math.Vector2D;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.Robot;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Limelight {
    private final Limelight3A limelight;
    private double tx =0;
    private double ty =0;


    public Limelight(Robot robot) {
        limelight = robot.hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();
    }

    public void pollenDetect() {
        List<LLResultTypes.DetectorResult> detections = limelight.getLatestResult().getDetectorResults();
        for (LLResultTypes.DetectorResult detection : detections) {
            tx = detection.getTargetXDegrees(); // Where it is (left-right)
            ty = detection.getTargetYDegrees(); // Where it is (up-down)})
        }}}

