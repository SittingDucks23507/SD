package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="2x", group="test")
public class IIx extends LinearOpMode {
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

		MoveByEncoder.encoderDrive(.25, 24*2, 5,
					   leftDrive, rightDrive);
	    MoveByEncoder.encoderDrive(.25, -12, 5,
			    leftDrive, rightDrive);
    }
}
