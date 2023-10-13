package com.example.dailyexpenseapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    ArrayList<Expense> arrExpense;
    Context context;
    DatabaseHelper databaseHelper;
    ExpenseAdapter(Context context, ArrayList<Expense> arrExpense, DatabaseHelper databaseHelper){
        this.context=context;
        this.arrExpense=arrExpense;
        this.databaseHelper=databaseHelper;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Expense currExpense = arrExpense.get(position);
         holder.date.setText(currExpense.getDate());
         holder.title.setText(currExpense.getTitle());
         holder.amount.setText(currExpense.getAmount());

         holder.row.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {
                 deleteExpense(position);
                 return false;
             }
         });

    }


    @Override
    public int getItemCount() {
        return arrExpense.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date, title, amount;
        LinearLayout row;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
            amount = itemView.findViewById(R.id.amount);
            row = itemView.findViewById(R.id.row);
        }
    }

    public void deleteExpense(int position){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure want to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.expenseDao().delete(new Expense(arrExpense.get(position).getId(),
                                arrExpense.get(position).getDate(), arrExpense.get(position).getTitle(),
                                arrExpense.get(position).amount));
                        Toast.makeText(context.getApplicationContext(), "Expense Deleted", Toast.LENGTH_SHORT).show();
                        ((ExpensesActivity)context).showExpenses();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();


    }
}
