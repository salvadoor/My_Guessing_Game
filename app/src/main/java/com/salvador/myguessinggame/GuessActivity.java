package com.salvador.myguessinggame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {
    private String result;
    private int random = new Random().nextInt(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        if (savedInstanceState != null) {
            random = savedInstanceState.getInt("random");
        }
    }

    public void submitGuess(View view) {
        int guess = 0;
        // get guess
        EditText guessText = findViewById(R.id.editGuess);
        String guessString = guessText.getText().toString();

        try {
            guess = Integer.parseInt(guessString);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.invalid_input),
                    Toast.LENGTH_SHORT).show();
        }


        if (guess == random) {
            result = "You guessed the magic number!!";
        }
        else if (guess < random) {
            result = "Try a little higher...";
        }
        else {
            result = "Try a little lower...";
        }

        setPrompter(result);
    }

    public void restartGuess (View view){
        random = new Random().nextInt(100);

        result = "New game started, guess the new number";
        setPrompter(result);
    }

    public void setPrompter (String text){
        TextView resultText = findViewById(R.id.guess_prompter);
        resultText.setText(text);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("random",random);
    }

}
