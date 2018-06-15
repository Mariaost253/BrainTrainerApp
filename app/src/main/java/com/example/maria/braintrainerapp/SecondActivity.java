package com.example.maria.braintrainerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class SecondActivity extends AppCompatActivity {
    private Button button2=null;
    TextView textTimer;
    TextView textMath;
    TextView textCorrect;
    TextView textCounter;
    Button button3;
    ArrayList<Integer> answers;
    int LocationOfCorrectAns;
    Button ans0;
    Button ans1;
    Button ans2;
    Button ans3;
    int CountAll;
    int CountCorrect;
    CountDownTimer counterTimer;
    boolean counterActive;
    GridLayout gridLayout;
    RelativeLayout Rlayout;
    Button HideLayout;
    public int SendBound;
    Button easyB;
    Button mediumB;
    Button hardB;
    TextView textView5;


    public void updateTimer(int secondsLeft) {//updates timer every second

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        textTimer.setText(secondString+"s");
    }


    public void e(View view){
        SendBound=20;
            mediumB.setBackgroundColor(WHITE);
            easyB.setBackgroundColor(RED);
            hardB.setBackgroundColor(WHITE);

    }
    public void m(View view){
        SendBound=50;
        mediumB.setBackgroundColor(RED);
        easyB.setBackgroundColor(WHITE);
        hardB.setBackgroundColor(WHITE);


    }
    public void h(View view){
        SendBound=120;
            mediumB.setBackgroundColor(WHITE);
            easyB.setBackgroundColor(WHITE);
            hardB.setBackgroundColor(RED);


    }

    public void LetStart(View view){
       // Rlayout.setVisibility(View.INVISIBLE);
        PlayAgain(findViewById(R.id.button3));
    }



    public void PlayAgain(View view){
        CountAll=0;
        CountCorrect=0;
        textTimer.setText("30s");
        textCounter.setText("0/0");
        counterActive=true;
        Rlayout.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        textCorrect.setText("");
        button3.setVisibility(View.INVISIBLE);
        GenRnads();
        counterTimer= new CountDownTimer(31000, 1000) {//timer
            public void onTick(long x) {
                updateTimer((int) x / 1000);
            }

            public void onFinish() {
                button3.setVisibility(View.VISIBLE);
                textTimer.setText("0s");
                counterActive=false;
                textMath.setText("");
                textCorrect.setText("Correct Answers:"+Integer.toString(CountCorrect)+" out of "+Integer.toString(CountAll) );

            }
        }.start();
    }


    public void GenRnads(){//generate random numbers
        if(counterActive) {
            ArrayList<Integer> answers = new ArrayList<Integer>();
            Random rand = new Random();
            int a = rand.nextInt(SendBound);
            int b = rand.nextInt(SendBound);
            int answer = a + b;

            textMath.setText(Integer.toString(a) + "+" + Integer.toString(b));
            LocationOfCorrectAns = rand.nextInt(4);

            int IncorrectAns;
            for (int i = 0; i < 4; i++) {
                if (i == LocationOfCorrectAns) {
                    answers.add(a + b);
                } else { //insure the incorrect answer will not be the same as the correct one
                    IncorrectAns = rand.nextInt(SendBound+SendBound);
                    while (IncorrectAns == a + b) {
                        IncorrectAns = rand.nextInt(SendBound+SendBound);
                    }
                    answers.add(IncorrectAns);
                }
            }
            ans0.setText(Integer.toString(answers.get(0)));
            ans1.setText(Integer.toString(answers.get(1)));
            ans2.setText(Integer.toString(answers.get(2)));
            ans3.setText(Integer.toString(answers.get(3)));
        }
    }

    public void chooseAnswer(View view) {
        if (counterActive) {
            if (view.getTag().toString().equals(Integer.toString(LocationOfCorrectAns))) {
                textCorrect.setText("Correct!");
                textCorrect.setVisibility(View.VISIBLE);
                CountAll++;
                CountCorrect++;
                String str = Integer.toString(CountCorrect) + "/" + Integer.toString(CountAll);
                textCounter.setText(str);
                GenRnads();
            } else {
                textCorrect.setText("Incorrect!");
                textCorrect.setVisibility(View.VISIBLE);
                CountAll++;
                String str = Integer.toString(CountCorrect) + "/" + Integer.toString(CountAll);
                textCounter.setText(str);
                GenRnads();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        button2=(Button) findViewById(R.id.button2);
        textTimer=(TextView) findViewById(R.id.textTimer);
        textMath=(TextView) findViewById(R.id.textMath);
        textCorrect=(TextView) findViewById(R.id.textCorrect);
        textCounter=(TextView) findViewById(R.id.textCounter);
        button3=(Button) findViewById(R.id.button3);
        textCorrect.setVisibility(View.INVISIBLE);
        ans0=(Button) findViewById(R.id.ans0);
        ans1=(Button) findViewById(R.id.ans1);
        ans2=(Button) findViewById(R.id.ans2);
        ans3=(Button) findViewById(R.id.ans3);


        Rlayout=(RelativeLayout) findViewById(R.id.Rlayout);
        textView5=(TextView)findViewById(R.id.textView5);
        textView5.setText("Solve as many operations as you can before the time runs out");
        HideLayout = (Button) findViewById(R.id.HideLayout);
        button3.setVisibility(View.INVISIBLE);
        
        CountAll=0;
        CountCorrect=0;
        textMath.setText("");

        easyB=(Button) findViewById(R.id.easyB);
        mediumB=(Button) findViewById(R.id.mediumB);
        hardB=(Button) findViewById(R.id.hardB);



        button2.setOnClickListener(new View.OnClickListener(){//back to main
            @Override
            public void onClick(View view){
                Intent i=new Intent(view.getContext(),MainActivity.class);
                startActivity(i);
            }
        });




    }




}
