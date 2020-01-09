package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SecondGameActivity4 extends AppCompatActivity implements View.OnClickListener {

    private int count = 1;
    private int[] num = new int[8];
    private int[] even_num = new int[4];
    private int[] odd_num = new int[4];
    Button[] button = new Button[8];

    private int timer_count = 31;
    private TextView countTxt;
    private CountDownTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_game4);

        button[0] = (Button) findViewById(R.id.btn1);   // 숫자   num홀수
        button[1] = (Button) findViewById(R.id.btn2);   // 계절   num짝수
        button[2] = (Button) findViewById(R.id.btn3);   // 숫자   num홀수
        button[3] = (Button) findViewById(R.id.btn4);   // 계절   num짝수
        button[4] = (Button) findViewById(R.id.btn5);   // 숫자   num홀수
        button[5] = (Button) findViewById(R.id.btn6);   // 계절   num짝수
        button[6] = (Button) findViewById(R.id.btn7);   // 숫자   num홀수
        button[7] = (Button) findViewById(R.id.btn8);   // 계절   num짝수

        // 랜덤값 1~8
        Random random = new Random();
        for (int x = 0; x < 8; x++) {
            num[x] = random.nextInt(8) + 1;
            for (int y = 0; y < x; y++) {
                if (num[x] == num[y]) {
                    x--;
                }
            }
        }
        // 짝수 랜덤값 0, 2, 4, 6
        for (int x = 0; x < 4; x++) {
            even_num[x] = 2 * random.nextInt(4);
            for (int y = 0; y < x; y++) {
                if (even_num[x] == even_num[y]) {
                    x--;
                }
            }
        }
        // 홀수 랜덤값 1, 3, 5, 7
        for (int x = 0; x < 4; x++) {
            odd_num[x] = 2 * random.nextInt(4) + 1;
            for (int y = 0; y < x; y++) {
                if (odd_num[x] == odd_num[y]) {
                    x--;
                }
            }
        }

        int even = 0;
        int odd = 0;

        for (int x = 0; x < 8; x++) {
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
                }
                even++;
            } else {
                button[odd_num[odd]].setTag(num[x]);
                switch (num[x]) {
                    case 2:
                        button[odd_num[odd]].setText("봄");
                        break;
                    case 4:
                        button[odd_num[odd]].setText("여름");
                        break;
                    case 6:
                        button[odd_num[odd]].setText("가을");
                        break;
                    case 8:
                        button[odd_num[odd]].setText("겨울");
                        break;
                }
                odd++;

            }
        }

        timer = new CountDownTimer(31 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer_count--;
                countTxt = (TextView) findViewById(R.id.count_txt);
                countTxt.setText("제한시간: " + timer_count + "초");
                if (timer_count == 0) {
                    timer.cancel();
                    timer.onFinish();
                    Toast.makeText(getApplicationContext(), "이번 게임의 점수는 " + String.valueOf(timer_count) + "점 입니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondGameActivity4.this, SecondGameActivity5.class);
                    intent.putExtra("timer_count1", String.valueOf(timer_count));
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFinish() {
                countTxt = (TextView) findViewById(R.id.count_txt);
                countTxt.setText("제한시간: " + timer_count + "초");
            }
        };
    }

    @Override
    public void onClick(View view) {
        int CompareValue = Integer.parseInt(view.getTag().toString());
        final Button button = (Button) view;
        Handler delayHandler = new Handler();
        Animation animation = new AlphaAnimation(0, 1);


        if (CompareValue == 1) {
            timer.start();
        }

        if (CompareValue == count) {
            if (CompareValue % 2 == 0) {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.rgreen));
                button.setEnabled(false);
            } else {
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.cgreen));
                button.setEnabled(false);
            }
            count++;
            if (count == 9) {
                timer.cancel();
                timer.onFinish();

                Toast.makeText(getApplicationContext(), "이번게임 점수는 " + String.valueOf(timer_count) + "점 입니다.", Toast.LENGTH_SHORT).show();
                SelectGame.secondGameResult2 = timer_count;
                Intent intent = new Intent(SecondGameActivity4.this, SecondGameActivity5.class);
                intent.putExtra("timer_count1", String.valueOf(timer_count));
                startActivity(intent);
                finish();
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
                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.rred));
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setBackgroundDrawable(getResources().getDrawable(R.drawable.rblack));
                    }
                }, 2000);
            }
        }
//        Toast.makeText(getApplicationContext(), "잘못된 순서입니다.", Toast.LENGTH_SHORT).show();


    }




}


