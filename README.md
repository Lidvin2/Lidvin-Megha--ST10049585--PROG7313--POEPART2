# Lidvin-Megha--ST10049585--PROG7313--POEPART2

To implement viewing and filtering in my Android Studio app (for something like expenses or budgets), I typically need to:

✅ 1. Design the UI
Use an Activity or Fragment with:

A RecyclerView to display the list of items.

A Spinner, EditText, DatePicker, or SearchView for filtering options.

✅ 2. Create a Data Model
public class Expense {
    String category;
    double amount;
    String date;

    public Expense(String category, double amount, String date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
}


✅ 3. Set Up RecyclerView and Adapter
Adapter (ExpenseAdapter.java)
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private List<Expense> expenseList;
    private List<Expense> fullList;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenseList = expenses;
        this.fullList = new ArrayList<>(expenses); // for filtering
    }

    public void filter(String text) {
        expenseList.clear();
        if (text.isEmpty()) {
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

    // ViewHolder and other methods...

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category, amount, date;
        public ViewHolder(View view) {
            super(view);
            category = view.findViewById(R.id.categoryTextView);
            amount = view.findViewById(R.id.amountTextView);
            date = view.findViewById(R.id.dateTextView);
        }
    }
}


✅ 4. Connect Logic in Activity
Example (MainActivity.java)
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private EditText filterEditText;
    private List<Expense> expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView = findViewById(R.id.recyclerView);
        filterEditText = findViewById(R.id.filterEditText);

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
        return list;
    }
}


✅ 5. (Optional) Filter by Date Range
Add DatePickerDialogs to get startDate and endDate.

Use similar filter() logic, but check date range instead of text.

How to fix RecyclerView?

My ExpenseAdapter class sets up the structure for a working RecyclerView.Adapter, but the implementation is incomplete. Specifically, I need to:

✅ Fix or implement the following:
Inflate the item layout in onCreateViewHolder()

Bind data to each item in onBindViewHolder()

Return the correct list size in getItemCount()

✅ Updated ExpenseAdapter with missing implementations:
Assuming you have a layout file named activity_main.xml with TextViews with IDs tv_Category, tv_Amount, and tv_Date,
there is fixed and complete version:


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


✅ Also ensure:
My Expense class has public fields or getters for category, amount, and date.

My activity_main.xml layout file exists and uses the same IDs (tv_Category, tv_Amount, tv_Date).
