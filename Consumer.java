package magicfour.stepcounter1_1;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Environment;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by aumas on 2016/9/18.
 */
public class Consumer implements  Runnable{
    private BlockingQueue<Sensor_Data> queue;
    private Context context;
    private static final String CONS ="CONSUMER";
    private static final String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/accelerometer";
    private File file;
    private static final Consumer consumer = new Consumer();

    private Consumer(){
        //Singleton
    }
    public static Consumer get_instance(){
        return consumer;
    }

    public Consumer(BlockingQueue<Sensor_Data> queue,Context context) {
        this.queue = queue;
        this.context = context;
        file = new File(PATH + "/acc.txt");
    }

    @Override
    public void run() {
        Sensor_Data sensor_Data = queue.poll();
            if(sensor_Data == null){
            }else{
                //write to file
                Log.i(CONS,sensor_Data.toString());
                String saveText = String.valueOf(sensor_Data.toString());
                save(saveText);
            }
    }

    public void save(String saveText)
    {
        try {
            FileOutputStream outStream= context.openFileOutput("acc3.txt",Context.MODE_APPEND);
            outStream.write(saveText.getBytes());
            outStream.close();
            Log.i(CONS, outStream.toString());
            Log.i(CONS, "SAVE DATA");
            Log.i(CONS,saveText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
