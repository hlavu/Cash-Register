package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryDetail extends AppCompatActivity {

    TextView name;
    TextView price;
    TextView date;
    History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        date = (TextView) findViewById(R.id.date);

        Intent myIntent = getIntent();
        history = myIntent.getParcelableExtra("history");
        name.setText(String.format("Product: %s", history.name));
        price.setText(String.format("Price: %s", history.total));
        date.setText(String.format("Purchase Date: %s", history.date));

    }
}