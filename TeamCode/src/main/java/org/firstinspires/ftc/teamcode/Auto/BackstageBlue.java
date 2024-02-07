/* Copyright (c) 2017-2020 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.teamcode.Auto.MoveByEncoder.encoderDrive;
import static org.firstinspires.ftc.teamcode.Auto.MoveByEncoder.encoderTurn;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

/*
 * This OpMode shows how to use a color sensor in a generic
 * way, regardless of which particular make or model of color sensor is used. The OpMode
 * assumes that the color sensor is configured with a name of "sensor_color".
 *
 * There will be some variation in the values measured depending on the specific sensor you are using.
 *
 * You can increase the gain (a multiplier to make the sensor report higher values) by holding down
 * the A button on the gamepad, and decrease the gain by holding down the B button on the gamepad.
 *
 * If the color sensor has a light which is controllable from software, you can use the X button on
 * the gamepad to toggle the light on and off. The REV sensors don't support this, but instead have
 * a physical switch on them to turn the light on and off, beginning with REV Color Sensor V2.
 *
 * If the color sensor also supports short-range distance measurements (usually via an infrared
 * proximity sensor), the reported distance will be written to telemetry. As of September 2020,
 * the only color sensors that support this are the ones from REV Robotics. These infrared proximity
 * sensor measurements are only useful at very small distances, and are sensitive to ambient light
 * and surface reflectivity. You should use a different sensor if you need precise distance measurements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */
@Autonomous(name = "Backstage Blue", group = "Sensor")
public class BackstageBlue extends LinearOpMode {
    NormalizedColorSensor leftSensor;
    NormalizedColorSensor rightSensor;
    static final float SPEED = .25f;
    @Override public void runOpMode() {
        DcMotor leftDrive = hardwareMap.get(DcMotor.class, "leftMotor");
        DcMotor rightDrive = hardwareMap.get(DcMotor.class, "rightMotor");
        Servo fingerServo = hardwareMap.get(Servo.class, "finger_servo");
        Servo wristServo = hardwareMap.get(Servo.class, "wrist_servo");
        wristServo.setDirection(Servo.Direction.FORWARD);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);


        // Once per loop, we will update this hsvValues array. The first element (0) will contain the
        // hue, the second element (1) will contain the saturation, and the third element (2) will
        // contain the value. See http://web.archive.org/web/20190311170843/https://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html
        // for an explanation of HSV color.
        final float[] hsv_left = new float[3];
        final float[] hsv_right = new float[3];

        // Get a reference to our sensor object. It's recommended to use NormalizedColorSensor over
        // ColorSensor, because NormalizedColorSensor consistently gives values between 0 and 1, while
        // the values you get from ColorSensor are dependent on the specific sensor you're using.
        leftSensor = hardwareMap.get(NormalizedColorSensor.class, "left_sensor");
        rightSensor = hardwareMap.get(NormalizedColorSensor.class, "right_sensor");

        leftSensor.setGain(2);
        rightSensor.setGain(2);

        // lights
        if (leftSensor instanceof SwitchableLight &&
            rightSensor instanceof SwitchableLight) {
            ((SwitchableLight) leftSensor).enableLight(true);
            ((SwitchableLight) rightSensor).enableLight(true);
        }

        // Wait for the start button to be pressed.
        waitForStart();
        encoderDrive(SPEED, 26, 5,
                leftDrive, rightDrive);
        sleep(2500);

        // Get the normalized colors from the sensor
        NormalizedRGBA colors_left = leftSensor.getNormalizedColors();
        NormalizedRGBA colors_right = rightSensor.getNormalizedColors();

        /* Use telemetry to display feedback on the driver station. We show the red, green, and blue
         * normalized values from the sensor (in the range of 0 to 1), as well as the equivalent
         * HSV (hue, saturation and value) values. See http://web.archive.org/web/20190311170843/https://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html
         * for an explanation of HSV color. */

        // Update the hsvValues array by passing it[0] to Color.colorToHSV()
        Color.colorToHSV(colors_left.toColor(), hsv_left);
        Color.colorToHSV(colors_right.toColor(), hsv_right);

        telemetry.addLine()
                .addData("Hue (Left)", "%.3f", hsv_left[0])
                .addData("Saturation (Left)", "%.3f", hsv_left[1])
                .addData("Value (Left)", "%.3f", hsv_left[2]);
        telemetry.addLine()
                .addData("Hue (Right)", "%.3f", hsv_right[0])
                .addData("Saturation (Right)", "%.3f", hsv_right[1])
                .addData("Value (Right)", "%.3f", hsv_right[2]);

        telemetry.update();

        if (hsv_right[0] > 200 && hsv_left[0] < 200) {
            encoderTurn(.25, 90, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, 12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, -12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, -12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, -12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, -12, 5,
                    leftDrive, rightDrive);
            encoderTurn(.25, 180, 5,
                    leftDrive, rightDrive);
            fingerServo.setPosition(0.2);
            wristServo.setPosition(0.5);
            encoderDrive(.25, -1, 5,
                    leftDrive, rightDrive);
        }
        if (hsv_right[0] < 200 && hsv_left[0] > 200) {
            encoderDrive(.25, 12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, -12, 5,
                    leftDrive, rightDrive);
            encoderTurn(.25, -90, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, 12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, 12, 5,
                    leftDrive, rightDrive);
            encoderDrive(.25, 12, 5,
                    leftDrive, rightDrive);
            wristServo.setPosition(0.5);
            encoderDrive(.25, -1, 5,
                    leftDrive, rightDrive);
        }
        if (hsv_right[0] < 200 && hsv_left[0] < 200) {
            encoderDrive(SPEED, 6, 5,
                    leftDrive, rightDrive);
            encoderDrive(SPEED, -6, 5,
                    leftDrive, rightDrive);
            encoderTurn(SPEED, -90, 5,
                    leftDrive, rightDrive);
            encoderDrive(SPEED, 38, 5,
                    leftDrive, rightDrive);
            encoderDrive(SPEED, -5, 5,
                    leftDrive, rightDrive);
            fingerServo.setPosition(0.2);
            wristServo.setPosition(.5);
            encoderDrive(SPEED, -4, 5,
                    leftDrive, rightDrive);
        }
    }
}
