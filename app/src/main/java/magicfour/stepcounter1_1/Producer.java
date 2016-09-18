package magicfour.stepcounter1_1;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Created by aumas on 2016/9/18.
 */
public class Producer implements Runnable {
    private float axis[];
    private float timestamp;
    private BlockingQueue<Accelerometer_Data> queue;
    private static String PROD = "PRODUCER";

    public Producer(BlockingQueue<Accelerometer_Data> q,float axis[], float timestamp) {
        this.queue = q;
        this.axis = axis;
        this.timestamp = timestamp;
    }

    @Override
    public void run() {
        try{
            queue.put(produce(axis,timestamp));
            Log.i(PROD,"Producing");
        }catch (InterruptedException ex){
        //handle the exception
        }
    }

    public Accelerometer_Data produce(float axis[], float timestamp){
        Accelerometer_Data accelerometer_data = new Accelerometer_Data(axis,timestamp);
        return accelerometer_data;
    }
}
