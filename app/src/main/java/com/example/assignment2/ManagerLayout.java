package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ManagerLayout extends AppCompatActivity {
    ArrayList<History> histories;
    ListView historyList;
    HistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_layout);

        if (savedInstanceState == null)
            histories = new ArrayList<History>();
        else
            histories = savedInstanceState.getParcelableArrayList("listofhistories");

        Intent myIntent = getIntent();
        if(myIntent != null){
            histories = myIntent.getParcelableArrayListExtra("history");
        }
        System.out.println("Above history is " + histories);
    }

    public void historyBtnClicked(View view) {
        Intent myIntent = new Intent(this, HistoryDetail.class);
        myIntent.putParcelableArrayListExtra("history", histories);
        System.out.println("My hisroty is "+ histories);
        startActivity(myIntent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listofhistories", histories);
    }
}