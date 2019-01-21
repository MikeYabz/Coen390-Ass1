package com.example.assignment_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainActivity extends AppCompatActivity {

    protected Button viewGradesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        viewGradesButton = (Button) findViewById(R.id.viewGradesButton);

        viewGradesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                viewGradesButtonPressed();

            }
        });

    }

    void viewGradesButtonPressed()
    {
        Intent intent = new Intent(mainActivity.this, gradeActivity.class);
        startActivity(intent);
    }


}
