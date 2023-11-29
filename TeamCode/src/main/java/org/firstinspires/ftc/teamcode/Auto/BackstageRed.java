package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Backstage Red", group="test")
public class BackstageRed extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
		waitForStart();
		leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
		rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");

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
