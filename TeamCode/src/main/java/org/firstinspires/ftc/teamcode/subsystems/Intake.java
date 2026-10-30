package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.conditional;
import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.Config;

public class Intake {
    private boolean slowMode = false;
    private Mode intakeMode = Mode.OFF;
    private Mode flowerMode = Mode.OFF;
    private Mode transferMode = Mode.OFF;

    private final DcMotorEx intakeMotor;
    private final Servo flowerServo;

    private final Telemetry telemetry;

    public Intake(Robot robot) {
        intakeMotor = robot.hardwareMap.get(DcMotorEx.class, Config.INTAKE_MOTOR);
        flowerServo = robot.hardwareMap.get(Servo.class, Config.FLOWER_SERVO);
        telemetry = robot.telemetry;
    }

    public Command intakeOn() {
        return instant(() -> intakeMode = Mode.ON).requiring(intakeMotor);
    }

    public Command intakeOff() {
        return instant(() -> intakeMode = Mode.OFF).requiring(intakeMotor);
    }

    public Command intakeReverse() {
        return instant(() -> intakeMode = Mode.REVERSE).requiring(intakeMotor);
    }

    public Command IntakeShortReverse() {
        return intakeReverse().then(waitMs(Config.intakeShortReverseTimeMs)).then(intakeOn());
    }

    public Command toggle() {
        return conditional(() -> intakeMode == Mode.OFF, intakeOn(), intakeOff());
    }

    public void intakeSlowDown() {
        slowMode = true;
    }

    public void intakeSpeedUp() {
        slowMode = false;
    }
    public Command flowerMechDown() {
        return instant(() -> flowerMode = Mode.ON).requiring(flowerServo);
    }

    public Command flowerMechUp() {
        return instant(() -> flowerMode = Mode.OFF).requiring(flowerServo);
    }
    public Command transferOn() {
        return instant(() -> intakeMode = Mode.ON).requiring(intakeMotor);
    }

    public Command transferOff() {
        return instant(() -> intakeMode = Mode.OFF).requiring(intakeMotor);
    }

    public Command transferReverse() {
        return instant(() -> intakeMode = Mode.REVERSE).requiring(intakeMotor);
    }
    public Command initIntake() {
        return instant(() -> flowerMechUp()).requiring(flowerServo);
    }

    public Command periodic() {
        return infinite(() -> {
            switch (intakeMode) {
                case ON:
                    intakeMotor.setPower(slowMode ? Config.intakeSlowPower : Config.intakeFastPower);
                    break;
                case OFF:
                    intakeMotor.setPower(Config.intakeOffPower);
                    break;
                case REVERSE:
                    intakeMotor.setPower(Config.intakeReversePower);
                    break;
            }
            switch (flowerMode) {
                case ON:
                    flowerServo.setPosition(Config.flowerMechOn);
                    break;
                case OFF:
                    intakeMotor.setPower(Config.intakeOffPower);
                    break;
            }


//            telemetry.addData("Intake Current", intakeMotor.getCurrent(CurrentUnit.MILLIAMPS));
//            telemetry.addData("Intake Velocity", intakeMotor.getVelocity());
//            telemetry.addData("Passive Flower Active ", flowerMode);
        });
    }

    enum Mode {
        ON,
        OFF,
        REVERSE
    }
}