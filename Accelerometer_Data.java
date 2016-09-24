package magicfour.stepcounter1_1;

import android.hardware.Sensor;

/**
 * Created by aumas on 2016/9/18.
 * This class is used to collect data from Accelerometer
 */
public class Accelerometer_Data extends Sensor_Data{

    public Accelerometer_Data(float acc_axis[], float acc_timestamp) {
        super(acc_axis, acc_timestamp);
        type = Sensor.TYPE_ACCELEROMETER;
    }
}
