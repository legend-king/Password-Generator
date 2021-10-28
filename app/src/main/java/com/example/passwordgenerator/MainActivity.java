package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView password, charac,textView;
    CheckBox letter,number,specialCharac;
    Button genPass,addPass;
    SeekBar noCharac;
    DBHelper DB;

    int prog = 8;
    int min = 0;
    int max = 52;
    String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String numbers = "0123456789";
    String specialCharacs = "!@#$%^&*";
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        password = findViewById(R.id.textView2);
        charac = findViewById(R.id.textView3);
        genPass = findViewById(R.id.button);
        noCharac = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        letter = findViewById(R.id.checkBox);
        number = findViewById(R.id.checkBox2);
        specialCharac = findViewById(R.id.checkBox3);
        addPass = findViewById(R.id.button2);
        DB = new DBHelper(this);

        letter.setChecked(true);
        noCharac.setMax(16);
        noCharac.setProgress(4);


        genPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!letter.isChecked() && !number.isChecked() && !specialCharac.isChecked()) {
                    Toast.makeText(context, "At least one check box should be selected", Toast.LENGTH_LONG).show();
                }
                else {
                    StringBuilder j = new StringBuilder();
                    if (letter.isChecked() && !number.isChecked() && !specialCharac.isChecked()) {
                        max = letters.length()-1;
                        for (int i = 0; i < prog; i++) {
                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            j.append(letters.charAt(random_int));
                        }
                    }
                    else if (number.isChecked() && !letter.isChecked() && !specialCharac.isChecked()) {
                        max = numbers.length()-1;
                        for (int i = 0; i < prog; i++) {
                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            j.append(numbers.charAt(random_int));
                        }

                    }
                    else if (specialCharac.isChecked() && !letter.isChecked() && !number.isChecked()) {
                        Toast.makeText(context, "Special Characters alone should not be selected", Toast.LENGTH_SHORT).show();
//                        max = specialCharac.length()-1;
//                        for (int i = 0; i < prog; i++) {
//                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
//                            j.append(specialCharacs.charAt(random_int));
//                        }
                    }
                    else if (letter.isChecked() && number.isChecked() && !specialCharac.isChecked()) {
                        String x="";
                        x+=letters;
                        x+=numbers;
                        max = x.length()-1;
                        for (int i = 0; i < prog; i++) {
                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            j.append(x.charAt(random_int));
                        }

                    }
                    else if (letter.isChecked() && specialCharac.isChecked() && !number.isChecked()) {
                        String x="";
                        x+=letters;
                        x+=specialCharacs;
                        max = x.length()-1;
                        for (int i = 0; i < prog; i++) {
                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            j.append(x.charAt(random_int));
                        }

                    }
                    else if (!letter.isChecked() && number.isChecked() && specialCharac.isChecked()) {
                        String x="";
                        x+=specialCharacs;
                        x+=numbers;
                        max = x.length()-1;
                        for (int i = 0; i < prog; i++) {
                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            j.append(x.charAt(random_int));
                        }

                    }
                    else if (letter.isChecked() && number.isChecked() && specialCharac.isChecked()){
                        String x="";
                        x+=letters;
                        x+=numbers;
                        x+=specialCharacs;
                        max = x.length()-1;
                        for (int i = 0; i < prog; i++) {
                            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
                            j.append(x.charAt(random_int));
                        }
                    }
                    pass = j.toString();
                    textView.setText(R.string.password_generated);
                    password.setText(pass);
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("simple text", pass);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(context, "Password copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("message_key",pass);
                startActivity(intent);
            }
        });

        noCharac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress+4;
                charac.setText("" + prog + " Characters");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}