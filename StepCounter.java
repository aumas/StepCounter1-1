package magicfour.stepcounter1_1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewManager;
import android.widget.Button;

public class StepCounter extends AppCompatActivity {
    private ClickListener_StepCounter clickListener_stepCounter;
    private ActivityManager activityManager;
    private StepService stepService;
    Button button_start;
    Button button_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
// Get views of Activity here
        button_start = (Button) findViewById(R.id.button_start);
        button_stop  = (Button) findViewById(R.id.button_stop);
// Register Listener for views Here
        clickListener_stepCounter = new ClickListener_StepCounter(this);
        button_start.setOnClickListener(clickListener_stepCounter);
        button_stop.setOnClickListener(clickListener_stepCounter);

    }
}
