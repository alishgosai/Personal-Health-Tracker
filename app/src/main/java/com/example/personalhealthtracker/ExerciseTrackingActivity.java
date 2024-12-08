package com.example.personalhealthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;

public class ExerciseTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tracking);

        // inputs for exercise type, duration, and calories burned
        EditText etExerciseType = findViewById(R.id.etExerciseType);
        EditText etDuration = findViewById(R.id.etDuration);
        EditText etCaloriesBurned = findViewById(R.id.etCaloriesBurned);
        Button btnNextExercise = findViewById(R.id.btnNextExercise);

        // receiving the data passed from MainActivity and decrypting it
        Intent intent = getIntent();
        String name = new String(Base64.decode(intent.getStringExtra("name"), Base64.DEFAULT));
        int age = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("age"), Base64.DEFAULT)));
        float weight = Float.parseFloat(new String(Base64.decode(intent.getStringExtra("weight"), Base64.DEFAULT)));
        float height = Float.parseFloat(new String(Base64.decode(intent.getStringExtra("height"), Base64.DEFAULT)));

        // setting up the button click functionality
        btnNextExercise.setOnClickListener(view -> {
            // fetching user inputs for exercise details
            String exerciseType = etExerciseType.getText().toString().trim();
            String durationStr = etDuration.getText().toString().trim();
            String caloriesBurnedStr = etCaloriesBurned.getText().toString().trim();

            // checking if fields are empty and showing a warning
            if (exerciseType.isEmpty() || durationStr.isEmpty() || caloriesBurnedStr.isEmpty()) {
                Toast.makeText(ExerciseTrackingActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return; // stop further execution if validation fails
            }

            // converting duration and calories into numeric values
            int duration = Integer.parseInt(durationStr);
            int caloriesBurned = Integer.parseInt(caloriesBurnedStr);

            // preparing intent to navigate to the diet tracking screen
            Intent nextIntent = new Intent(ExerciseTrackingActivity.this, DietTrackingActivity.class);

            // encrypting and adding all the data to the intent
            nextIntent.putExtra("name", Base64.encodeToString(name.getBytes(), Base64.DEFAULT));
            nextIntent.putExtra("age", Base64.encodeToString(String.valueOf(age).getBytes(), Base64.DEFAULT));
            nextIntent.putExtra("weight", Base64.encodeToString(String.valueOf(weight).getBytes(), Base64.DEFAULT));
            nextIntent.putExtra("height", Base64.encodeToString(String.valueOf(height).getBytes(), Base64.DEFAULT));
            nextIntent.putExtra("exerciseType", Base64.encodeToString(exerciseType.getBytes(), Base64.DEFAULT));
            nextIntent.putExtra("duration", Base64.encodeToString(String.valueOf(duration).getBytes(), Base64.DEFAULT));
            nextIntent.putExtra("caloriesBurned", Base64.encodeToString(String.valueOf(caloriesBurned).getBytes(), Base64.DEFAULT));

            // starting the next activity
            startActivity(nextIntent);
        });
    }
}
