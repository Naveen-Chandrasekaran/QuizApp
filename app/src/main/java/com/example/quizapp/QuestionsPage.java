package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsPage extends AppCompatActivity {
    ListView questionListView;
    String[] questions = {
            "1. What is the capital of France?",
            "2. What is the currency of Japan?",
            "3. Who is the author of the Harry Potter book series?",
            "4. What is the largest organ in the human body?",
            "5. In which year did World War I begin?",
            "6. Which continent is known as the ‘Dark’ continent?",
            "7. Which planet in our solar system is known as the 'Morning Star' or the 'Evening Star'?",
            "8. What is the world's second-largest ocean by surface area?",
            "9. What is the capital of Australia?",
            "10. Who invented the Watch?"
    };

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

    String[] sportsQuestions = {
            "1. Largest cricket ground in the world, by capacity is located in which country?",
            "2. In which country was the sport of tennis first played?",
            "3. How many players are there on a standard soccer (football) team?",
            "4. Where did M.S.Dhoni made his 183 not out?",
            "5. What is the most prestigious cycling race in the world?",
            "6. Which National Games of India were held using Green Protocols?",
            "7. Which country won the FIFA World Cup in 2018?",
            "8. In which sport would you perform a slam dunk?",
            "9. Who is often referred to as 'The Greatest' in the sport of boxing?",
            "10. What is the national sport of America?"
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

    String[] techQuestions = {
            "1. What does CPU stand for?",
            "2. Which programming language is often used for developing Android apps?",
            "3. What is the primary function of RAM in a computer?",
            "4. What does HTML stand for?",
            "5. Which company developed the Java programming language?",
            "6. Which University offered the first-ever academic programme in Computer Science?",
            "7. What is the main purpose of a firewall in network security?",
            "8. Which programming language is commonly used for web development?",
            "9. What does GPU stand for?",
            "10. What is the purpose of an SSD (Solid State Drive) in a computer?"
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



    String[] all_titles ={"General Knowledge","Technology","Sports"};
    TextView txt_title,txt_instructions;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);
        questionListView=findViewById(R.id.question_listView);
        txt_title=findViewById(R.id.quizTitle);
        txt_instructions=findViewById(R.id.instructions);



        Bundle b=getIntent().getExtras();

        String display=b.getString("display");
        String title=b.getString("title");


        if (display != null && display.equals("Questions")) {
            txt_title.setText(""+title+" Quiz");
            txt_instructions.setText(" (Click The Question To Answer) ");
            if (title != null && title.equals("General Knowledge")) {
                ListAdapter listAdapter = new ListAdapter(questions, QuestionsPage.this);
                questionListView.setAdapter(listAdapter);

                questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(QuestionsPage.this, QuizPage.class);
                        in.putExtra("question", questions[i]);
                        in.putExtra("title", all_titles[0]);
                        startActivity(in);
                    }
                });
            }
            if (title != null && title.equals(all_titles[1])) {
                ListAdapter listAdapter = new ListAdapter(techQuestions, QuestionsPage.this);
                questionListView.setAdapter(listAdapter);

                questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(QuestionsPage.this, QuizPage.class);
                        in.putExtra("question", techQuestions[i]);
                        in.putExtra("title", all_titles[1]);
                        startActivity(in);
                    }
                });
            }

            if (title != null && title.equals("Sports")) {
                ListAdapter listAdapter = new ListAdapter(sportsQuestions, QuestionsPage.this);
                questionListView.setAdapter(listAdapter);

                questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(QuestionsPage.this, QuizPage.class);
                        in.putExtra("question", sportsQuestions[i]);
                        in.putExtra("title", all_titles[2]);
                        startActivity(in);
                    }
                });
            }

        }
        if(display.equals("Answers")){
            txt_title.setText("Answers");
            txt_instructions.setText("(Check Your Answers)");

            if (title != null && title.equals("General Knowledge")) {
                ListAdapter listAdapter = new ListAdapter(answers, QuestionsPage.this);
                questionListView.setAdapter(listAdapter);
            }
            if (title != null && title.equals("Technology")) {
                ListAdapter listAdapter = new ListAdapter(techAnswers, QuestionsPage.this);
                questionListView.setAdapter(listAdapter);
            }
            if (title != null && title.equals("Sports")) {
                ListAdapter listAdapter = new ListAdapter(sportsAnswers, QuestionsPage.this);
                questionListView.setAdapter(listAdapter);
            }


        }


    }
}