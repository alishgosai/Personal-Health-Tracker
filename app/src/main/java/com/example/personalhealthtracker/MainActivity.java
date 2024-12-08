package com.example.personalhealthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import android.util.Base64;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up input fields and button
        TextInputEditText etName = findViewById(R.id.etName);
        TextInputEditText etAge = findViewById(R.id.etAge);
        TextInputEditText etWeight = findViewById(R.id.etWeight);
        TextInputEditText etHeight = findViewById(R.id.etHeight);
        Button btnNext = findViewById(R.id.btnNext);

        // adding functionality to the "Next" button
        btnNext.setOnClickListener(view -> {

            // grabbing user input from the fields
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String weightStr = etWeight.getText().toString().trim();
            String heightStr = etHeight.getText().toString().trim();

            // checking if any input field is empty
            if (name.isEmpty() || ageStr.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return; // stop further execution if there's an error
            }

            try {
                // converting inputs into proper types (int for age, float for weight and height)
                int age = Integer.parseInt(ageStr);
                float weight = Float.parseFloat(weightStr);
                float height = Float.parseFloat(heightStr);

                // setting up intent to navigate to the exercise tracking screen
                Intent intent = new Intent(MainActivity.this, ExerciseTrackingActivity.class);

                // encrypting the data before passing it to the next activity
                intent.putExtra("name", Base64.encodeToString(name.getBytes(), Base64.DEFAULT));
                intent.putExtra("age", Base64.encodeToString(String.valueOf(age).getBytes(), Base64.DEFAULT));
                intent.putExtra("weight", Base64.encodeToString(String.valueOf(weight).getBytes(), Base64.DEFAULT));
                intent.putExtra("height", Base64.encodeToString(String.valueOf(height).getBytes(), Base64.DEFAULT));

                // launching the next screen
                startActivity(intent);

            } catch (NumberFormatException e) {
                // showing an error if user inputs are not valid numbers
                Toast.makeText(MainActivity.this, "Please enter valid numbers for age, weight, and height", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
