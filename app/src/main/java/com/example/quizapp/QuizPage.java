package com.example.quizapp;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizPage extends AppCompatActivity {
    TextView questionTv;
    RadioButton []choice=new RadioButton[4];
    RadioGroup radioGroupAns;
    Button previous,next,skip,submit;
    RadioButton radioButtonSelected;
    // Maintain an array to keep track of selected choices for each question
    List<String> selectedChoices = new ArrayList<>();

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

    String[][] choices = {
            {"A) Berlin", "B) Madrid", "C) Paris", "D) Rome"},
            {"A) Yen", "B) Won", "C) Baht", "D) Rupee"},
            {"A) J.R.R. Tolkien", "B) George R.R. Martin", "C) J.K. Rowling", "D) Stephen King"},
            {"A) Liver", "B) Brain", "C) Heart", "D) Skin"},
            {"A) 1914", "B) 1916", "C) 1918", "D) 1920"},
            {"A) Europe", "B) Asia", "C) Africa", "D) South America"},
            {"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
            {"A) Indian Ocean", "B) Southern Ocean", "C) Atlantic Ocean", "D) Arctic Ocean"},
            {"A) Sydney", "B) Melbourne", "C) Canberra", "D) Brisbane"},
            {"A) Peter Henlein", "B) Nicolas Hayek", "C) Leonardo da Vinci", "D) Timex"}
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

    String[][] sportsChoices = {
            {"A) Australia", "B) India", "C) England", "D) South Africa"},
            {"A) England", "B) France", "C) Spain", "D) USA"},
            {"A) 11", "B) 9", "C) 10", "D) 12"},
            {"A) Kolkata", "B) Ahmedabad", "C) Jaipur", "D) Hyderabad"},
            {"A) Tour de France", "B) Giro d'Italia", "C) Vuelta a España", "D) Paris-Roubaix"},
            {"A) 2011 National Games", "B) 2015 National Games", "C) 2007 National Games", "D) 2012 National Games"},
            {"A) Brazil", "B) Germany", "C) France", "D) Argentina"},
            {"A) Basketball", "B) Football (Soccer)", "C) Tennis", "D) Golf"},
            {"A) Mike Tyson", "B) Muhammad Ali", "C) Sugar Ray Leonard", "D) Floyd Mayweather Jr."},
            {"A) Hockey", "B) Lacrosse", "C) Soccer", "D) Baseball"}
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

    String[][] techChoices = {
            {"A. Central Processing Unit", "B. Computer Personal Unit", "C. Central Process Unit", "D. Central Processor Unit"},
            {"A. Java", "B. C++", "C. Python", "D. Ruby"},
            {"A. Store permanent data", "B. Execute instructions", "C. Store temporary data", "D. Control input and output devices"},
            {"A. Hyper Text Markup Language", "B. High Technology Markup Language", "C. Hyper Transfer Markup Language", "D. Human Text Markup Language"},
            {"A. Microsoft", "B. Apple", "C. Sun Microsystems", "D. Google"},
            {"A. Stanford University", "B. Harvard University", "C. University of Cambridge", "D. Massachusetts Institute of Technology"},
            {"A. Monitor network traffic", "B. Block internet access", "C. Filter incoming and outgoing data", "D. Increase internet speed"},
            {"A. Java", "B. C#", "C. Python", "D. JavaScript"},
            {"A. Graphical Processing Unit", "B. General Processing Unit", "C. Graphics Performance Unit", "D. General Purpose Unit"},
            {"A. Increase storage capacity", "B. Improve internet speed", "C. Enhance graphics performance", "D. Increase data transfer speed"}
    };


    String questionString,title,answerSelected="";
    // Keep track of the currently selected choice index
    int selectedChoiceIndex = -1,currentIndex = -1;
    String[] all_titles ={"General Knowledge","Technology","Sports"};


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        questionTv=findViewById(R.id.questionTextView);
        choice[0]=findViewById(R.id.choiceA);
        choice[1]=findViewById(R.id.choiceB);
        choice[2]=findViewById(R.id.choiceC);
        choice[3]=findViewById(R.id.choiceD);
        previous=findViewById(R.id.previousButton);
        next=findViewById(R.id.nextButton);
        skip=findViewById(R.id.skipButton);
        submit=findViewById(R.id.submitButton);
        radioGroupAns=findViewById(R.id.radioAnswer);

        Bundle bundle=getIntent().getExtras();



        if (bundle != null) {
            title=bundle.getString("title");
        }
        questionString = bundle.getString("question");

        if(title.equals(all_titles[0])){
          setInitialQuestionsChoices(questionString,questions,choices);
          setQuestionStringQuestions(questionString,questions,choices);
      }
        if(title.equals(all_titles[1])){
            setInitialQuestionsChoices(questionString,techQuestions,techChoices);
            setQuestionStringQuestions(questionString,techQuestions,techChoices);
        }
      if(title.equals(all_titles[2])){
          setInitialQuestionsChoices(questionString,sportsQuestions,sportsChoices);
          setQuestionStringQuestions(questionString,sportsQuestions,sportsChoices);
      }

        // This is the code to set the background of the selected choice
        if (currentIndex >= 0 && currentIndex < questions.length) {
            // Retrieve the previously selected choice for the current question from the list
            if (currentIndex < selectedChoices.size()) {
                String answerSelected = selectedChoices.get(currentIndex);

                // Set the background of the previously selected choice
                for (int k = 0; k < choice.length; k++) {
                    if (answerSelected.equals(choice[k].getText().toString())) {
                        setChoiceBackgrounds(k);
                        break; // No need to continue checking
                    }
                }
            }
        }



        for (int j = 0; j < choice.length; j++) {
            int finalJ = j;
            choice[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setChoiceBackgrounds(finalJ);

                }
            });
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("quiz", MODE_PRIVATE);
                int selected = radioGroupAns.getCheckedRadioButtonId();

                int check = checkSelected(questionString,questions);


                if (selected == -1 && check == -1) {
                    Toast.makeText(QuizPage.this, "Please Select an Option!!", Toast.LENGTH_SHORT).show();
                } else {
                    // Find the current question index
                    currentIndex = getCurrentQuestionIndex();

                    for (int k = 0; k < choice.length; k++) {
                        choice[k].setBackgroundResource(R.drawable.choice_background);
                    }

                    if (currentIndex >= 0 && currentIndex < questions.length - 1) {
                        try {
                            radioButtonSelected = findViewById(selected);
                            answerSelected = radioButtonSelected.getText().toString();
                        } catch (NullPointerException e) {
                            answerSelected = sharedPreferences.getString("" + currentIndex, "");
                        }

                        if (currentIndex >= 0 && currentIndex < selectedChoices.size()) {
                            selectedChoices.set(currentIndex, answerSelected); // Update the existing choice
                        } else if (currentIndex == selectedChoices.size()) {
                            selectedChoices.add(answerSelected); // Add a new choice
                        }

                        // Save the selected choice for the current question in shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("" + currentIndex, answerSelected);
                        Toast.makeText(QuizPage.this, "" + answerSelected, Toast.LENGTH_SHORT).show();
                        editor.apply();

                        radioGroupAns.clearCheck(); // Clear the radio group selection

                        currentIndex++; // Move to the next question


                        // Call the setupQuestionsAndChoices method to set up the new question and choices

                            setupQuestionsAndChoices(questions, choices,currentIndex);


                    } else if (currentIndex == questions.length - 1) {
                        // Update the selected choice for the last question
                        try {
                            radioButtonSelected = findViewById(selected);
                            answerSelected = radioButtonSelected.getText().toString();
                        } catch (NullPointerException e) {
                            answerSelected = sharedPreferences.getString("" + currentIndex, "");
                        }

                        if (currentIndex >= 0 && currentIndex < selectedChoices.size()) {
                            selectedChoices.set(currentIndex, answerSelected); // Update the existing choice
                        } else if (currentIndex == selectedChoices.size()) {
                            selectedChoices.add(answerSelected); // Add a new choice
                        }

                        // Save the selected choice for the last question in shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("" + currentIndex, answerSelected);
                        Toast.makeText(QuizPage.this, "" + answerSelected, Toast.LENGTH_SHORT).show();
                        editor.apply();

                        Toast.makeText(QuizPage.this, "No more questions!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < questions.length; i++) {
                    if (questionString != null && questionString.equals(questions[i])) {
                        currentIndex = i;
                        break;
                    }
                }

                if (currentIndex > 0 && currentIndex < questions.length) {
                    // Load the previously selected answer for the current question
                    SharedPreferences sharedPreferences = getSharedPreferences("quiz", MODE_PRIVATE);
                    String answerSelected = sharedPreferences.getString("" + currentIndex, "");

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putInt("currentIndex",currentIndex);
                    editor.putString(""+currentIndex,answerSelected);
                    editor.apply();

                    // Update the selected choice index based on the loaded answer
                    for (int k = 0; k < choice.length; k++) {
                        if (answerSelected.equals(choice[k].getText().toString())) {
                            selectedChoiceIndex = k; // Update the selected choice index
                            setChoiceBackgrounds(selectedChoiceIndex); // Set the background for the loaded choice
                            break;
                        }
                    }

                    currentIndex--; // Move to the previous question


                    // Call the setupQuestionsAndChoices method to set up the new question and choices

                        setupQuestionsAndChoices(questions, choices,currentIndex);


                } else {
                    Toast.makeText(QuizPage.this, "Click Next For More Questions!!", Toast.LENGTH_SHORT).show();
                }
            }
        });



        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = radioGroupAns.getCheckedRadioButtonId();

                // Find the current question index

                for (int i = 0; i < questions.length; i++) {
                    if (questionString != null && questionString.equals(questions[i])) {
                        currentIndex = i;
                        break;
                    }
                }

                if (selected != -1) {
                    // If a choice is selected, save the answer and clear the radio group selection
                    RadioButton radioButtonSelected = (RadioButton) findViewById(selected);
                    String answerSelected = "" + radioButtonSelected.getText();
                    SharedPreferences sharedPreferences=getSharedPreferences("quiz",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("" + currentIndex, answerSelected);
                    editor.apply();
                    radioGroupAns.clearCheck();
                }

                if (currentIndex >= 0 && currentIndex < questions.length-1) {
                    currentIndex++; // Move to the next question


                    // Call the setupQuestionsAndChoices method to set up the new question and choices
                        setupQuestionsAndChoices(questions, choices,currentIndex);


                } else {
                    Toast.makeText(QuizPage.this, "No more questions!!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(QuizPage.this, "Click Submit to Evaluate!!", Toast.LENGTH_SHORT).show();
                }
            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(QuizPage.this);
                builder.setCancelable(false);
                builder.setTitle("Alert");
                builder.setMessage("Are You Sure You Want To Submit?\n(Changes cannot be made once the quiz is submitted.)  ");
                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent in =new Intent(QuizPage.this,ResultPage.class);
                        in.putExtra("title",title);

                        startActivity(in);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();


            }
        });




    }

    private void setChoiceBackgrounds(int finalJ) {
        // Remove the background from all choices
        for (int k = 0; k < choice.length; k++) {
            choice[k].setBackgroundResource(R.drawable.choice_background);
        }

        // Set the background for the clicked choice
        choice[finalJ].setBackgroundResource(R.drawable.choice_selected_background);

        // Update the selected choice index
        selectedChoiceIndex = finalJ;
    }


    private int getSelectedChoiceIndex(int currentIndex) {
        SharedPreferences sharedPreferences = getSharedPreferences("quiz", MODE_PRIVATE);
        String answerSelected = sharedPreferences.getString("" + currentIndex, "");

        for (int k = 0; k < choice.length; k++) {
            if (answerSelected.equals(choice[k].getText().toString())) {
                return k;
            }
        }

        return -1; // Return -1 if no choice is selected
    }


    private void setupQuestionsAndChoices(String[] questions, String[][] choices, int currentIndex) {
        // Other code
        if (currentIndex >= 0 && currentIndex < questions.length) {
            // Update the UI with the current question and choices
            questionString = questions[currentIndex]; // Update the questionString
            questionTv.setText(questions[currentIndex]);
            choice[0].setText(choices[currentIndex][0]);
            choice[1].setText(choices[currentIndex][1]);
            choice[2].setText(choices[currentIndex][2]);
            choice[3].setText(choices[currentIndex][3]);

            // Retrieve the previously selected choice for the current question from the list
            if (currentIndex < selectedChoices.size()) {
                String answerSelected = selectedChoices.get(currentIndex);

                // Set the background of the previously selected choice
                for (int k = 0; k < choice.length; k++) {
                    if (answerSelected.equals(choice[k].getText().toString())) {
                        setChoiceBackgrounds(k);
                    }
                }
            }
        }
    }

      int checkSelected(String questionString,String[] questions){
          SharedPreferences sharedPreferences=getSharedPreferences("quiz",MODE_PRIVATE);
          SharedPreferences.Editor editor=sharedPreferences.edit();
          int currentIndex = -1;
          for (int i = 0; i < questions.length; i++) {
              if (questionString != null && questionString.equals(questions[i])) {
                  currentIndex = i;
                  break;
              }
          }
          String answerSelected = sharedPreferences.getString(""+currentIndex,"");

          if(!answerSelected.isEmpty()){
              return 1;
          }

          return -1;
      }

    // Add this method to get the current question index based on the current questionString
    private int getCurrentQuestionIndex() {
        for (int i = 0; i < questions.length; i++) {
            if (questionString != null && questionString.equals(questions[i])) {
                return i;
            }
        }
        return -1;
    }

    void setInitialQuestionsChoices(String questionString,String[] questions,String[][] choices){
        for (int i = 0; i < questions.length; i++) {
            if (questionString != null && questionString.equals(questions[i])) {
                questionTv.setText(questionString);
                choice[0].setText(choices[i][0]);
                choice[1].setText(choices[i][1]);
                choice[2].setText(choices[i][2]);
                choice[3].setText(choices[i][3]);
                selectedChoiceIndex = getSelectedChoiceIndex(i); // Update selectedChoiceIndex
                break;
            }
        }
    }
   void setQuestionStringQuestions(String questionString, String[] questions, String[][] choices){
        this.questionString=questionString;
        this.questions=questions;
        this.choices= choices;
   }





    }

