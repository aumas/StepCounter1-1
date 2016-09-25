package edu.uiowa.dichha.stepcounter1_1;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;

/**
 * Created by raidi01 on 9/20/2016.
 */
public class Consumer implements Runnable{
    private BlockingQueue<Sensor_Data> shared_queue;
    private Context context;
    private static final String CONS = "CONSUMER";
    private static final String CONS_DATA = "CONSUMER_DATA";
    private static final String PATH_NAME = "PATH_NAME";

    //private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Accelerometer";
    //private String saveFileName = "sensorData.txt";

    private File file;
    public Consumer(BlockingQueue<Sensor_Data> shared_queue, Context context){
        this.shared_queue = shared_queue;
        this.context = context;
        //file = new File(PATH + "/acc1.txt");
    }

    @Override
    public void run(){
        Sensor_Data sensor_data = null;
        try {
            sensor_data = shared_queue.take();//retrieves and removes the head of this shared_queue
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(sensor_data == null){

        }else{
            Log.i(CONS, "Consuming");
            Log.i(CONS_DATA, sensor_data.toString());
            String saveText = String.valueOf(sensor_data.toString());
            save(saveText);
        }
    }
    public void save(String saveText){
        try{
            FileOutputStream outStream = context.openFileOutput("acc1.txt", Context.MODE_APPEND);
            //Log.i("path_test0", String.valueOf(context.getFileStreamPath("acc1.txt")));
            outStream.write(saveText.getBytes());
            //flush the content to the underlying stream
            //outStream.flush();
            outStream.close();


            Log.i(CONS, outStream.toString());
            Log.i(CONS, "SAVE DATA");
            Log.i(CONS, saveText);
            //Log.i(PATH_NAME, file.getPath());

        }catch(FileNotFoundException e){


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
