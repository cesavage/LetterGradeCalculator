package com.christophersavage.lettergradecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class GradeCalculatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calculator);

        Button mCalculateLetterGradeFromScores_button = findViewById(R.id.calculate_button);
        mCalculateLetterGradeFromScores_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView mCalculatedLetterGrade_textView = findViewById(R.id.calculatedLetterGrade);
                HashMap studentScores;

                try {
                    studentScores = recordScoresFromInputFields();
                }
                catch(RuntimeException e){
                    mCalculatedLetterGrade_textView.setText("");
                    return;
                }

                String calculatedLetterGrade = calculateLetterGrade(studentScores);
                mCalculatedLetterGrade_textView.setText(calculatedLetterGrade);
            }
        });
    }

    protected HashMap recordScoresFromInputFields(){
        HashMap studentScores = new HashMap<>();
        try{
            studentScores = recordAllScores();
        }
        catch(NumberFormatException e){
            Toast.makeText(GradeCalculatorActivity.this, "Please enter a numeric value into each score.", Toast.LENGTH_LONG).show();
            throw new RuntimeException();
        }
        return studentScores;
    }

    protected HashMap recordAllScores(){
        HashMap<String, Integer> studentScores = new HashMap<>();

            EditText mQuizScoreField = findViewById(R.id.quizScore);
            Integer quizScore = Integer.parseInt(mQuizScoreField.getText().toString());
            studentScores.put("quiz", quizScore);

            EditText mAssignmentScoreField = findViewById(R.id.assignmentScore);
            Integer assignmentScore = Integer.parseInt(mAssignmentScoreField.getText().toString());
            studentScores.put("assignment", assignmentScore);

            EditText mMidtermScoreField = findViewById(R.id.midtermScore);
            Integer midtermScore = Integer.parseInt(mMidtermScoreField.getText().toString());
            studentScores.put("midterm", midtermScore);

            EditText mFinalScoreField = findViewById(R.id.finalScore);
            Integer finalScore = Integer.parseInt(mFinalScoreField.getText().toString());
            studentScores.put("final", finalScore);

            return studentScores;
        }

    protected String calculateLetterGrade(HashMap studentScores){
        Integer quizComponentScore = Integer.parseInt(studentScores.get("quiz").toString());
        Integer assignmentComponentScore = Integer.parseInt(studentScores.get("assignment").toString());
        Integer midtermComponentScore = Integer.parseInt(studentScores.get("midterm").toString());
        Integer finalComponentScore = Integer.parseInt(studentScores.get("final").toString());

        Double studentScore = (quizComponentScore * .15) + (assignmentComponentScore * .4) + (midtermComponentScore * .2) + (finalComponentScore * .25);
        String calculatedLetterGrade = "E";

        if (studentScore >= 90){
            calculatedLetterGrade = "A";
        }
        else if (studentScore  >= 80){
            calculatedLetterGrade = "B";
        }
        else if (studentScore >= 70){
            calculatedLetterGrade = "C";
        }
        else if (studentScore >= 60){
            calculatedLetterGrade = "D";
        }
        else {
            calculatedLetterGrade = "F";
        }

        return calculatedLetterGrade;
    }

}
