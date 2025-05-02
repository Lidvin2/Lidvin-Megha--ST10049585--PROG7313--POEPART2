package com.example.prog7313_poe2st10049585;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{
    private List<Expense> expenseList;
    private List<Expense> fullList;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenseList = expenses;
        this.fullList = new ArrayList<>(expenses);
    }

    public void filter(String text) {
        expenseList.clear();
        if (text.isEmpty()){
            expenseList.addAll(fullList);
        } else {
            text = text.toLowerCase();
            for (Expense e : fullList) {
                if (e.category.toLowerCase().contains(text)) {
                    expenseList.add(e);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category, amount, date;
        public ViewHolder(View view) {
            super(view);
            category = view.findViewById(R.id.tv_Category);
            amount = view.findViewById(R.id.tv_Amount);
            date = view.findViewById(R.id.tv_Date);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.category.setText(expense.category);
        holder.amount.setText(String.valueOf(expense.amount));
        holder.date.setText(expense.date);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }


}
