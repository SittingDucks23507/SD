package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Backstage Blue", group="test")
public class BackstageRed extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;
	private Servo wristServo;
	private Servo fingerServo;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
		waitForStart();
		leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
		rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");
		wristServo = hardwareMap.get(Servo.class, "wrist_servo");
		wristServo.setPosition(0);
		fingerServo = hardwareMap.get(Servo.class, "finger_servo");
		fingerServo.setPosition(1);

		leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

		MoveByEncoder.encoderDrive(.25, 32, 5,
					   leftDrive, rightDrive);
	    MoveByEncoder.encoderDrive(.25, -3, 5,
	    	    leftDrive, rightDrive);
	    MoveByEncoder.encoderTurn(0.25, 90, 5,
			    leftDrive, rightDrive);
	    MoveByEncoder.encoderDrive(.25, 34, 5,
			    leftDrive, rightDrive);
		MoveByEncoder.encoderDrive(.25, -5, 5,
				leftDrive, rightDrive);
    }
}
