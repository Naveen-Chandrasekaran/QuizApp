package com.example.quizapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button gk,tech,sports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        gk=findViewById(R.id.gk);
        tech=findViewById(R.id.Technology);
        sports=findViewById(R.id.Sports);

        gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, QuestionsPage.class);
                intent.putExtra("display","Questions");
                intent.putExtra("title","General Knowledge");
                startActivity(intent);
            }
        });
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, QuestionsPage.class);
                intent.putExtra("display","Questions");
                intent.putExtra("title","Technology");
                startActivity(intent);

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, QuestionsPage.class);
                intent.putExtra("display","Questions");
                intent.putExtra("title","Sports");
                startActivity(intent);

            }
        });
    }
}