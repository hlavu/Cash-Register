package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class RecyclerHistoryList extends AppCompatActivity {
    ArrayList<History> histories;
    RecyclerView recyclerView;
    HistoryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_history_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerlist);

        Intent myIntent = getIntent();
        if(myIntent != null){
            histories = myIntent.getParcelableArrayListExtra("history");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryRecyclerAdapter(histories, this, new HistoryRecyclerAdapter.OnItemClickListner() {
            @Override
            public void onHistoryClicked(History history) {
                Intent myIntent = new Intent(RecyclerHistoryList.this, HistoryDetail.class);
                myIntent.putExtra("history", history);
                startActivity(myIntent);
            }

        });
        recyclerView.setAdapter(adapter);
    }


}