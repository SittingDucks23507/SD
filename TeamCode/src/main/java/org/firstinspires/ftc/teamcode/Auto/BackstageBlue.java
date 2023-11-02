package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import  org.firstinspires.ftc.teamcode.Auto.MoveByEncoder;
import org.firstinspires.ftc.teamcode.Auto.Test;

@Autonomous(name="Backstage Blue", group="test")
public class BackstageBlue extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
	leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
	rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");

	leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

	MoveByEncoder.encoderDrive(.25, 24, 24, 5.0,
				   leftDrive, rightDrive);
    }
}
