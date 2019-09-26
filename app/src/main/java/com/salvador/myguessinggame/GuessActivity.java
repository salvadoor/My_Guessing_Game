package com.salvador.myguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {
    private String result; // used to provide hints for prompter text box
    private int random;
    public static final String EXTRA_RANGE = "range";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        // get intent, get range value and generate random number
        Intent intent = getIntent();
        int range = intent.getExtras().getInt(EXTRA_RANGE);
        Toast.makeText(getApplicationContext(),
                  "New Game, number is between 0 and " + (range-1),
                       Toast.LENGTH_LONG).show();

        random = new Random().nextInt(range);

        // Restore saved random value if one exists
        if (savedInstanceState != null) {
            random = savedInstanceState.getInt("random");
        }

    }

    public void submitGuess(View view) {
        int guess = 0; // user submitted guess
        // get guess
        EditText guessText = findViewById(R.id.editGuess);
        String guessString = guessText.getText().toString();

        // check for input type
        // input should already be limited to combinations of numbers 0-9
        try {
            guess = Integer.parseInt(guessString);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.invalid_input),
                    Toast.LENGTH_SHORT).show();
        }

        // provide hint to user or let them know they guessed correctly
        if (guess == random) {
            result = "You guessed the magic number!!";
        }
        else if (guess < random) {
            result = "Try a little higher...";
        }
        else {
            result = "Try a little lower...";
        }
        // update text for prompter
        setPrompter(result);
    }

    // close activity and go back to MenuActivity to start a new game
    // same as using back button
    public void restartGuess (View view){
        finish();
    }

    // set new text hints for the prompter
    public void setPrompter (String text){
        TextView resultText = findViewById(R.id.guess_prompter);
        resultText.setText(text);
    }

    // save random number
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("random",random);
    }

}
