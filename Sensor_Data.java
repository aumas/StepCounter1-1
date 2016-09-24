package magicfour.stepcounter1_1;

/**
 * Created by aumas on 2016/9/20.
 */
public class Sensor_Data {
    protected float timestamp;
    protected int type;
    protected float x;
    protected float y;
    protected float z;

    public Sensor_Data(float axis[], float timestamp) {
        this.timestamp = timestamp;
        this.x = axis[0];
        this.y = axis[1];
        this.z = axis[2];
    }

    public float getx() {
        return x;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public float getz() {
        return z;
    }

    public float gety() {
        return y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setx(float ax) {
        this.x = ax;
    }

    public void sety(float ay) {
        this.y = ay;
    }

    public void setz(float az) {
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
