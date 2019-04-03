package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "pushBot", group = "Autonomous")

public class pushBotAutonomous extends LinearOpMode {
    public Robot R = new Robot();
    public void runOpMode(){

        R.init(hardwareMap);
        R.initOpenCV();

        waitForStart();

    }
}

