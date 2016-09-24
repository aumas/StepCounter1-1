package magicfour.stepcounter1_1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StepService extends Service implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private BlockingQueue<Sensor_Data> queue = new ArrayBlockingQueue<Sensor_Data>(200);
    private ExecutorService pool = Executors.newFixedThreadPool(200);

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //Listen Acc
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        //Listen Gyr
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this, gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Producer producer;
        Log.i("Test","SenorEvent");
        //Producer producer = Producer.get_instance();
        //Consumer consumer = Consumer.get_instance();
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            producer = new Producer(queue,Sensor.TYPE_ACCELEROMETER,event.values,event.timestamp);
            new Thread(producer).start();
        }else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            producer = new Producer(queue,Sensor.TYPE_GYROSCOPE,event.values,event.timestamp);
            new Thread(producer).start();
        }else{
            producer = null;
            return;
        }

        Consumer consumer = new Consumer(queue,getApplicationContext());

        //start Consumer
        new Thread(consumer).start();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        sensorManager.unregisterListener(this, accelerometer);
        stopSelf();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
