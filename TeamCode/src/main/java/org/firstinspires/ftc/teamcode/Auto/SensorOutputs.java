package org.firstinspires.ftc.teamcode.Auto;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous(name = "Color Sensor Data", group = "Data")
public class SensorOutputs extends LinearOpMode {
    NormalizedColorSensor left_sensor;
    NormalizedColorSensor right_sensor;
    @Override public void runOpMode() {
        left_sensor = hardwareMap.get(NormalizedColorSensor.class, "left_sensor");
        right_sensor = hardwareMap.get(NormalizedColorSensor.class, "right_sensor");

        float[] hl = new float[3]; // java???
        float[] hr = new float[3];

        waitForStart();
        while (opModeIsActive()) {
            NormalizedRGBA cl = left_sensor.getNormalizedColors();
            NormalizedRGBA cr = right_sensor.getNormalizedColors();

            Color.colorToHSV(cl.toColor(), hl);
            Color.colorToHSV(cr.toColor(), hr);

            telemetry.addLine("--- LEFT ---");
            telemetry.addLine()
                    .addData("H", "%.3f", hl[0])
                    .addData("S", "%.3f", hl[1])
                    .addData("V", "%.3f", hl[2]);
            telemetry.addLine()
                    .addData("R", cl.red)
                    .addData("G", cl.green)
                    .addData("B", cl.blue);
            telemetry.addLine("--- RIGHT ---");
            telemetry.addLine()
                    .addData("H", "%.3f", hr[0])
                    .addData("S", "%.3f", hr[1])
                    .addData("V", "%.3f", hr[2]);
            telemetry.addLine()
                    .addData("R", cr.red)
                    .addData("G", cr.green)
                    .addData("B", cr.blue);

            telemetry.update();
        }
    }
}
