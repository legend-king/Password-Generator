package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.passwordgenerator.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> arrayAdapter;
    private ArrayList<Password> passwordArrayList;
    Button button;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        button = findViewById(R.id.button3);
//
//        Intent intent = getIntent();
//        String pass = intent.getStringExtra("message_key");
//        DB = new DBHelper(this);
//
//        passwordArrayList = new ArrayList<>();
//
//        Cursor res = DB.getData();
//        while (res.moveToNext()){
//            Password pd = new Password(res.getString(0), res.getString(1));
//            passwordArrayList.add(pd);
//        }
//
//        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity2.this, passwordArrayList);
//        recyclerView.setAdapter(recyclerViewAdapter);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(MainActivity2.this, AddPassData.class);
//                intent1.putExtra("message_key1",pass);
//                startActivity(intent1);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.button3);

        Intent intent = getIntent();
        String pass = intent.getStringExtra("message_key");
        DB = new DBHelper(this);

        passwordArrayList = new ArrayList<>();

        Cursor res = DB.getData();
        while (res.moveToNext()){
            Password pd = new Password(res.getString(0), res.getString(1));
            passwordArrayList.add(pd);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity2.this, passwordArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this, AddPassData.class);
                intent1.putExtra("message_key1",pass);
                startActivity(intent1);
            }
        });
    }
}