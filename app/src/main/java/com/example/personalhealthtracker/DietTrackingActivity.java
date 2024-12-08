package com.example.personalhealthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;

public class DietTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_tracking);

        // input fields for diet tracking
        EditText etMealDetails = findViewById(R.id.etMealDetails);
        EditText etCaloriesConsumed = findViewById(R.id.etCaloriesConsumed);
        EditText etWaterIntake = findViewById(R.id.etWaterIntake);
        Button btnNextDiet = findViewById(R.id.btnNextDiet);

        // receiving and decrypting the data from the previous activity
        Intent intent = getIntent();
        String name = new String(Base64.decode(intent.getStringExtra("name"), Base64.DEFAULT));
        int age = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("age"), Base64.DEFAULT)));
        float weight = Float.parseFloat(new String(Base64.decode(intent.getStringExtra("weight"), Base64.DEFAULT)));
        float height = Float.parseFloat(new String(Base64.decode(intent.getStringExtra("height"), Base64.DEFAULT)));
        String exerciseType = new String(Base64.decode(intent.getStringExtra("exerciseType"), Base64.DEFAULT));
        int duration = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("duration"), Base64.DEFAULT)));
        int caloriesBurned = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("caloriesBurned"), Base64.DEFAULT)));

        // setting the button click listener for the "Next" button
        btnNextDiet.setOnClickListener(view -> {
            // capturing user inputs for diet details
            String mealDetails = etMealDetails.getText().toString().trim();
            String caloriesConsumedStr = etCaloriesConsumed.getText().toString().trim();
            String waterIntakeStr = etWaterIntake.getText().toString().trim();

            // checking if fields are empty and showing a warning
            if (mealDetails.isEmpty() || caloriesConsumedStr.isEmpty() || waterIntakeStr.isEmpty()) {
                Toast.makeText(DietTrackingActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // converting user inputs for calories and water intake into numbers
                int caloriesConsumed = Integer.parseInt(caloriesConsumedStr);
                int waterIntake = Integer.parseInt(waterIntakeStr);

                // preparing intent to navigate to the summary screen
                Intent nextIntent = new Intent(DietTrackingActivity.this, SummaryActivity.class);

                // encrypting and adding all data to the intent
                nextIntent.putExtra("name", Base64.encodeToString(name.getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("age", Base64.encodeToString(String.valueOf(age).getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("weight", Base64.encodeToString(String.valueOf(weight).getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("height", Base64.encodeToString(String.valueOf(height).getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("exerciseType", Base64.encodeToString(exerciseType.getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("duration", Base64.encodeToString(String.valueOf(duration).getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("caloriesBurned", Base64.encodeToString(String.valueOf(caloriesBurned).getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("mealDetails", Base64.encodeToString(mealDetails.getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("caloriesConsumed", Base64.encodeToString(String.valueOf(caloriesConsumed).getBytes(), Base64.DEFAULT));
                nextIntent.putExtra("waterIntake", Base64.encodeToString(String.valueOf(waterIntake).getBytes(), Base64.DEFAULT));

                // starting the summary activity
                startActivity(nextIntent);

            } catch (NumberFormatException e) {
                // show error message for invalid numeric input
                Toast.makeText(DietTrackingActivity.this, "Please enter valid numbers for calories and water intake", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
