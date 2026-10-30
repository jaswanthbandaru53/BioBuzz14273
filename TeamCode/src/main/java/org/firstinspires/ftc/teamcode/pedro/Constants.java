package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static Follower create(HardwareMap h) {
        // return new Follower(Drivetrain, Localizer, Foresight);
        return null;
    }
    public static MecanumConfig driveConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("motor0");
                c.frontRightName.set("motor1");
                c.backLeftName.set("motor2");
                c.backRightName.set("motor3");

                c.frontLeftDirection.set(DcMotorEx.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorEx.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorEx.Direction.FORWARD);
                c.backRightDirection.set(DcMotorEx.Direction.FORWARD);
            }
    );
}