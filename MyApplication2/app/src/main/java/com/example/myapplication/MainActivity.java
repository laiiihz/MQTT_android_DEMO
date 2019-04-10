package com.example.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public static int MsgTime=0;
    public static String MsgContext="Connecting";
    public static String MyID="get fail";
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ClientF clientF=new ClientF();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                TextView textView=findViewById(R.id.textView);
                textView.setText(MsgContext);
                if(!MsgContext.equals("Connecting")){
                    ProgressBar progressBar=findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.INVISIBLE);
                }
                handler.postDelayed(this,1000);
            }
        };
        handler.postDelayed(runnable,1000);


        try {
            clientF.start();
            TextView textView=findViewById(R.id.textViewId);
            textView.setText(MyID);
        } catch (Exception e) {
            TextView textView=findViewById(R.id.textView);
            textView.setText(getString(R.string.connect_fail));
            e.printStackTrace();
        }
    }




}
