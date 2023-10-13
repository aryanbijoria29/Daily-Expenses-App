package com.example.dailyexpenseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_items);

        recyclerView= findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper = DatabaseHelper.getInstance(this);
        showExpenses();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.expenses_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAllExpenses:
                databaseHelper.expenseDao().deleteAllExpenses();
                Toast.makeText(this,"All Expenses Deleted", Toast.LENGTH_SHORT).show();
                showExpenses();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void showExpenses(){
        ArrayList<Expense> expenses = (ArrayList<Expense>) databaseHelper.expenseDao().getAllExpense();
        ExpenseAdapter adapter = new ExpenseAdapter(this,expenses, databaseHelper);
        recyclerView.setAdapter(adapter);
    }


}