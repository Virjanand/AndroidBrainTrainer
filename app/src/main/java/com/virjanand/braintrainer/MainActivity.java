package com.virjanand.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView countDown;
    private CountDownTimer countDownTimer;

    public void startGame(View view) {
        countDownTimer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDown = (TextView) findViewById(R.id.timerTextView);

        countDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long milliSecondsUntilDone) {
                updateCountDown(milliSecondsUntilDone);
            }

            public void onFinish() {
                updateCountDown(0);
            }
        };
    }

    private void updateCountDown(long milliSecondsUntilDone) {
        countDown.setText(milliSecondsUntilDone / 1000 + "s");
    }
}
