package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddPassData extends AppCompatActivity {

    TextView namePass, orgPass;
    Button addBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pass_data);

        Intent intent = getIntent();
        String pass = intent.getStringExtra("message_key1");

        namePass = findViewById(R.id.pass_name);
        orgPass = findViewById(R.id.org_pass);
        addBtn = findViewById(R.id.add_btn);
        DB = new DBHelper(this);

        orgPass.setText(pass);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insertName = namePass.getText().toString();
                String insertPass = orgPass.getText().toString();
                boolean insert = DB.insertData(insertName, insertPass);
                if (!insert){
                    Toast.makeText(getApplicationContext(), "Not able to add the password", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }
}