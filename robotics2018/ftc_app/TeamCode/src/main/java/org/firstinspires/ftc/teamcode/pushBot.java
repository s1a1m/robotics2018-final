package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Set;

@TeleOp(name = "Push Bot", group = "Teleop")

public class pushBot extends LinearOpMode {
    public Robot R = new Robot();
    public HardwareMap HwMap;

    @Override
    public void runOpMode(){
        R.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()) {

            R.left_front_motor.setPower(gamepad1.right_stick_y);
            R.left_back_motor.setPower(gamepad1.right_stick_y);
            R.right_front_motor.setPower(gamepad1.right_stick_y);
            R.right_back_motor.setPower(gamepad1.right_stick_y);

            R.left_front_motor.setPower(gamepad1.right_stick_x);
            R.left_back_motor.setPower(gamepad1.right_stick_x);
            R.right_front_motor.setPower(-gamepad1.right_stick_x);
            R.right_back_motor.setPower(-gamepad1.right_stick_x);

            if(gamepad1.right_trigger <= 1){
                R.arm.setPower(gamepad1.right_trigger);
            }
            if(gamepad1.left_trigger <= 1){
                R.arm.setPower(-gamepad1.left_trigger);
            }


        }
    }
}