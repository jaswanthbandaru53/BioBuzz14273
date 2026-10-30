package org.firstinspires.ftc.teamcode.opmodes.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.skeletonarmy.marrow.prompts.BooleanPrompt;
import com.skeletonarmy.marrow.prompts.OptionPrompt;
import com.skeletonarmy.marrow.prompts.Prompter;
import com.skeletonarmy.marrow.prompts.ValuePrompt;

import org.firstinspires.ftc.teamcode.Config;
import org.firstinspires.ftc.teamcode.robot.RobotOpMode;

@TeleOp(name = "TeleOpBasic", group = "Main")
public class TeleOpBasic extends RobotOpMode {
    Prompter autoPrompter = new Prompter(this);

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void init_loop() {
        autoPrompter.prompt("drivemode", new BooleanPrompt("Forza Drive", true));
        autoPrompter.run();
        super.init_loop();
    }

    @Override
    public void loop() {
        /* The code below defines whether the drive is Forza or Arcade*/
        Config.forzaDrive = autoPrompter.get("drivemode");
        if (Config.forzaDrive) {
            robot.drivetrain.forzaDrive(gamepad1);
        } else {
            robot.drivetrain.arcadeDrive(gamepad1);
        }

        if (gamepad1.rightBumperWasReleased()) {
            robot.intake.intakeOff().schedule();
            robot.intake.transferOn();
        }
        if (gamepad1.dpadUpWasReleased()) {
            robot.drivetrain.switchDriveMode();
        }

        if (gamepad1.rightBumperWasPressed()) {
            robot.intake.intakeOn().schedule();
            robot.intake.transferOff();
        }
        if (gamepad1.leftBumperWasPressed()) {
            robot.intake.intakeReverse().schedule();
            robot.intake.transferReverse();
        }
        super.loop();
    }
}
