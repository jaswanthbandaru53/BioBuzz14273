package org.firstinspires.ftc.teamcode.opmodes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Config;
import org.firstinspires.ftc.teamcode.robot.RobotOpMode;

@TeleOp(name = "TeleOp", group = "Test")
public class TeleOpIntake extends RobotOpMode {

    @Override
    public void init() {
        telemetry.addData("Forza Drive Active : ", Config.forzaDrive);
        if (gamepad1.dpadUpWasReleased()) {Config.forzaDrive = !Config.forzaDrive;}
        super.init();
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        /* The code below defines whether the drive is Forza or Arcade*/
        if (Config.forzaDrive) {
            robot.drivetrain.forzaDrive(gamepad1);
        } else robot.drivetrain.arcadeDrive(gamepad1);

//        if (gamepad1.rightTriggerWasPressed()) {
//            robot.intake.slowDown();
//        }
//
//        if (gamepad1.rightTriggerWasReleased()) {
//            robot.intake.speedUp();
//        }
//
//        if (gamepad1.rightBumperWasReleased()) robot.intake.off().schedule();
//        if (gamepad1.rightBumperWasPressed()) robot.intake.on().schedule();
//        if (gamepad1.leftBumperWasPressed()) robot.intake.shortReverse().schedule();
        super.loop();
    }
}