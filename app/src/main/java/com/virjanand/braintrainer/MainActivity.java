package com.virjanand.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    public static final int GAME_TIME_IN_MILLISEC = 30000;
    public static final int SECOND = 1000;

    private TextView countDown;
    private CountDownTimer countDownTimer;
    private Button startGameButton;
    private Button playAgainButton;
    private TextView doneText;

    private int correctAnswer;
    private Random random;
    private List<Integer> answers;
    private int totalNumberQuestions = 0;
    private int numberCorrect = 0;

    public void startGame(View view) {
        startGameButton.setVisibility(View.INVISIBLE);
        countDownTimer.start();
        setupGame();
    }

    public void submitAnswer(View view) {
        int answerSubmitted = Integer.parseInt(((Button) view).getText().toString());
        if (answerSubmitted == correctAnswer)
            numberCorrect++;
        totalNumberQuestions++;
        setupGame();
    }

    public void playAgain(View view) {
        countDownTimer.start();
        playAgainButton.setVisibility(View.INVISIBLE);
        doneText.setVisibility(View.INVISIBLE);
        setupGame();
        totalNumberQuestions = 0;
        numberCorrect = 0;
    }

    private void setupGame() {
        answers = new ArrayList<>();
        generateSum();
        generateAnswers();
        showGrid();
    }

    private void showScore() {
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText(String.format("%d/%d", numberCorrect, totalNumberQuestions));
    }

    private void generateAnswers() {
        int wrongAnswer1 = random.nextInt(100) + 1;
        answers.add(wrongAnswer1);
        int wrongAnswer2 = random.nextInt(100) + 1;
        answers.add(wrongAnswer2);
        int wrongAnswer3 = random.nextInt(100) + 1;
        answers.add(wrongAnswer3);
        Collections.shuffle(answers);
    }

    private void showGrid() {
        GridLayout grid = (GridLayout) findViewById(R.id.answerGridLayout);
        grid.setVisibility(View.VISIBLE);

        Button answerButton1 = (Button) findViewById(R.id.answerButton1);
        Button answerButton2 = (Button) findViewById(R.id.answerButton2);
        Button answerButton3 = (Button) findViewById(R.id.answerButton3);
        Button answerButton4 = (Button) findViewById(R.id.answerButton4);
        answerButton1.setText(answers.get(0).toString());
        answerButton2.setText(answers.get(1).toString());
        answerButton3.setText(answers.get(2).toString());
        answerButton4.setText(answers.get(3).toString());
        showScore();
    }

    private void generateSum() {
        random = new Random();
        int augend = random.nextInt(50) + 1;
        int addend = random.nextInt(50) + 1;
        correctAnswer = augend + addend;
        answers.add(correctAnswer);

        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        sumTextView.setText(augend + " + " + addend);
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
