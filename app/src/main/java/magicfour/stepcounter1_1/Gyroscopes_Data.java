package magicfour.stepcounter1_1;

/**
 * Created by aumas on 2016/9/20.
 */
public class Gyroscopes_Data extends Sensor_Data {
    public Gyroscopes_Data(float acc_axis[], float acc_timestamp) {
        super(acc_axis, acc_timestamp);
        type = "GYR";
    }
}
