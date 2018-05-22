package com.christophersavage.lettergradecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;

public class GradeCalculatorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calculator);
        Button mCalculateButton = findViewById(R.id.calculate_button);

        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mQuizScoreField = findViewById(R.id.quizScore);
                Integer quizScore = Integer.parseInt(mQuizScoreField.getText().toString());

                EditText mAssignmentScoreField = findViewById(R.id.assignmentScore);
                Integer assignmentScore = Integer.parseInt(mAssignmentScoreField.getText().toString());

                EditText mMidtermScoreField = findViewById(R.id.midtermScore);
                Integer midtermScore = Integer.parseInt(mMidtermScoreField.getText().toString());

                EditText mFinalScoreField = findViewById(R.id.finalScore);
                Integer finalScore = Integer.parseInt(mFinalScoreField.getText().toString());


                HashMap<String, Integer> studentScores = new HashMap<>();

                studentScores.put("quiz", quizScore);
                studentScores.put("assignment", assignmentScore);
                studentScores.put("midterm", midtermScore);
                studentScores.put("final", finalScore);

                String calculatedGrade = calculateLetterGrade(studentScores);

                TextView mCalculatedLetterGradeField = findViewById(R.id.calculatedLetterGrade);
                mCalculatedLetterGradeField.setText(calculatedGrade);
            }
        });
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
