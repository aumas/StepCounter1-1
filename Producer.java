package magicfour.stepcounter1_1;

import android.hardware.Sensor;
import android.util.Log;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Created by aumas on 2016/9/18.
 */
public class Producer implements Runnable {
    private float axis[];
    private float timestamp;
    private int type;
    private BlockingQueue<Sensor_Data> queue;
    private static String PROD = "PRODUCER";
    private static final Producer instance = new Producer();

    private Producer(){
        //used for singleton
    }
    public static Producer get_instance(){
        return instance;
    }

    public Producer(BlockingQueue<Sensor_Data> q,
                    int type,
                    float axis[],
                    float timestamp) {
        this.type = type;
        this.queue = q;
        this.axis = axis;
        this.timestamp = timestamp;
    }

    @Override
    public void run() {
        try{
            queue.put(produce(type,axis,timestamp));
            Log.i(PROD,"Producing");
        }catch (InterruptedException ex){
        //handle the exception
        }
    }

    public Sensor_Data produce(int type, float axis[], float timestamp){
        Sensor_Data sensor_data;
        if (type == Sensor.TYPE_ACCELEROMETER) {
            sensor_data = new Accelerometer_Data(axis, timestamp);
        }else if (type == Sensor.TYPE_GYROSCOPE) {
            sensor_data = new Gyroscopes_Data(axis, timestamp);
        }else{
            sensor_data = null;
        }

        return sensor_data;
    }
}
