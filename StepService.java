package edu.uiowa.dichha.stepcounter1_1;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;*/

/**
 * Created by raidi01 on 9/20/2016.
 */
public class StepService extends Service implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private BlockingQueue<Sensor_Data> shared_queue = new ArrayBlockingQueue<Sensor_Data>(10);

    //onStartCommand is called by a system every time a client explicitly starts the service by calling startService(Intent)
    @Override
    public int onStartCommand(Intent intent,int flag, int startId){
        Log.i("AccelerometerService", "Received Start id "+ startId + ": " + intent);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY; //used for services that are explicitly started and stopped as needed
    }
    /*
    //methods in Activity Class
    @Override
    public void onResume(){
        super.onResume();

    }
    @Override
    public void onPause(){

    }*/
    @Override
    public IBinder onBind(Intent intent){
        // TODO: Return the communication channel to the service
        throw  new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        Log.i("Test", "Sensor Event");
        //Producer producer;
        Sensor sensor = event.sensor;


        /*if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            producer = new Producer(shared_queue,Sensor.TYPE_ACCELEROMETER,event.values,event.timestamp);
            Thread prodThread = new Thread(producer);
            prodThread.start();

        }else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            producer = new Producer(shared_queue,Sensor.TYPE_GYROSCOPE,event.values,event.timestamp);//TYPE_ returns int
            Thread prodThread = new Thread(producer);
            prodThread.start();
        }*/

        Producer producer= new Producer(shared_queue,sensor.getType(), event.values, event.timestamp);
        Consumer consumer = new Consumer(shared_queue, getApplicationContext());//getApplicationContext() returns the context of the single, global Application object of the current process.

        //creating Producer, Consumer Thread
        Thread prodThread = new Thread(producer);
        Thread consThread = new Thread(consumer);

        //starting Consumer Thread
        consThread.start();
        prodThread.start();
    }

    @Override
    public void onDestroy(){
        Toast.makeText(this, "Service done!", Toast.LENGTH_SHORT).show();
        sensorManager.unregisterListener(this, accelerometer);
        sensorManager.unregisterListener(this, gyroscope);
        stopSelf();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}

