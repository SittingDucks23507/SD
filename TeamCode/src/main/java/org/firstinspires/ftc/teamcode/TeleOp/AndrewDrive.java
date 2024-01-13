/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This particular OpMode executes a POV Game style Teleop for a direct drive robot
 * The code is structured as a LinearOpMode
 * In this mode the left stick moves the robot FWD and back, the Right stick turns left and right.
 * It raises and lowers the arm using the Gamepad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Andrew's Drive", group="Robot")
public class AndrewDrive extends LinearOpMode {

    /* Declare OpMode members. */
    public DcMotor  leftDrive;
    public DcMotor  rightDrive;
    public DcMotor launchMotor;
    public DcMotor armMotor;

    public Servo droneServo;
    public Servo wristServo;
    public Servo fingerServo;

    double clawOffset = 0;

    private int boolToInt(boolean bool) { return bool ? 1 : 0; }

    @Override
    public void runOpMode() {
        double left;
        double right;
        double drive;
        double turn;
        double arm;
        double max;
        double wrist;
	    double finger;
	    double stand;

        boolean launch;

        // Define and Initialize Motors
        leftDrive  = hardwareMap.get(DcMotor.class, "leftMotor");
        rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");
        launchMotor = hardwareMap.get(DcMotor.class, "launch_motor");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        droneServo = hardwareMap.get(Servo.class, "drone_servo");
        wristServo = hardwareMap.get(Servo.class, "wrist_servo");
        fingerServo = hardwareMap.get(Servo.class, "finger_servo");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        launchMotor.setDirection(DcMotor.Direction.FORWARD);
        wrist = .1;
        finger = 0.4;

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            /*
             * Driver 1 Section
             * Movement & Drone
             */
            drive = gamepad1.right_trigger - gamepad1.left_trigger;
            turn  = gamepad1.left_stick_x;

            launch = gamepad1.b;
	        // Move up when y is pressed
            stand = gamepad1.y ? 0 : 0.4;

            /*
             * Driver 2 Section
             * Arm
             */
            arm = boolToInt(gamepad1.dpad_up) - boolToInt(gamepad1.dpad_down);
            if (gamepad2.x)
                armMotor.setTargetPosition(5);
	        // Wrist
            wristServo.setDirection(Servo.Direction.FORWARD);
            if (gamepad1.left_bumper)
                wrist = 0.7;
            if (gamepad1.right_bumper)
                wrist = 0.1;
	        // Finger
    	    if (gamepad2.right_bumper)
	    	    finger = 0.2;
            if (gamepad2.left_trigger > 0)
                finger = 0.6;

            /*
             * Movement & Logic
             */

            // Combine drive and turn for blended motion.
            left  = drive + turn;
            right = drive - turn;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            // Output the safe vales to the motor drives.
            leftDrive.setPower(left * .75);
            rightDrive.setPower(right * .75);
            launchMotor.setPower(launch ? (.75/2) : 0);
            armMotor.setPower(arm * .5);

	        // Move Servos
            droneServo.setPosition(stand);
	        wristServo.setPosition(wrist);
	        fingerServo.setPosition(finger);

            /*
             * Telemetry
             */

            // Send telemetry message to signify robot running;
            telemetry.addData("claw",  "Offset = %.2f", clawOffset);
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.addData("launcher", "%s", launch ? "activated" : "deactivated");
            telemetry.addData("drone_servo", droneServo.getPosition());
            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
