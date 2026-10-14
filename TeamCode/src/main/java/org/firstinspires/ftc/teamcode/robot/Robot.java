package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.*;

public class Robot {
    public final HardwareMap hardwareMap;
    public final Telemetry telemetry;
    public final Intake intake;
    public final Drivetrain drivetrain;


    public Robot(OpMode opMode) {
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        intake = new Intake(this);
        drivetrain = new Drivetrain(this);
    }
}