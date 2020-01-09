package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondGameActivity5 extends AppCompatActivity implements View.OnClickListener {

    private int count = 1;
    private int[] num = new int[14];
    private int[] even_num = new int[7];
    private int[] odd_num = new int[7];
    private Button[] button = new Button[14];

    private int timer_count = 41;
    private TextView countTxt;
    private CountDownTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_game5);

        Intent intent = getIntent();
        String timercounter1 = intent.getStringExtra("timer_counter1");

        button[0] = (Button) findViewById(R.id.btn1);
        button[1] = (Button) findViewById(R.id.btn2);
        button[2] = (Button) findViewById(R.id.btn3);
        button[3] = (Button) findViewById(R.id.btn4);
        button[4] = (Button) findViewById(R.id.btn5);
        button[5] = (Button) findViewById(R.id.btn6);
        button[6] = (Button) findViewById(R.id.btn7);
        button[7] = (Button) findViewById(R.id.btn8);
        button[8] = (Button) findViewById(R.id.btn9);
        button[9] = (Button) findViewById(R.id.btn10);
        button[10] = (Button) findViewById(R.id.btn11);
        button[11] = (Button) findViewById(R.id.btn12);
        button[12] = (Button) findViewById(R.id.btn13);
        button[13] = (Button) findViewById(R.id.btn14);

        Button btnReturnMenu = findViewById(R.id.second_return_menu_button);


        // 랜덤값 1~14
        Random random = new Random();
        for (int x = 0; x < 14; x++) {
            num[x] = random.nextInt(14) + 1;
            for (int y = 0; y < x; y++) {
                if (num[x] == num[y]) {
                    x--;
                }
            }
        }

//        for(int x = 0; x<14;x++){
//            Log.e("num[x]", String.valueOf(num[x]));
//        }

        // 짝수 랜덤값 0, 2, 4, 6, 8, 10, 12
        for (int x = 0; x < 7; x++) {
            even_num[x] = 2 * random.nextInt(7);
            for (int y = 0; y < x; y++) {
                if (even_num[x] == even_num[y]) {
                    x--;
                }
            }
        }

//        for(int x = 0; x<7;x++){
//            Log.e("even_num[x]", String.valueOf(even_num[x]));
//        }
        // 홀수 랜덤값 1, 3, 5, 7, 9, 11, 13
        for (int x = 0; x < 7; x++) {
            odd_num[x] = 2 * random.nextInt(7) + 1;
            for (int y = 0; y < x; y++) {
                if (odd_num[x] == odd_num[y]) {
                    x--;
                }
            }

        }

//        for(int x = 0; x<7;x++){
//            Log.e("odd_num[x]", String.valueOf(odd_num[x]));
//        }

        int even = 0;
        int odd = 0;

        for (int x = 0; x < 14; x++) {
            if (num[x] % 2 == 1) {
                button[even_num[even]].setTag(num[x]);
                switch (num[x]) {
                    case 1:
                        button[even_num[even]].setText("1");
                        break;
                    case 3:
                        button[even_num[even]].setText("2");
                        break;
                    case 5:
                        button[even_num[even]].setText("3");
                        break;
                    case 7:
                        button[even_num[even]].setText("4");
                        break;
                    case 9:
                        button[even_num[even]].setText("5");
                        break;
                    case 11:
                        button[even_num[even]].setText("6");
                        break;
                    case 13:
                        button[even_num[even]].setText("7");
                        break;
                }
                even++;
            } else {
                button[odd_num[odd]].setTag(num[x]);
                switch (num[x]) {
                    case 2:
                        button[odd_num[odd]].setText("월");
                        break;
                    case 4:
                        button[odd_num[odd]].setText("화");
                        break;
                    case 6:
                        button[odd_num[odd]].setText("수");
                        break;
                    case 8:
                        button[odd_num[odd]].setText("목");
                        break;
                    case 10:
                        button[odd_num[odd]].setText("금");
                        break;
                    case 12:
                        button[odd_num[odd]].setText("토");
                        break;
                    case 14:
                        button[odd_num[odd]].setText("일");
                        break;
                }
                odd++;

            }
        }

        timer = new CountDownTimer(41 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer_count--;
                countTxt = (TextView) findViewById(R.id.count_txt);
                countTxt.setText("제한시간: " + timer_count + "초");

                if (timer_count == 0) {
                    SelectGame.secondGameResult3 = timer_count;
                    timer.cancel();
                    timer.onFinish();

                    Toast.makeText(getApplicationContext(), "이번 게임의 점수는 " + String.valueOf(timer_count) + "점 입니다.", Toast.LENGTH_SHORT).show();

                      RelativeLayout scoreLayout = findViewById(R.id.second_score_layout);
                    scoreLayout.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFinish() {
                countTxt = (TextView) findViewById(R.id.count_txt);
                countTxt.setText("제한시간: " + timer_count + "초");
            }
        };


        btnReturnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Intent intent = new Intent(SecondGameActivity5.this, MainActivity.class);
                //intent.putExtra("timer_count1", String.valueOf(timer_count));
                //startActivity(intent);
                finish();


            }
        });


    }

    @Override
    public void onClick(View view) {
        int CompareValue = Integer.parseInt(view.getTag().toString());
        final Button button = (Button) view;
        Handler delayHandler = new Handler();

        if (CompareValue == 1) {
            timer.start();
        }
        if (CompareValue == count) {
            if (CompareValue % 2 == 0) {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.sgreen));
                button.setEnabled(false);
            } else {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cgreen));
                button.setEnabled(false);
            }
            count++;
            if (count == 15) {
                SelectGame.secondGameResult3 = timer_count;
                timer.cancel();
                timer.onFinish();

                Toast.makeText(getApplicationContext(), "이번 게임의 점수는 " + String.valueOf(timer_count) + "점 입니다.", Toast.LENGTH_SHORT).show();


                TextView second1ScoreResult = findViewById(R.id.second1_result_text_view);
                TextView second2ScoreResult = findViewById(R.id.second2_result_text_view);
                TextView second3ScoreResult = findViewById(R.id.second3_result_text_view);

                second1ScoreResult.setText("1-25  순서 : " + SelectGame.secondGameResult1);
                second2ScoreResult.setText("숫자 - 계절 : " + SelectGame.secondGameResult2);
                second3ScoreResult.setText("숫자 - 요일 : " + SelectGame.secondGameResult3);


                RelativeLayout scoreLayout = findViewById(R.id.second_score_layout);
                scoreLayout.setVisibility(View.VISIBLE);


            }
        } else {
            if (CompareValue % 2 == 1) {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cred));
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cblack));
                    }
                }, 2000);
            } else {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.sred));
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.sblack));
                    }
                }, 2000);
//            Toast.makeText(getApplicationContext(), "잘못된 순서입니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

