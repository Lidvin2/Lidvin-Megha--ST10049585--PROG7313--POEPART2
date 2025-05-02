package com.example.prog7313_poe2st10049585;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private EditText filterEditText;
    private List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_category_keyword);
        filterEditText = findViewById(R.id.et_filder);

        expenses = loadExpenses(); // load from local DB or mock list

        adapter = new ExpenseAdapter(expenses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private List<Expense> loadExpenses() {
        // Replace this with actual DB call or load mock data
        List<Expense> list = new ArrayList<>();
        list.add(new Expense("Groceries", 50, "2025-04-01"));
        list.add(new Expense("Transport", 20, "2025-04-02"));
        list.add(new Expense("Clothes", 2000, "2025-06-06"));
        list.add(new Expense("Apartment", 18000, "2025-09-01"));
        return list;
    }
}