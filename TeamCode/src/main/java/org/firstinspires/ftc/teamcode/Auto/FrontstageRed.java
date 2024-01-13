package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Frontstage Red", group="test")
public class FrontstageRed extends LinearOpMode {
	private DcMotor leftDrive;
	private DcMotor rightDrive;
	private Servo fingerServo;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
		leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
		rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");
		fingerServo = hardwareMap.get(Servo.class, "finger_servo");
		fingerServo.setPosition(0.4);

		waitForStart();
		leftDrive.setDirection(DcMotor.Direction.REVERSE);
		rightDrive.setDirection(DcMotor.Direction.FORWARD);

		MoveByEncoder.encoderDrive(.25, 32, 5,
					   leftDrive, rightDrive);
	    MoveByEncoder.encoderDrive(.25, -6, 5,
	    	    leftDrive, rightDrive);
	    MoveByEncoder.encoderTurn(0.25, 90, 5,
			    leftDrive, rightDrive);
	    MoveByEncoder.encoderDrive(.25, 92, 15,
			    leftDrive, rightDrive);
		MoveByEncoder.encoderDrive(.25, -5, 5,
				leftDrive, rightDrive);
		fingerServo.setPosition(0.2);
	}
}
