package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(-3.75);
        c.yPodOffset.set(-.125);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.21314744873512695);
                Controller secondaryTranslationalForward = Controller.proportional(0.07875228690290544);
                Controller primaryTranslationalLateral = Controller.proportional(0.3552082303065136);
                Controller secondaryTranslationalLateral = Controller.proportional(0.1312399497595385);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.017508549750224475));
                c.brake.set(Controller.proportionalFeedforward(0.014882267287690803));

                c.headingFeedback.set(Controller.proportional(2.6333309396406275));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.037101523039380524, 0.011791351664184303));

                c.linearBrakeCoefficients.set(Matrix.diag(0.10461480944478761, 0.05270502842161555));
                c.quadraticBrakeCoefficients.set(Matrix.diag(7.360945170471249E-4, 0.0029061723113116));

                c.maxAchievableForwardVelocity.set(55.30019139413226);
                c.maxAchievableStrafeVelocity.set(48.08310151044038);
                c.naturalForwardDeceleration.set(33.88158695597618);
                c.naturalStrafeDeceleration.set(53.62474814533156);
            }
    );
}