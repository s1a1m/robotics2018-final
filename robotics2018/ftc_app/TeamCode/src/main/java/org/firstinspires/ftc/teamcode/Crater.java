package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Crater", group = "Autonomous")

public class Crater extends LinearOpMode {
    public Robot R = new Robot();
    public void runOpMode(){

        R.init(hardwareMap);
        R.initOpenCV();

        waitForStart();

        R.raiseLifter(-9350, 1);
        while (R.lifter.isBusy()) {
            //Waiting
        }

        R.arm.setPower(-.4);
        sleep(1000);
        R.arm.setPower(-.2);

        int var = R.sampleMineral();
        telemetry.addData("Wubwub", var);

        telemetry.update();

        R.arm.setPower(0);

        if(var == 0) {
            R.strafe("Right", 500, .3);
            while (R.left_front_motor.isBusy()) {
                //Waiting
            }
            R.driveStraight(1100, .8);
            R.waitFor();

            R.turn("CCW", 1800, .8);
            R.waitFor();

            R.driveStraight(1800, .8);
            R.waitFor();

            R.turn("CW", 1800, .8);
            R.waitFor();

            R.driveStraight(800, .8);
            R.waitFor();

            R.turn("CCW", 1800, .8);
            R.waitFor();

            R.driveStraight(1700, .8);
            R.waitFor();

            R.turn("CCW", 1100, .8);
            R.waitFor();

            R.driveStraight(4500, .8);
            R.waitFor();

            //Drop off totem
            R.sweeper.setPower(1);
            sleep(3000);
            R.sweeper.setPower(0);

            //Back up and park in the crater
            R.driveStraight(-5500, -.8);
            R.waitFor();

            R.driveStraight(-1500, -.4);
            R.waitFor();

        }else if(var == 1){
            R.strafe("Right", 500, .3);
            while (R.left_front_motor.isBusy()) {
                //Waiting
            }
            R.driveStraight(1100, .8);
            R.waitFor();

            R.strafe("Left", 500, .3);
            R.waitFor();

            R.driveStraight(1200, .8);
            R.waitFor();

            R.driveStraight(-1200, .8);
            R.waitFor();

            R.turn("CCW", 1800, .8);
            R.waitFor();

            R.driveStraight(4200, .8);
            R.waitFor();

            R.turn("CCW", 1130, .8);
            R.waitFor();

            R.strafe("Right", 250, .8);
            R.waitFor();

            R.driveStraight(3000, .8);
            R.waitFor();

            R.sweeper.setPower(1);
            sleep(3000);
            R.sweeper.setPower(0);

            R.driveStraight(-6000, .8);
            R.waitFor();


        }else if(var == 2){

            R.strafe("Right", 500, .3);
            while (R.left_front_motor.isBusy()) {
                //Waiting
            }

            R.driveStraight(1100, .8);
            R.waitFor();

            R.turn("CW", 800, .8);
            R.waitFor();

            R.driveStraight(1200, .8);
            R.waitFor();

            R.driveStraight(-1200, .8);
            R.waitFor();

            R.turn("CCW", 2600, .8);
            R.waitFor();

            R.driveStraight(4800, .8);
            R.waitFor();

            R.turn("CCW", 1130, .8);
            R.waitFor();

            R.strafe("Right", 250, .8);
            R.waitFor();

            R.driveStraight(3000, .8);
            R.waitFor();

            R.sweeper.setPower(1);
            sleep(3000);
            R.sweeper.setPower(0);

            R.driveStraight(-5800, .8);
            R.waitFor();




        }
    }
}

