package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="turn 90° right", group="test")
public class TurnRight extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
	    leftDrive.setDirection(DcMotor.Direction.REVERSE);
	    rightDrive.setDirection(DcMotor.Direction.FORWARD);
		waitForStart();
	leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
	rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");

	MoveByEncoder.encoderTurn(0.25, -90, 5,
			leftDrive, rightDrive);
    }
}
