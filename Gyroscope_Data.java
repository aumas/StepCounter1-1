package edu.uiowa.dichha.stepcounter1_1;

import android.hardware.Sensor;

/**
 * Created by raidi01 on 9/24/2016.
 */
public class Gyroscope_Data extends Sensor_Data{
    public Gyroscope_Data(float gyros_axis[], float timestamp){
        super(gyros_axis, timestamp);
        type = Sensor.TYPE_GYROSCOPE;

    }
}
