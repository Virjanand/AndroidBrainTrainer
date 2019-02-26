package com.virjanand.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int GAME_TIME_IN_MILLISEC = 10000;
    public static final int SECOND = 1000;
    private TextView countDown;
    private CountDownTimer countDownTimer;
    private Button startGameButton;
    private Button playAgainButton;
    private TextView doneText;

    public void startGame(View view) {
        countDownTimer.start();
        startGameButton.setVisibility(View.INVISIBLE);
    }

    public void playAgain(View view) {
        countDownTimer.start();
        playAgainButton.setVisibility(View.INVISIBLE);
        doneText.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDown = (TextView) findViewById(R.id.timerTextView);
        startGameButton = (Button) findViewById(R.id.startGameButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        doneText = (TextView) findViewById(R.id.doneTextView);

        countDownTimer = new CountDownTimer(GAME_TIME_IN_MILLISEC, SECOND) {
            public void onTick(long milliSecondsUntilDone) {
                updateCountDown(milliSecondsUntilDone);
            }

            public void onFinish() {
                updateCountDown(0);
                doneText.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        };
    }

    private void updateCountDown(long milliSecondsUntilDone) {
        countDown.setText(milliSecondsUntilDone / 1000 + "s");
    }
}
