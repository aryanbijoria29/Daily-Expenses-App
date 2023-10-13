package com.example.dailyexpenseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText dateText, titleText, amountText;
    Button addButton, showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.dateText);
        titleText = findViewById(R.id.titleText);
        amountText = findViewById(R.id.amountText);
        addButton = findViewById(R.id.addButton);
        showButton = findViewById(R.id.showButton);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateText.getText().toString();
                String title = titleText.getText().toString();
                String amount = amountText.getText().toString();

                if (date.isEmpty() || title.isEmpty() || amount.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.expenseDao().insert(new Expense(date,title, amount));
                    Toast.makeText(MainActivity.this,"Expense Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ExpensesActivity.class);
                    startActivity(intent);
                    amountText.getText().clear();
                    titleText.getText().clear();
                    dateText.getText().clear();

                }


            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpensesActivity.class);
                startActivity(intent);
            }
        });


    }
}