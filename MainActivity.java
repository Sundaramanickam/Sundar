package com.example.batterymonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
    }
    public void optionpage_click(View view)
    {
        //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        Speak("Hi. Please . select . Alert . message . Option.");
        try{
            Intent in=new Intent(this,GetOption.class);
            startActivity(in);
        }catch(Exception e)
        {
            Toast.makeText(getBaseContext(), "Error"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void Speak(String toSpeak)
    {
        Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
}