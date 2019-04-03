package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Depot", group = "Autonomous")
public class Depot extends LinearOpMode{
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
        sleep(1600);
        R.arm.setPower(-.2);

        int var = R.sampleMineral();
        telemetry.addData("Wubwub", var);

        telemetry.update();

        R.arm.setPower(0);

        if(var == 0){

            R.strafe("Right", 500, .3);
            R.waitFor();

            R.driveStraight(500, .8);
            R.waitFor();

            R.turn("CCW", 900, .8);
            R.waitFor();

            R.driveStraight(3600, .8);
            R.waitFor();

            R.turn("CW", 1800, .8);
            R.waitFor();

            R.driveStraight(1500, .8);
            R.waitFor();

            //Drop off totem
            R.sweeper.setPower(1);
            sleep(3000);
            R.sweeper.setPower(0);

            //Back up
            R.driveStraight(-1500, -.8);
            R.waitFor();

        }else if(var == 1){

            R.strafe("Right", 500, .3);
            R.waitFor();

            R.driveStraight(500, .8);
            R.waitFor();

            R.strafe("Left", 500, .3);
            R.waitFor();

            //Drive straight to knock off middle  (maybe in future add sensors)
            R.driveStraight(3500, .8);
            R.waitFor();

            //Drop off totem
            R.sweeper.setPower(1);
            sleep(3000);
            R.sweeper.setPower(0);

            //Back up
            R.driveStraight(-2000, -.8);
            R.waitFor();
        }else if(var == 2){

            R.strafe("Right", 500, .3);
            R.waitFor();

            R.driveStraight(500, .8);
            R.waitFor();

            R.turn("CW", 600, .8);
            R.waitFor();

            R.driveStraight(3500, .8);
            R.waitFor();

            R.turn("CCW", 1000, .8);
            R.waitFor();

            R.driveStraight(1000, .8);
            R.waitFor();

            //Drop off totem
            R.sweeper.setPower(1);
            sleep(1500);
            R.sweeper.setPower(0);

            //Back up
            R.driveStraight(-2000, -.8);
            R.waitFor();

            R.turn("CCW", 2400, .8);
            R.waitFor();

            R.driveStraight(1500, .8);
            R.waitFor();
        }
    }
}
