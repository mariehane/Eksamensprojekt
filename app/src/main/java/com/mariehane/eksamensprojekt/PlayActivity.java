package com.mariehane.eksamensprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class PlayActivity extends ActionBarActivity {

    /**
     * The total amount of rounds
     */
    private final static int ROUNDS = 15;
    /**
     * The amount of titles (lines) in the file titles_theonion.txt
     */
    private static final int TITLES_THEONION = 8628;
    /**
     * The amount of titles (lines) in the file titles_nottheonion.txt
     */
    private static final int TITLES_NOTTHEONION = 10010;

    private static final boolean ANSWER_THEONION = true;
    private static final boolean ANSWER_NOTTHEONION = false;

    private String seed;
    private Random r;
    private int round;

    private String[] questions = new String[ROUNDS];
    /**
     * The correct answers for the given questions
     */
    private boolean[] answers = new boolean[ROUNDS];
    /**
     * Whether the player answered correct to the given question
     */
    private boolean[] wins = new boolean[ROUNDS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Get seed from MainActivity
        Bundle extras = getIntent().getExtras();
        seed = extras.getString(Extras.SEED);
        r = new Random(seed.hashCode());

        round = 0;
        updateQuestion();
    }

    /**
     * Gets a question from either /r/theonion or /r/nottheonion
     * The title question is then set to this question.
     */
    private void updateQuestion() {
        boolean answer = r.nextBoolean();
        answers[round] = answer;


        try {
            if (answer == ANSWER_THEONION) {
                InputStream file = getResources().openRawResource(R.raw.titles_theonion);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
                questions[round] = FileTools.readLine(bufferedReader, r.nextInt(TITLES_THEONION));
            } else {
                InputStream file = getResources().openRawResource(R.raw.titles_nottheonion);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
                questions[round] = FileTools.readLine(bufferedReader, r.nextInt(TITLES_NOTTHEONION));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update title question
        TextView question = (TextView) findViewById(R.id.question_title);
        question.setText(questions[round]);

        setTitle("Round: " + Integer.toString(round + 1));
    }


    public void click(View view, boolean answer) {
        wins[round] = answer == answers[round];

        round++;
        if (round == ROUNDS) {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra(Extras.SEED, seed);
            intent.putExtra(Extras.QUESTIONS, questions);
            intent.putExtra(Extras.ANSWERS, answers);
            intent.putExtra(Extras.WINS, wins);
            startActivity(intent);
        } else {
            updateQuestion();
        }
    }

    public void clickTheOnion(View view) {
        click(view, ANSWER_THEONION);
    }

    public void clickNotTheOnion(View view) {
        click(view, ANSWER_NOTTHEONION);
    }
}
