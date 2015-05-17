package com.mariehane.eksamensprojekt;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        TableLayout resultsTable = (TableLayout) findViewById(R.id.results_table);

        for (int i = 0; i < questions.length; i++) {
            TableRow row = new TableRow(this);
            row.setOnClickListener(rowClickListener);

            // Create correctIcon and add it to the row
            ImageView correctIcon = new ImageView(getApplicationContext());
            if (wins[i]) {
                correctIcon.setImageDrawable(getResources().getDrawable(R.drawable.correct_answer));
            } else {
                correctIcon.setImageDrawable(getResources().getDrawable(R.drawable.wrong_answer));
            }
            // Scale it so there's enough room for the other items
            //correctIcon.setLayoutParams(layoutParams);
            row.addView(correctIcon);

            // Create titleText and add it to the row
            TextView titleText = new TextView(getApplicationContext());
            titleText.setText(questions[i] + "\nYour answer was: ");
            if (answers[i]) {
                titleText.append("theonion");
            } else {
                titleText.append("nottheonion");
            }
            // Scale it so there's enough room for the other items
            //titleText.setLayoutParams(layoutParams);
            row.addView(titleText);

            // Create linkIcon and add it to the row
            ImageView linkIcon = new ImageView(getApplicationContext());
            linkIcon.setImageDrawable(getResources().getDrawable(R.drawable.link_icon));
            // Scale it so there's enough room for the other items
            //linkIcon.setLayoutParams(layoutParams);
            row.addView(linkIcon);

            // Add row to resultsTable
            resultsTable.addView(row);
        }
    }

    OnClickListener rowClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            TableRow row = (TableRow) view;
            TextView titleText = (TextView) row.getChildAt(1);
            System.out.println(titleText.getText());
        }
    };

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
