package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.RobotOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Config;
import org.firstinspires.ftc.teamcode.robot.RobotOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;



public class Drivetrain {
    public final DcMotorEx frontLeft;
    public final DcMotorEx frontRight;
    public final DcMotorEx backLeft;
    public final DcMotorEx backRight;
    private final Telemetry telemetry;
    public Drivetrain(Robot robot) {
        frontLeft = robot.hardwareMap.get(DcMotorEx.class, Config.LEFT_FRONT_MOTOR);
        frontRight = robot.hardwareMap.get(DcMotorEx.class, Config.RIGHT_FRONT_MOTOR);
        backLeft = robot.hardwareMap.get(DcMotorEx.class, Config.LEFT_REAR_MOTOR);
        backRight = robot.hardwareMap.get(DcMotorEx.class, Config.RIGHT_REAR_MOTOR);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry = robot.telemetry;
    }

    public void forzaDrive(Gamepad gamepad) {

        double x = -gamepad.right_trigger+gamepad.left_trigger;
        double y =  -gamepad.left_stick_x;
        double r = -gamepad.right_stick_x;

//        double frontLeftPower  = x + y + r;
//        double frontRightPower = -x + y - r;
//        double backLeftPower   = -x + y + r;
//        double backRightPower  = x + y - r;
        double frontLeftPower  = x + y + r;
        double frontRightPower = -x + y + r;
        double backLeftPower   = x -y + r;
        double backRightPower  = -x - y + r;

        double max = Math.max(1.0, Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backLeftPower), Math.abs(backRightPower)))));

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);
    }
    public void arcadeDrive(Gamepad gamepad) {

        double x = gamepad.left_stick_y;
        double y = -gamepad.left_stick_x;
        double r = -gamepad.right_stick_x;

        double frontLeftPower  = x + y + r;
        double frontRightPower = -x + y + r;
        double backLeftPower   = x -y + r;
        double backRightPower  = -x - y + r;

        double max = Math.max(1.0, Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower), Math.max(Math.abs(backLeftPower), Math.abs(backRightPower)))));

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);
    }
    public void switchDriveMode() {Config.forzaDrive = !Config.forzaDrive;}

    public Command periodic() {
        return infinite(() ->{
//            telemetry.addData("Forza Drive : ", Config.forzaDrive);
        });
    }
}
