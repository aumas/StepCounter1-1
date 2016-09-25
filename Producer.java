package edu.uiowa.dichha.stepcounter1_1;
import android.hardware.Sensor;
import android.util.Log;
import java.util.concurrent.BlockingQueue;

/**
 * Created by raidi01 on 9/20/2016.
 */
public class Producer implements Runnable{
    private float axis[];
    private float timestamp;
    private int type;
    private BlockingQueue<Sensor_Data> shared_queue;
    private static String PROD = "PRODUCER";
    private static String PROD_DATA = "PRODUCER_DATA";

    public Producer(BlockingQueue<Sensor_Data> shared_queue,int type,float axis[], float timestamp){
        this.shared_queue = shared_queue;
        this.type = type;
        this.axis = axis;
        this.timestamp = timestamp;
    }
    @Override
    public void run(){
        try{
            Log.i(PROD, "Producing");
            Sensor_Data sensor_data = produce(type, axis, timestamp);
            Log.i(PROD_DATA, sensor_data.toString());
            shared_queue.put(sensor_data);

        }catch (InterruptedException ex){
            //handle the exception
        }

    }
    public static Sensor_Data produce(int type, float axis[], float timestamp){
        Sensor_Data sensor_data;
        if(type == Sensor.TYPE_ACCELEROMETER){
            sensor_data = new Accelerometer_Data(axis, timestamp);
        }else if(type == Sensor.TYPE_GYROSCOPE){
            sensor_data = new Gyroscope_Data(axis, timestamp);
        }else{
            sensor_data = null;
        }

        return sensor_data;
    }

}
