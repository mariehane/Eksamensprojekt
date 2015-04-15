package com.mariehane.eksamensprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class PlayActivity extends ActionBarActivity {

    private final static String EXTRA_SEED = "com.mariehane.eksamensprojekt.SEED";
    private final static String EXTRA_QUESTIONS = "com.mariehane.eksamensprojekt.QUESTIONS";
    private final static String EXTRA_ANSWERS = "com.mariehane.eksamensprojekt.ANSWERS";
    private final static String EXTRA_WINS = "com.mariehane.eksamensprojekt.WINS";

    /**
     * The total amount of rounds
     */
    private final static int ROUNDS = 15;
    private static final boolean ANSWER_THEONION = true;
    private static final boolean ANSWER_NOTTHEONION = false;

    private String seed;
    private Random r;
    private int round;
    private String[] questions = new String[ROUNDS];
    private boolean[] answers = new boolean[ROUNDS];
    private boolean[] wins = new boolean[ROUNDS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Get seed from MainActivity
        Bundle extras = getIntent().getExtras();
        seed = extras.getString(EXTRA_SEED);
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

        if (answer == ANSWER_THEONION) {
            questions[round] = "HEJ";
        } else {
            questions[round] = "hej med dig";
        }

        // Get question from reddit
        // & 0xffffffffL;

        // Update title question
        TextView question = (TextView) findViewById(R.id.question_title);
        question.setText(questions[round]);

        setTitle("Round: " + Integer.toString(round + 1) + " Seed: " + seed);
    }


    public void click(View view, boolean answer) {
        wins[round] = answer == answers[round];

        round++;
        if (round == ROUNDS) {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra(EXTRA_SEED, seed);
            intent.putExtra(EXTRA_QUESTIONS, questions);
            intent.putExtra(EXTRA_ANSWERS, EXTRA_ANSWERS);
            intent.putExtra(EXTRA_WINS, wins);
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
