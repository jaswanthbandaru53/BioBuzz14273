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
    //Ignore this shit
    public void init() {
        super.init();
    }

    @Override
    public void init_loop() {
        // this gives a prompt upon init, read doc at marrow lib, screw u Tanish
//        autoPrompter.prompt("drivemode", new BooleanPrompt("Forza Drive", true));
//        autoPrompter.run();
//        robot.limelight.start();TODO:enable this
        super.init_loop();
    }

    @Override
    public void loop() {
        /* The code below defines whether the drive is Forza or Arcade, Tanish, it uses the marrow lib, read the doc if u want to understand, im not explaining it to you*/
//        Config.forzaDrive = autoPrompter.get("drivemode");
        if (Config.forzaDrive) {
            robot.drivetrain.forzaDrive(gamepad1);
        } else {
            robot.drivetrain.arcadeDrive(gamepad1);
        }
        // MAKE SURE THAT BUTTON IS EITHER WAS RELEASED OR WAS PRESSED(cough cough Tanish)

        if (gamepad1.rightBumperWasReleased()) {
            robot.intake.intakeOff().schedule();
            robot.intake.transferOn();
        }
        // Tanish, the following define Intake, it was really obvious but yk had to tell ya

        if (gamepad1.rightBumperWasPressed()) {
            robot.intake.intakeOn().schedule();
            robot.intake.transferOff();
        }
        if (gamepad1.leftBumperWasPressed()) {
            robot.intake.intakeReverse().schedule();
            robot.intake.transferReverse();
        }
        // This switches drive mode
        if (gamepad1.dpadUpWasReleased()) {
            robot.drivetrain.switchDriveMode();
        }
        //DONT TOUCH THE THING BELOW, ITS REALLY IMPORTANT
        super.loop();
    }
}
