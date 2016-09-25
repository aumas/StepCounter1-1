package edu.uiowa.dichha.stepcounter1_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class StepCounter extends AppCompatActivity {
    private ClickListener_StepCounter clickListener_stepCounter;
    Button button_start;
    Button button_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        //get views of activity here
        button_start = (Button) findViewById(R.id.button_start);
        button_stop = (Button) findViewById(R.id.button_stop);
        //Register Listener for views here
        clickListener_stepCounter = new ClickListener_StepCounter(this);
        button_start.setOnClickListener(clickListener_stepCounter);
        button_stop.setOnClickListener(clickListener_stepCounter);


    }

}
