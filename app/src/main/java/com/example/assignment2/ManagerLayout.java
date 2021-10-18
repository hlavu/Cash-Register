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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_layout);

        Intent myIntent = getIntent();
        if(myIntent != null){
            histories = myIntent.getParcelableArrayListExtra("history");
        }
    }

    public void historyBtnClicked(View view) {
        Intent myIntent = new Intent(this, RecyclerHistoryList.class);
        myIntent.putParcelableArrayListExtra("history", histories);
        startActivity(myIntent);
    }
}