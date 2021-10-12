package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryDetail extends AppCompatActivity {

    ListView historyList;
    HistoryAdapter adapter;
    ArrayList<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        historyList = (ListView)findViewById(R.id.historyList);

//        if (savedInstanceState == null){
//            histories = new ArrayList<History>();}
//        else {
//            histories = savedInstanceState.getParcelableArrayList("listofhistories");
//        }

        Intent myIntent = getIntent();
        histories = myIntent.getParcelableArrayListExtra("history");
        adapter = new HistoryAdapter(this, histories);
        historyList.setAdapter(adapter);

    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList("listofhistories", histories);
//    }


}