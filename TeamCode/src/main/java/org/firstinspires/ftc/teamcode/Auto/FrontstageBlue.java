package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Frontstage Blue", group="test")
public class FrontstageBlue extends LinearOpMode {
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

		MoveByEncoder.encoderDrive(.25, 24, 5.0,
				leftDrive, rightDrive);
		MoveByEncoder.encoderTurn(.25, 45, 5,
				leftDrive, rightDrive);
    }
}
