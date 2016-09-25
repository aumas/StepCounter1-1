package edu.uiowa.dichha.stepcounter1_1;

import android.hardware.Sensor;

/**
 * Created by raidi01 on 9/20/2016.
 */
public class Accelerometer_Data extends Sensor_Data {
    public Accelerometer_Data(float acc_axis[], float acc_timestamp){
        super(acc_axis,acc_timestamp);
        type = Sensor.TYPE_ACCELEROMETER;
    }


}
