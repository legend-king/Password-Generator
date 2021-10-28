package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateDeleteData extends AppCompatActivity {

    TextView nameOfPass;
    EditText passText;
    Button updateBtn, deleteBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_data);

        Intent intent = getIntent();
        String name = intent.getStringExtra("message_key3");
        String pass = intent.getStringExtra("message_key4");

        DB = new DBHelper(this);
        nameOfPass = findViewById(R.id.org1_name);
        passText = findViewById(R.id.org1_pass);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        nameOfPass.setText(name);
        passText.setText(pass);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String changePass = passText.getText().toString();
                boolean update = DB.updateData(name, changePass);
                if (!update){
                    Toast.makeText(getApplicationContext(), "Not able to update the details", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean delete = DB.deleteData(name);
                if (!delete){
                    Toast.makeText(getApplicationContext(), "Not able to delete", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

    }
}