package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here
    int question_index;
    int score;

    // TODO: Declare member variables here:
    TextView questiontxt;
    Button truebtn;
    Button falsebtn;
    TextView scoretxt;
    ProgressBar progress;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("ScoreKey");
            question_index = savedInstanceState.getInt("IndexKey");
        }

        questiontxt = findViewById(R.id.question_text_view);
        truebtn = findViewById(R.id.true_button);
        falsebtn = findViewById(R.id.false_button);
        scoretxt = findViewById(R.id.score);
        progress = findViewById(R.id.progress_bar);

        questiontxt.setText(mQuestionBank[question_index].getQuestionID());
        scoretxt.setText("Score: "+score+"/"+mQuestionBank.length);

        truebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                nextQuestion();
            }
        });

        falsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                nextQuestion();
            }
        });
    }

    public void checkAnswer(boolean answer) {
        if(answer == mQuestionBank[question_index].isAnswer()) {
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
            score = score + 1;
        }
        else {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }

    public void nextQuestion() {
        int progress_val = (int) Math.ceil(100.0/mQuestionBank.length);
        question_index = (question_index + 1) % mQuestionBank.length;
        if(question_index == 0) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Game Over");
            dialog.setMessage("Your Score is "+score);
            dialog.setCancelable(false);
            dialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }
        questiontxt.setText(mQuestionBank[question_index].getQuestionID());
        scoretxt.setText("Score: " + score + "/" + mQuestionBank.length);
        progress.incrementProgressBy(progress_val);
    }

    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey",score);
        outState.putInt("IndexKey",question_index);
    }
}
