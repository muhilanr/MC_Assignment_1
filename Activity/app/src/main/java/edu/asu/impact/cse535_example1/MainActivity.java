package edu.asu.impact.cse535_example1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, float[]> id_map=new HashMap<Integer, float[]>();
    private String[] names={"Tom Hanks","Leonardo DiCaprio","Brad Pitt","George Clooney","Ben Affleck","Henry Cavill","Matt Dammon",
            "Hugh Jackman","Jennifer Lawrence","Amanda Seyfried","Amber Heard","Julia Roberts","Sandra Bullock",
            "Angelina Jolie","Scarlett Johanson"};
    private int[] age={57,45,42,56,44,32,34,44,27,28,26,45,50,39,31};
    GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       for(int i=0;i<15;i++)
        {
            float[] value=new float[15];
            Random rgen=new Random();
            for(int j=0;j<15;j++) {
                value[j] = rgen.nextFloat()*2000;
            }
           id_map.put(i+1,value);
        }

        float[] values = new float[15];
        String[] verlabels = new String[] { "2000", "1500", "1000", "500", "0" };
        String[] horlabels = new String[] { "2000", "2200", "2400", "2600", "2800", "3000", "3200", "3400", "3600", "3800", "4000",
                "4200", "4400", "4600", "4800"};

        graphView = new GraphView(MainActivity.this, values , "GraphViewDemo",horlabels, verlabels, GraphView.LINE);
        LinearLayout ll= (LinearLayout) findViewById(R.id.theGraph);
        ll.addView(graphView);
    }


    public void onRun(View v){
        //Toast.makeText(MainActivity.this,"Great",Toast.LENGTH_LONG).show();
        EditText pid_field = (EditText) findViewById(R.id.p_id);
        String pid_str = pid_field.getText().toString();
        if(pid_str.matches("")) {
            Toast.makeText(MainActivity.this, "Enter details and before running", Toast.LENGTH_LONG).show();
        }
        else {
            int pid = Integer.parseInt(pid_str);
            graphView.setValues(id_map.get(pid));
            LinearLayout ll = (LinearLayout) findViewById(R.id.theGraph);
            ll.removeAllViews();
            ll.addView(graphView);
        }
    }


    public void onStop(View v){
        float[] values = new float[15];
        graphView.setValues(values);
        LinearLayout ll= (LinearLayout) findViewById(R.id.theGraph);
        ll.removeAllViews();
        ll.addView(graphView);
    }
}
