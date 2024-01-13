package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Backstage Blue", group="test")
public class BackstageBlue extends LinearOpMode {

	private final ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
		DcMotor leftDrive = hardwareMap.get(DcMotor.class, "leftMotor");
		DcMotor rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");
		Servo fingerServo = hardwareMap.get(Servo.class, "finger_servo");
		Servo wristServo = hardwareMap.get(Servo.class, "wrist_servo");
		wristServo.setDirection(Servo.Direction.FORWARD);

		waitForStart();
		leftDrive.setDirection(DcMotor.Direction.REVERSE);
		rightDrive.setDirection(DcMotor.Direction.FORWARD);
		MoveByEncoder.encoderDrive(.25, 32, 5,
				leftDrive, rightDrive);
	    MoveByEncoder.encoderDrive(.25, -6, 5,
				leftDrive, rightDrive);
	    MoveByEncoder.encoderTurn(0.25, -90, 5,
				leftDrive, rightDrive);
		MoveByEncoder.encoderDrive(.25, 38, 5,
				leftDrive, rightDrive);
		MoveByEncoder.encoderDrive(.25, -5, 5,
				leftDrive, rightDrive);
		fingerServo.setPosition(0.2);
		wristServo.setPosition(.5);
		MoveByEncoder.encoderDrive(.25, -4, 5,
				leftDrive, rightDrive);
	}
}
