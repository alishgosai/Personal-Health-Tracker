package com.example.personalhealthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // reference to the TextView where we’ll display the summary
        TextView tvSummary = findViewById(R.id.tvSummary);

        // get the intent that started this activity
        Intent intent = getIntent();

        // decrypting all the data that was passed from the previous activities
        String name = new String(Base64.decode(intent.getStringExtra("name"), Base64.DEFAULT));
        int age = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("age"), Base64.DEFAULT)));
        float weight = Float.parseFloat(new String(Base64.decode(intent.getStringExtra("weight"), Base64.DEFAULT)));
        float height = Float.parseFloat(new String(Base64.decode(intent.getStringExtra("height"), Base64.DEFAULT)));
        String exerciseType = new String(Base64.decode(intent.getStringExtra("exerciseType"), Base64.DEFAULT));
        int duration = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("duration"), Base64.DEFAULT)));
        int caloriesBurned = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("caloriesBurned"), Base64.DEFAULT)));
        String mealDetails = new String(Base64.decode(intent.getStringExtra("mealDetails"), Base64.DEFAULT));
        int caloriesConsumed = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("caloriesConsumed"), Base64.DEFAULT)));
        int waterIntake = Integer.parseInt(new String(Base64.decode(intent.getStringExtra("waterIntake"), Base64.DEFAULT)));

        // building the summary text to display all the collected data
        String summaryText = "Profile:\n" +
                "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Weight: " + weight + " kg\n" +
                "Height: " + height + " cm\n\n" +
                "Exercise:\n" +
                "Type: " + exerciseType + "\n" +
                "Duration: " + duration + " mins\n" +
                "Calories Burned: " + caloriesBurned + "\n\n" +
                "Diet:\n" +
                "Meals: " + mealDetails + "\n" +
                "Calories Consumed: " + caloriesConsumed + "\n" +
                "Water Intake: " + waterIntake + " glasses";

        // setting the text to the TextView so the user can see the summary
        tvSummary.setText(summaryText);
    }
}
