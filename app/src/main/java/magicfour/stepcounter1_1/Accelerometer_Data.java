package magicfour.stepcounter1_1;

/**
 * Created by aumas on 2016/9/18.
 * This class is used to collect data from Accelerometer
 */
public class Accelerometer_Data {
    private float ax;
    private float ay;
    private float az;
    private float timestamp;

    public Accelerometer_Data(float axis[], float timestamp) {
        this.timestamp = timestamp;
        this.ax = axis[0];
        this.ay = axis[1];
        this.az = axis[2];
    }

    public float getAx() {
        return ax;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public float getAz() {
        return az;
    }

    public float getAy() {
        return ay;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public void setAy(float ay) {
        this.ay = ay;
    }

    public void setAz(float az) {
        this.az = az;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public String toString(){
        return String.valueOf(this.ax) +','+String.valueOf(this.ay)+','+String.valueOf(this.az)+','+String.valueOf(this.timestamp)+"\n";
    }
}
