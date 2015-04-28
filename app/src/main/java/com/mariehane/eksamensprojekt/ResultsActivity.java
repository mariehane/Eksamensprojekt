package com.mariehane.eksamensprojekt;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Bundle extras = getIntent().getExtras();
        String seed = extras.getString(Extras.SEED);
        String[] questions = extras.getStringArray(Extras.QUESTIONS);
        boolean[] answers = extras.getBooleanArray(Extras.ANSWERS);
        boolean[] wins = extras.getBooleanArray(Extras.WINS);

        int totalWins = 0;
        for (boolean win : wins) {
            if (win) {
                totalWins++;
            }
        }

        TextView resultsText = (TextView) findViewById(R.id.results_text);
        resultsText.append("Seed: " + seed + "\nCorrect: " + Integer.toString(totalWins) + "/15\n\n");
        for (int i = 0; i < questions.length; i++) {
            String question = questions[i];

            String answer;
            if (answers[i]) {
                answer = "theonion";
            } else {
                answer = "nottheonion";
            }

            String winText;
            if (wins[i]) {
                winText = "correct";
            } else {
                winText = "wrong";
            }

            resultsText.append("\"" + question + "\"\nAnswer: " + answer + " (" + winText + ")\n\n");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
