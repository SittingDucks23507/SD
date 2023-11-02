package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import  org.firstinspires.ftc.teamcode.Auto.MoveByEncoder;
import org.firstinspires.ftc.teamcode.Auto.Test;

@Autonomous(name="turn 90° right", group="test")
public class TurnRight extends LinearOpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
	leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
	rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");

	MoveByEncoder.encoderDrive(1, 12, -12, 5.0,
				   leftDrive, rightDrive);
    }
}
