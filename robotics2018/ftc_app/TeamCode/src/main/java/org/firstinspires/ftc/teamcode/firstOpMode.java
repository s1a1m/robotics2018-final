package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Set;

@TeleOp(name = "Drift Mode", group = "Teleop")

//@Disabled
public class firstOpMode extends LinearOpMode {
    public Robot R = new Robot();
    public HardwareMap HwMap;

    @Override
    public void runOpMode(){
        R.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){

            // Found this online, tweaked a bit.
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;

            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            if(gamepad1.dpad_left){ // If Left D-Pad Arrow is pressed.
                R.left_front_motor.setPower(-0.7);
                R.right_front_motor.setPower(0.7);
                R.left_back_motor.setPower(0.7);
                R.right_back_motor.setPower(-0.7);
            }else if(gamepad1.dpad_right){ // If Right D-Pad Arrow is pressed.
                R.left_front_motor.setPower(0.7);
                R.right_front_motor.setPower(-0.7);
                R.left_back_motor.setPower(-0.7);
                R.right_back_motor.setPower(0.7);
            }else{ // If Left or Right D-Pad Arrows are NOT pressed.
                R.left_front_motor.setPower(v1);
                R.right_front_motor.setPower(v2);
                R.left_back_motor.setPower(v3);
                R.right_back_motor.setPower(v4);
            }

            // linearSlide
            R.linearSlide.setPower(-gamepad2.left_stick_y);

            // lifter
            if(gamepad1.dpad_up){
                R.lifter.setPower(-.85);
            }else if(gamepad1.dpad_down){
                R.lifter.setPower(.85);
            }else{
                R.lifter.setPower(0);
            }

            // sweeper
            if(gamepad2.a){
                R.sweeper.setPower(1);
            }else if(gamepad2.b){
                R.sweeper.setPower(-1);
            }else if(gamepad2.y){
                R.sweeper.setPower(0);
            }

            // arm
            int verticalPosition = -1600;
            if(gamepad2.left_trigger >= .5){
                if(R.arm.getCurrentPosition() <= verticalPosition){
                    //Slow down the arm to keep from slamming down.
                    R.arm.setPower(-.2);
                }
                else {R.arm.setPower(-.6);}
            }
            else if(gamepad2.right_trigger >= .5){
                if(R.arm.getCurrentPosition() <= verticalPosition){
                    R.arm.setPower(.5);
                }
                else {R.arm.setPower(.2);}
            }
            else {
                if(R.arm.getCurrentPosition() <= verticalPosition){
                    R.arm.setPower(.2);
                }
                else {
                    R.arm.setPower(-.2);
                }
            }

            //Ability to turn on arm controller
            if(R.linearSlide.getPower() == 0) {
                double armTurningPower = .36;
                R.right_front_motor.setPower(-gamepad2.left_stick_x);
                R.right_back_motor.setPower(-gamepad2.left_stick_x);
                R.left_front_motor.setPower(gamepad2.left_stick_x);
                R.left_back_motor.setPower(gamepad2.left_stick_x);
                while (gamepad2.left_stick_x >= armTurningPower) {
                    R.left_back_motor.setPower(armTurningPower);
                    R.left_front_motor.setPower(armTurningPower);
                    R.right_back_motor.setPower(-armTurningPower);
                    R.right_front_motor.setPower(-armTurningPower);
                }
                while (gamepad2.left_stick_x <= -armTurningPower) {
                    R.left_back_motor.setPower(-armTurningPower);
                    R.left_front_motor.setPower(-armTurningPower);
                    R.right_back_motor.setPower(armTurningPower);
                    R.right_front_motor.setPower(armTurningPower);
                }
            }

        }
    }
}