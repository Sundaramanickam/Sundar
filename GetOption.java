package com.example.batterymonitor;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class GetOption extends AppCompatActivity {
    RadioButton rb1;
    RadioButton rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.get_option);
            rb1 = (RadioButton) findViewById(R.id.radioButton1);
            rb2 = (RadioButton) findViewById(R.id.radioButton2);
        }catch (Exception ee)
        {
            Toast.makeText(getBaseContext(), "Error"+ee.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void next_click(View view) {
        //Toast.makeText(this, "Next Click", Toast.LENGTH_SHORT).show();
        String sound_option="";

        if(rb1.isChecked())
        {
            sound_option="voice";
        }
        else {
            sound_option="beep";
        }
        try{
            Intent in=new Intent(this,Dashboard.class);
            in.putExtra("sound_option",sound_option);
            startActivity(in);
        }catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "Error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}