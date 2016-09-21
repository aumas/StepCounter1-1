package magicfour.stepcounter1_1;

/**
 * Created by aumas on 2016/9/20.
 */
public class Sensor_Data {
    protected float timestamp;
    protected String type;
    protected float x;
    protected float y;
    protected float z;

    public Sensor_Data(float axis[], float timestamp) {
        this.timestamp = timestamp;
        this.x = axis[0];
        this.y = axis[1];
        this.z = axis[2];
    }

    public float getAx() {
        return x;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public float getAz() {
        return z;
    }

    public float getAy() {
        return y;
    }

    public void setAx(float ax) {
        this.x = ax;
    }

    public void setAy(float ay) {
        this.y = ay;
    }

    public void setAz(float az) {
        this.z = az;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public String toString(){
        return String.valueOf(this.timestamp)+','+
                String.valueOf(this.type)+','+
                String.valueOf(this.x) +','+
                String.valueOf(this.y)+','+
                String.valueOf(this.z)+','+"\n";
    }
}
