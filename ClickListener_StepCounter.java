package magicfour.stepcounter1_1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static magicfour.stepcounter1_1.R.id.button_start;
import static magicfour.stepcounter1_1.R.id.button_stop;

/**
 * Created by aumas on 2016/9/18.
 */
public class ClickListener_StepCounter implements View.OnClickListener{

        private Context context;

        public ClickListener_StepCounter(Context context){
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //Button start : start service
                case button_start:
                    Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show();
                    Intent intentStart = new Intent(context,StepService.class);
                    context.startService(intentStart);
                    break;
                //Button stop : stop service and unregister Listener
                case button_stop:
                    Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show();
                    Intent intentStop = new Intent(context,StepService.class);
                    context.stopService(intentStop);
                    read();
                    break;
                default:
                    break;
            }
        }

    public void read() {
        String message;
        try {
            FileInputStream fileInputStream = context.openFileInput("acc3.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((message=bufferedReader.readLine())!=null){
                stringBuffer.append(message + "\n");
                Log.i("Test", "Read " + stringBuffer.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
