package com.salvador.myguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Hide Action Bar
        getSupportActionBar().hide();

        // Set onClickListeners for the three buttons
        Button one = (Button) findViewById(R.id.btn_one);
        one.setOnClickListener(this);

        Button two = (Button) findViewById(R.id.btn_two);
        two.setOnClickListener(this);

        Button three = (Button) findViewById(R.id.btn_three);
        three.setOnClickListener(this);

    }

    // set range depending on the option that was clicked
    // start GuessActivity and pass the range value
    @Override
    public void onClick(View view){
        int range = 1;

        switch(view.getId()){
            case R.id.btn_one:
                range = 10;
                break;
            case R.id.btn_two:
                range = 100;
                break;
            case R.id.btn_three:
                range = 1000;
                break;
            default:
                break;
        }

        Intent intent = new Intent(this, GuessActivity.class);
        intent.putExtra("range", range);
        startActivity(intent);
    }


}
