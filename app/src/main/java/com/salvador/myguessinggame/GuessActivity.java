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
        String result;
        int guess = 0;
        // get guess
        EditText guessText = (EditText)findViewById(R.id.editGuess);
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

            View restartView = (View)findViewById(R.id.restart_btn);
            View submitView = (View)findViewById(R.id.submit_btn);

            restartView.setVisibility(View.VISIBLE);
            submitView.setVisibility(View.GONE);
        }
        else if (guess < random) {
            result = "Try a little higher...";
        }
        else {
            result = "Try a little lower...";
        }

        TextView resultText = (TextView)findViewById(R.id.guess_prompter);
        resultText.setText(result);
    }

    public void restartGuess (View view){
        random = new Random().nextInt(100);

        View restartView = (View)findViewById(R.id.restart_btn);
        View submitView = (View)findViewById(R.id.submit_btn);

        restartView.setVisibility(View.GONE);
        submitView.setVisibility(View.VISIBLE);
    }




    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("random",random);
    }

}
