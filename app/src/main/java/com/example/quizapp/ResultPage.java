package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultPage extends AppCompatActivity {
    TextView correct,wrong,skipped,score,review;
    Button back,answersBtn;
    String[] selectedAnswer=new String[10];
    String title;
    int correctCount=0,skippedCount=0,wrongCount=0;
    float scoreCount;
    String[] all_titles ={"General Knowledge","Technology","Sports"};

    String[] answers = {
            "C) Paris",
            "A) Yen",
            "C) J.K. Rowling",
            "D) Skin",
            "A) 1914",
            "C) Africa",
            "A) Venus",
            "C) Atlantic Ocean",
            "C) Canberra",
            "A) Peter Henlein"
    };
    String[] sportsAnswers = {
            "B) India",
            "A) England",
            "A) 11",
            "C) Jaipur",
            "A) Tour de France",
            "B) 2015 National Games",
            "C) France",
            "A) Basketball",
            "B) Muhammad Ali",
            "D) Baseball"
    };

    String[] techAnswers = {
            "A. Central Processing Unit",
            "A. Java",
            "C. Store temporary data",
            "A. Hyper Text Markup Language",
            "C. Sun Microsystems",
            "C. University of Cambridge",
            "C. Filter incoming and outgoing data",
            "D. JavaScript",
            "A. Graphical Processing Unit",
            "D. Increase data transfer speed"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        correct=findViewById(R.id.correct);
        wrong=findViewById(R.id.wrong);
        skipped=findViewById(R.id.skippedAns);
        score=findViewById(R.id.score);
        review=findViewById(R.id.review);
        back=findViewById(R.id.backBtn);
        answersBtn=findViewById(R.id.displayAnsBtn);

        Bundle bundle=getIntent().getExtras();


        if (bundle != null) {
            title = bundle.getString("title");
        }
        if(title.equals(all_titles[0])){
            setAnswers(answers);
        }
        if(title.equals(all_titles[1])){
            setAnswers(techAnswers);
        }
        if (title.equals(all_titles[2])) {
            setAnswers(sportsAnswers);

        }

        SharedPreferences sharedPreferences=getSharedPreferences("quiz",MODE_PRIVATE);
        for(int i=0;i<10;i++){
            selectedAnswer[i]=sharedPreferences.getString(""+i,"");
        }


            for(int j=0;j<10;j++){
                if(!selectedAnswer[j].isEmpty()){
                    if(selectedAnswer[j].equals(""+answers[j])){
                        correctCount++;
                    }
                    else if(!(selectedAnswer[j].equals(""+answers[j]))){
                        wrongCount++;

                    }
                }
                else
                {
                    skippedCount++;

                }
            }

        scoreCount=((float)correctCount/10)*100;
        String scoreValue = String.format("%.1f", scoreCount);

        correct.setText(""+correctCount);
        wrong.setText(""+wrongCount);
        skipped.setText(""+skippedCount);
        score.setText(""+scoreValue+"%");

        if(correctCount>7){
            review.setText("Congratulations!\n You passed the quiz with a high score.");
        } else if (correctCount>5) {
            review.setText("You scored more than 50%. You passed the quiz");
        }
        else{
            review.setText("You scored less than 50% . Better Luck Next Time  ");
        }

        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(ResultPage.this, MainActivity.class);
                startActivity(in);

            }
        });

        answersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultPage.this, QuestionsPage.class);
                intent.putExtra("display","Answers");
                intent.putExtra("title",title);
                startActivity(intent);

            }
        });

    }

    void setAnswers(String[] answers){
        this.answers=answers;
    }

}