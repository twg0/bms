package com.alddeul.capstone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class
state_data extends AppCompatActivity implements View.OnClickListener{
    private LineChart lineChart;
    TextView textView1, textView2, textView3, toolbar;
    String vname, name, message, temp_data[], humid_data[], time_data[];
    Integer size = 0;
    MainActivity m = new MainActivity();
    Button button1,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_data);

        Intent intent = getIntent();
        vname = intent.getStringExtra("nickname");
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        temp_data = intent.getStringArrayExtra("temperature");
        humid_data = intent.getStringArrayExtra("humidity");
        time_data = intent.getStringArrayExtra("detect_time");
        size = intent.getIntExtra("size", size);
        Log.d("state", "size" + size);

        /*
        try {
            Log.d("state", "temp" + temp_data[0]);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
*/
        toolbar = findViewById(R.id.toolbar_title);
        toolbar.setText(vname + "??????" + name + "???");



        ArrayList<Entry> entry_chart1 = new ArrayList<>(); // ???????????? ?????? Arraylist
        ArrayList<Entry> entry_chart2 = new ArrayList<>();

        lineChart = (LineChart) findViewById(R.id.chart);

        LineData chartData = new LineData(); // ????????? ?????? ?????????

        entry_chart1.add(new Entry(1710, (float) 23.3)); //entry_chart1??? ?????? ???????????? ?????????.
        entry_chart1.add(new Entry(1711, (float) 20.2));
        entry_chart1.add(new Entry(1712, (float) 19.2));
        entry_chart1.add(new Entry(1713, (float) 20.4));
        entry_chart1.add(new Entry(1714, (float) 22.8));


        entry_chart2.add(new Entry(1710, (float) 21.3)); //entry_chart2??? ?????? ???????????? ?????????.
        entry_chart2.add(new Entry(1711, (float) 29.2));
        entry_chart2.add(new Entry(1712, (float) 28.2));
        entry_chart2.add(new Entry(1713, (float) 25.4));
        entry_chart2.add(new Entry(1714, (float) 23.8));


        LineDataSet lineDataSet1 = new LineDataSet(entry_chart1, "Temperature"); // ???????????? ?????? Arraylist ??? LineDataSet ?????? ????????????.
        LineDataSet lineDataSet2 = new LineDataSet(entry_chart2, "Humidity");

        lineDataSet1.setColor(Color.RED); // ?????? LineDataSet??? ??? ?????? :: ??? Line ??? ????????? ????????? ????????? ????????????.
        lineDataSet1.setCircleColor(Color.RED); // ????????? ?????? ??????
        lineDataSet2.setColor(Color.BLACK);
        lineDataSet2.setCircleColor(Color.BLACK);


        chartData.addDataSet(lineDataSet1); // ?????? LineDataSet ??? ????????? ????????? ????????? DataSet ??? ?????????.
        chartData.addDataSet(lineDataSet2);


        XAxis xAxis = lineChart.getXAxis(); // x ??? ??????
        xAxis.setPosition(XAxis.XAxisPosition.TOP); //x ??? ????????? ?????? ?????? ??????
        xAxis.setLabelCount(5, true); //X?????? ???????????? ?????? ?????? ?????? ??????????????? ?????? ?????? 5??? force??? true ?????? ????????? ?????????
        xAxis.setValueFormatter(new ValueFormatter() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public String getFormattedValue(float value) {

                String now_time = "" + (int) (value / 100) + ":" + (int) (value % 100);
                return now_time;

            }
        });


        YAxis yAxisRight = lineChart.getAxisRight(); //Y?????? ???????????? ??????
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        //y?????? ???????????? ?????????

        lineChart.setData(chartData); // ????????? ?????? DataSet??? ?????????.

        lineChart.invalidate(); // ?????? ????????????
        lineChart.setTouchEnabled(false); // ?????? ?????? disable
        lineChart.setDescription(null);

        textView1 = findViewById(R.id.gas);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

/*

        try {
            if (message.charAt(0) == '???')
                textView1.setText(message);

            if (message.charAt(0) == '???')
                textView2.setText(message);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.d("state", "message empty");
        }

*/

    }

    @Override
    public void onClick(View view) {

        if(view == button1) {

            button1.setBackgroundResource(R.drawable.background_round);
            button2.setBackgroundResource(R.drawable.background_round2);
            button3.setBackgroundResource(R.drawable.background_round2);
            button4.setBackgroundResource(R.drawable.background_round2);
        }
        else if(view == button2){
            button1.setBackgroundResource(R.drawable.background_round2);
            button2.setBackgroundResource(R.drawable.background_round);
            button3.setBackgroundResource(R.drawable.background_round2);
            button4.setBackgroundResource(R.drawable.background_round2);
        }
        else if(view == button3){
            button1.setBackgroundResource(R.drawable.background_round2);
            button2.setBackgroundResource(R.drawable.background_round2);
            button3.setBackgroundResource(R.drawable.background_round);
            button4.setBackgroundResource(R.drawable.background_round2);
        }
        else if(view == button4){
            button1.setBackgroundResource(R.drawable.background_round2);
            button2.setBackgroundResource(R.drawable.background_round2);
            button3.setBackgroundResource(R.drawable.background_round2);
            button4.setBackgroundResource(R.drawable.background_round);
        }
    }
}