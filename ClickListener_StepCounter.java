package edu.uiowa.dichha.stepcounter1_1;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static edu.uiowa.dichha.stepcounter1_1.R.id.button_start;
import static edu.uiowa.dichha.stepcounter1_1.R.id.button_stop;

/**
 * Created by raidi01 on 9/20/2016.
 */
public class ClickListener_StepCounter implements View.OnClickListener{
    private Context context;



    public ClickListener_StepCounter(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            //Button start: start service
            case button_start:
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show();
                Intent intentStart = new Intent(context, StepService.class);
                context.startService(intentStart);
                break;
            case button_stop:
                read();
                Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show();
                Intent intentStop = new Intent(context, StepService.class);
                Log.i("path_test", String.valueOf(context.getFileStreamPath("acc1.txt")));
                context.stopService(intentStop);

                break;
            default:
                break;
        }
    }
    public void read(){
        String message;
        try{
            FileInputStream fileInputStream = context.openFileInput("acc1.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            File sdCard = Environment.getExternalStorageDirectory();
            File file = new File(sdCard.getAbsolutePath()+"/sensorData.txt");
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            //StringBuffer stringBuffer = new StringBuffer();
            while((message = bufferedReader.readLine())!= null){
                //stringBuffer.append(message + "\n");
                Log.i("Test","Read: " + message);
                outputStreamWriter.write(message+"\n");
            }
            fileOutputStream.close();
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
