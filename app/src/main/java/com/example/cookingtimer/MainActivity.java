package com.example.cookingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean counterActive = false;
    Button startButton ;
    CountDownTimer countDownTimer;
    public void reset(){
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        startButton.setText("Start!");
        counterActive = false;
    }
    public void buttonClick(View view){
        if(counterActive){
            reset();
        }else{
            counterActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("Stop!");



           countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000 + 100, 1000) {

                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                public void onFinish() {
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.horn);
                    player.start();
                    reset();

                }
            }.start();
        }



    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft / 60 ;
        int seconds = secondsLeft - (minutes*60);
        String secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString = "0"+secondString;
        }
        timerTextView.setText(Integer.toString(minutes)+ ":" + secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
