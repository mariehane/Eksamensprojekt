package com.mariehane.eksamensprojekt;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private static final int SEEDS = 10006;

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
            try {
                seed = createSeed();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        intent.putExtra(Extras.SEED, seed);
        startActivity(intent);
    }

    /**
     * Returns a memorable english string to be used as a seed
     */
    private String createSeed() throws IOException {
        InputStream file = getResources().openRawResource(R.raw.seeds);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
        String seed = FileTools.readLine(bufferedReader, new Random().nextInt(SEEDS));

        bufferedReader.close();
        file.close();

        return seed;
    }

}
