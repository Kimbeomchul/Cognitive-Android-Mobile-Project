package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ThirdGameActivity6 extends AppCompatActivity {

    private int[] num = new int[3];
    private Button[] button = new Button[3];
    private int answer_count5 = 0;

    private int timer_count = 15;
    private TextView countTxt;
    private CountDownTimer timer;
    final Animation animation = new AlphaAnimation(0, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_game6);

        Intent intent = getIntent();
        answer_count5 = intent.getIntExtra("answer_count", 0);

        button[0] = (Button) findViewById(R.id.btn1);
        button[1] = (Button) findViewById(R.id.btn2);
        button[2] = (Button) findViewById(R.id.btn3);

        Random random = new Random();
        for (int x = 0; x < 3; x++) {
            num[x] = random.nextInt(3);
            for (int y = 0; y < x; y++) {
                if (num[x] == num[y]) {
                    x--;
                }
            }
        }

        button[num[0]].setBackgroundDrawable(getResources().getDrawable(R.drawable.e_1));
        button[num[0]].setTag(num[0]);
        button[num[1]].setBackgroundDrawable(getResources().getDrawable(R.drawable.e_2));
        button[num[1]].setTag(num[1]);
        button[num[2]].setBackgroundDrawable(getResources().getDrawable(R.drawable.e_answer));
        button[num[2]].setTag(num[2]);

        timer = new CountDownTimer(15 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer_count--;
                countTxt = (TextView) findViewById(R.id.count_txt);
                countTxt.setText("제한시간: " + timer_count + "초");
                if (timer_count == 0) {
                    timer.cancel();
                    timer.onFinish();
                    Toast.makeText(getApplicationContext(), "시간이 초과되었습니다."+"  현재 점수: "+answer_count5, Toast.LENGTH_SHORT).show();
                    button[0].setEnabled(false);
                    button[1].setEnabled(false);
                    button[2].setEnabled(false);

                    SelectGame.thirdGameResult1 = answer_count5;
                    finish();

                }
            }

            @Override
            public void onFinish() {
                countTxt = (TextView) findViewById(R.id.count_txt);
                countTxt.setText("제한시간: " + timer_count + "초");
            }
        };

        timer.start();
    }

    public void onClick(View view) {
        int CompareValue = Integer.parseInt(view.getTag().toString());

        timer.cancel();
        timer.onFinish();

        button[0].setEnabled(false);
        button[1].setEnabled(false);
        button[2].setEnabled(false);


        if (CompareValue == num[0]) {
            Toast.makeText(getApplicationContext(), "틀렸습니다." + "  현재 점수: " + answer_count5, Toast.LENGTH_SHORT).show();
        } else if (CompareValue == num[1]) {
            Toast.makeText(getApplicationContext(), "틀렸습니다." + "  현재 점수: " + answer_count5, Toast.LENGTH_SHORT).show();
        } else {
            answer_count5++;
            Toast.makeText(getApplicationContext(), "정답입니다." + "  현재 점수: " + answer_count5, Toast.LENGTH_SHORT).show();
        }

        SelectGame.thirdGameResult1 = answer_count5;
        finish();

    }
}
