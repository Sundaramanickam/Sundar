package com.example.batterymonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Dashboard extends AppCompatActivity {

    MediaPlayer player;
    TextView tv_status;
    TextView tv_percentage;
    IntentFilter intentfilter;
    int deviceStatus;
    String currentBatteryStatus="";
    int batteryLevel;
    String sound_option;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board);
        Intent in=getIntent();
        sound_option=in.getStringExtra("sound_option");
        Toast.makeText(getBaseContext(),"Sound Option :" +sound_option,Toast.LENGTH_LONG).show();

        tv_status=(TextView)findViewById(R.id.textView2);
        tv_percentage=(TextView)findViewById(R.id.textView3);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        //Speak("Hello Welcome to my project");

    }

    public void monitor_click(View view) {
        Dashboard.this.registerReceiver(broadcastreceiver,intentfilter);

    }

    public void Speak(String toSpeak)
    {
        //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
        t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }
    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {

                deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryLevel = (int) (((float) level / (float) scale) * 100.0f);

                if (deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
                    tv_status.setText("Charging  ");
                    tv_percentage.setText(batteryLevel+" %");

                    if(batteryLevel ==100)
                    {
                        tv_status.setText("FULL  ");
                        if(sound_option.equals("voice"))
                        {
                            Speak("Charger . Connected. Your . Phone . Battery. Level. is . 100 . Percentage. Please . remove . charger.");
                         }
                        else
                        {
                            playfull();
                        }
                    }
                    else if (batteryLevel >=50 )
                    {
                        if(sound_option.equals("voice"))
                        {
                            Speak("Charger . Connected. Your . Phone . Battery. Level. is ."+batteryLevel +" . Percentage .");
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                    else if (batteryLevel >=30 )
                    {
                        if(sound_option.equals("voice"))
                        {
                            Speak("Charger . Connected. Your . Phone . Battery. Level. is ."+batteryLevel +" . Percentage .");
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                    else  if (batteryLevel >=20 ) // < 30 %
                    {
                        tv_status.setText("Charging & Very LOW");
                        if(sound_option.equals("voice"))
                        {
                            Speak("Charger . Connected. Your . Phone . Battery. Level. is . Very . Very. Low .  and . Battery. Level. is . "+batteryLevel+ ". Percentage ." );
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                    else   // < 2 0 %
                    {
                        tv_status.setText("Charging & Very  Very  LOW");
                        if(sound_option.equals("voice"))
                        {
                            Speak("Charger . Connected. Your . Phone . Battery. Level. is . Very . Very. Low .  and .  Battery. Level. is . "+batteryLevel+". Percentage ." );
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                }

                if (deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    tv_status.setText("Discharging");
                    tv_percentage.setText(batteryLevel+" %");
                    if(batteryLevel ==100)
                    {
                        tv_status.setText("FULL  ");
                        if(sound_option.equals("voice"))
                        {
                            Speak("Please . connect . Charger . Your . Phone . Battery. Level. is . 100 . Percentage.  ");
                        }
                        else
                        {
                            playfull();
                        }
                    }
                    else if (batteryLevel >=50 )
                    {
                        if(sound_option.equals("voice"))
                        {
                            Speak("Please . connect . Charger . Your . Phone . Battery. Level. is ."+batteryLevel +" . Percentage .");
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                    else if (batteryLevel >=30 )
                    {
                        if(sound_option.equals("voice"))
                        {
                            Speak("Please . connect . Charger . Your . Phone . Battery. Very . Level. and .  Battery. Level. is ."+batteryLevel +" . Percentage . ");
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                    else   // < 30 %
                    {
                        tv_status.setText("Discharging & LOW");
                        if(sound_option.equals("voice"))
                        {
                            Speak("Please . connect . Charger .  Your . Phone . Battery. Level. is . very . very. Low .  and .  Battery. Level. is . "+batteryLevel +". Percentage");
                        }
                        else
                        {
                            playalertsound();
                        }
                    }
                }

                if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL) {
                    tv_status.setText("Battery Full");
                    tv_percentage.setText(batteryLevel+" %");

                }

                if (deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                    tv_status.setText("Unknown");
                    tv_percentage.setText(batteryLevel+" %");
                }

                if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    tv_status.setText("Not Charging ");
                    tv_percentage.setText(batteryLevel+" %");
                }
            }catch (Exception ee)
            {
                Toast.makeText(getBaseContext(),"Error  :" +ee.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    };  //BroadcastReceiver


    public void playalertsound()
    {
        player = MediaPlayer.create(this, R.raw.alertsound);
        player.start();
    }
    public void playfull()
    {
        player = MediaPlayer.create(this, R.raw.alertsound);
        player.start();
    }
}//class