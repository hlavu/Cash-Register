package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryList extends AppCompatActivity {
    ListView historyList;
    HistoryAdapter adapter;
    ArrayList<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        historyList = (ListView) findViewById(R.id.historyList);

//        if (savedInstanceState == null){
//            histories = new ArrayList<History>();}
//        else {
//            histories = savedInstanceState.getParcelableArrayList("listofhistories");
//        }

        Intent myIntent = getIntent();
        histories = myIntent.getParcelableArrayListExtra("history");
        adapter = new HistoryAdapter(this, histories);
        historyList.setAdapter(adapter);

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent = new Intent(HistoryList.this, HistoryDetail.class);
                myIntent.putExtra("name", histories.get(i).name);
                myIntent.putExtra("price", histories.get(i).total);
                myIntent.putExtra("date", histories.get(i).date);
                startActivity(myIntent);
            }
        });

    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList("listofhistories", histories);
//    }
}