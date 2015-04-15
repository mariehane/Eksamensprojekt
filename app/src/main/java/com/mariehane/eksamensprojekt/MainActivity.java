package com.mariehane.eksamensprojekt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_SEED = "com.mariehane.eksamensprojekt.SEED";
    public final static String EXTRA_ROUND = "com.mariehane.eksamensprojekt.ROUND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * Called when the user clicks the Start button
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        EditText editText = (EditText) findViewById(R.id.seed_input);
        String seed = editText.getText().toString();
        if (seed.isEmpty()) {
            seed = createSeed();
        }
        intent.putExtra(EXTRA_ROUND, 1);
        intent.putExtra(EXTRA_SEED, seed);
        startActivity(intent);
    }

    private String createSeed() {
        switch (new Random().nextInt(12)) {
            case 0:
                return "alpha";
            case 1:
                return "beta";
            case 2:
                return "cupcake";
            case 3:
                return "donut";
            case 4:
                return "eclair";
            case 5:
                return "froyo";
            case 6:
                return "gingerbread";
            case 7:
                return "honeycomb";
            case 8:
                return "ice cream sandwich";
            case 9:
                return "jelly bean";
            case 10:
                return "kitkat";
            case 11:
                return "lollipop";
        }
        return null;
    }
}
