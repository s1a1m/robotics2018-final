package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    public HardwareMap HwMap;

    // DcMotors
    public DcMotor left_front_motor;
    public DcMotor right_front_motor;
    public DcMotor left_back_motor;
    public DcMotor right_back_motor;
    public DcMotor lifter;
    public DcMotor arm;
    public DcMotor sweeper;
    public DcMotor linearSlide;

    public GoldAlignDetector detector;

    // Constants
    double motorMax = 0.7;
    double joyScale = 1.0;

    public Robot(){
        System.out.println("Creating a Robot!");
    }

    public void init(HardwareMap hwm){
        HwMap = hwm;

        left_front_motor = HwMap.dcMotor.get("left_front_motor"); // Port 1, Hub 3
        right_front_motor = HwMap.dcMotor.get("right_front_motor"); // Port 1, Hub 2
        left_back_motor = HwMap.dcMotor.get("left_back_motor"); // Port 0, Hub 3
        right_back_motor = HwMap.dcMotor.get("right_back_motor"); // Port 0, Hub 2

        lifter = HwMap.dcMotor.get("lifter"); // Port 2, Hub 3
        arm = HwMap.dcMotor.get("arm"); // Port 2, Hub 2
        sweeper = HwMap.dcMotor.get("sweeper"); // Port 3, Hub 3
        linearSlide = HwMap.dcMotor.get("linearSlide"); // Port 3, Hub 2

        right_front_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        right_back_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Sets up detector
        detector = new GoldAlignDetector(); // Create detector
        detector.init(HwMap.appContext, CameraViewDisplay.getInstance());
    }

    // 1 - RUN_TO_POSITION, 2 - RUN_USING_ENCODER, 3 - RUN_WITHOUT_ENCODER, 4 - STOP_AND_RESET_ENCODER
    public void setDriveMotorsMode(int option) {
        switch(option) {
            case 1:
                this.left_front_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.right_front_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.left_back_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.right_back_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                break;
            case 2:
                this.left_front_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                this.right_front_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                this.left_back_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                this.right_back_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                break;
            case 3:
                this.left_front_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                this.right_front_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                this.left_back_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                this.right_back_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                break;
            case 4:
                this.left_front_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                this.right_front_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                this.left_back_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                this.right_back_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                break;
            default:
                System.out.println("Error: Invalid argument!");
        }
    }

    public boolean areDriveMotorsBusy() {
        if (this.left_front_motor.isBusy() || this.right_front_motor.isBusy() || this.left_back_motor.isBusy() || this.right_back_motor.isBusy()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean areAuxMotorsBusy() {
        if (this.sweeper.isBusy() || this.arm.isBusy() || this.linearSlide.isBusy() || this.lifter.isBusy()) {
            return true;
        } else {
            return false;
        }
    }

    public void waitFor() {
        while(this.left_front_motor.isBusy()){
            //Waiting
        }
    }

    // --- Autonomous Functions --- //

    public void driveStraight(int encoderTicks, double power) {
        this.setDriveMotorsMode(4); // STOP_AND_RESET_ENCODER
        this.setDriveMotorsMode(1); // RUN_TO_POSITION
        // Sets target position
        this.left_front_motor.setTargetPosition(encoderTicks);
        this.right_front_motor.setTargetPosition(encoderTicks);
        this.left_back_motor.setTargetPosition(encoderTicks);
        this.right_back_motor.setTargetPosition(encoderTicks);
        // Sets power
        this.left_front_motor.setPower(power);
        this.right_front_motor.setPower(power);
        this.left_back_motor.setPower(power);
        this.right_back_motor.setPower(power);
    }

    public void turn(String direction, int encoderTicks, double power) {
        this.setDriveMotorsMode(4); // STOP_AND_RESET_ENCODER
        this.setDriveMotorsMode(1); // RUN_TO_POSITION
        if(direction == "CW") {
            // Turning  ClockWise
            this.left_front_motor.setTargetPosition(encoderTicks);
            this.left_back_motor.setTargetPosition(encoderTicks);
            this.right_front_motor.setTargetPosition(-encoderTicks);
            this.right_back_motor.setTargetPosition(-encoderTicks);
        }
        else if(direction == "CCW") {
            // Turning Counter ClockWise
            this.left_front_motor.setTargetPosition(-encoderTicks);
            this.left_back_motor.setTargetPosition(-encoderTicks);
            this.right_front_motor.setTargetPosition(encoderTicks);
            this.right_back_motor.setTargetPosition(encoderTicks);
        } else {
            System.out.println("Error: Invalid argument!");
            return;
        }
        // Sets power
        this.left_front_motor.setPower(power);
        this.right_front_motor.setPower(power);
        this.left_back_motor.setPower(power);
        this.right_back_motor.setPower(power);
    }

    public void ejectTotem(){
        this.sweeper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.sweeper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.sweeper.setTargetPosition(1000);
        this.sweeper.setPower(1.0);
    }

    public void strafe(String direction, int distance, double power) {
        setDriveMotorsMode(1);
        if (direction == "Right") {
            this.left_back_motor.setTargetPosition(this.left_back_motor.getCurrentPosition() - distance);
            this.left_front_motor.setTargetPosition(this.left_front_motor.getCurrentPosition() + distance);
            this.right_back_motor.setTargetPosition(this.right_back_motor.getCurrentPosition() + distance);
            this.right_front_motor.setTargetPosition(this.right_front_motor.getCurrentPosition() - distance);

            this.left_back_motor.setPower(power);
            this.left_front_motor.setPower(power);
            this.right_back_motor.setPower(power);
            this.right_front_motor.setPower(power);
        }
        if(direction == "Left"){
            this.left_back_motor.setTargetPosition(this.left_back_motor.getCurrentPosition() + distance);
            this.left_front_motor.setTargetPosition(this.left_front_motor.getCurrentPosition() - distance);
            this.right_back_motor.setTargetPosition(this.right_back_motor.getCurrentPosition() - distance);
            this.right_front_motor.setTargetPosition(this.right_front_motor.getCurrentPosition() + distance);

            this.left_back_motor.setPower(power);
            this.left_front_motor.setPower(power);
            this.right_back_motor.setPower(power);
            this.right_front_motor.setPower(power);
        }
    }

    public void raiseLifter(int distance, double power){
        this.lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.lifter.setTargetPosition(distance);
        this.lifter.setPower(power);
    }

    public void initOpenCV(){

       this.detector.useDefaults(); // Set detector to use default settings
       // Optional tuning
       this.detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
       this.detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
       this.detector.downscale = 0.4; // How much to downscale the input frames
       this.detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
       //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
       this.detector.maxAreaScorer.weight = 0.005; //
       this.detector.ratioScorer.weight = 5; //
       this.detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment
       this.detector.enable(); // Start the detector!
    }

    public int sampleMineral(){

        double xValue = 0.0;
        int loopCount = 0;
        long startTime = System.currentTimeMillis(); //fetch starting time
        while(false||(System.currentTimeMillis()-startTime)<2000) {
            loopCount++;
            xValue += detector.getXPosition();
        }
        double xAvg = xValue / loopCount;
        if(xAvg <= 200){
            return 0;
        }else if(xAvg > 200 && xAvg <= 400){
            return  1;
        }else if(xAvg > 400 ){
            return 2;
        }else{
            return -1;
        }
    }
}



