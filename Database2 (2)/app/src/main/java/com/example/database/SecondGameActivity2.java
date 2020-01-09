package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondGameActivity2 extends AppCompatActivity {

    String sNum = "1";
    int timer = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_game2);

        Button btn1 = findViewById(R.id.cip_num1_button); Button btn14 = findViewById(R.id.cip_num14_button);
        Button btn2 = findViewById(R.id.cip_num2_button); Button btn15 = findViewById(R.id.cip_num15_button);
        Button btn3 = findViewById(R.id.cip_num3_button); Button btn16 = findViewById(R.id.cip_num16_button);
        Button btn4 = findViewById(R.id.cip_num4_button); Button btn17 = findViewById(R.id.cip_num17_button);
        Button btn5 = findViewById(R.id.cip_num5_button); Button btn18 = findViewById(R.id.cip_num18_button);
        Button btn6 = findViewById(R.id.cip_num6_button); Button btn19 = findViewById(R.id.cip_num19_button);
        Button btn7 = findViewById(R.id.cip_num7_button); Button btn20 = findViewById(R.id.cip_num20_button);
        Button btn8 = findViewById(R.id.cip_num8_button); Button btn21= findViewById(R.id.cip_num21_button);
        Button btn9 = findViewById(R.id.cip_num9_button); Button btn22= findViewById(R.id.cip_num22_button);
        Button btn10 = findViewById(R.id.cip_num10_button); Button btn23 = findViewById(R.id.cip_num23_button);
        Button btn11 = findViewById(R.id.cip_num11_button); Button btn24 = findViewById(R.id.cip_num24_button);
        Button btn12 = findViewById(R.id.cip_num12_button); Button btn25= findViewById(R.id.cip_num25_button);
        Button btn13 = findViewById(R.id.cip_num13_button);

        final Button cipStart = findViewById(R.id.cip_start_button);

        //초기화 버튼 미사용
        Button cipClear = findViewById(R.id.cip_clear_button);
        cipClear.setVisibility(View.GONE);



        final String[] ascNum = {  "1","2","3","4","5", "6","7","8","9","10",
                "11","12","13","14","15", "16","17","18","19","20",
                "21","22","23","24","25"};

        final Button[] buttonArray = {btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
                btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20,
                btn21, btn22, btn23, btn24, btn25};

        for(int i=0; i<25; i++){
            buttonArray[i].setText(ascNum[i]);
            buttonArray[i].setBackground(getResources().getDrawable(R.drawable.custom_button_init_background));
        }



        final ButtonArrayClickableControl btnArrayClickableControl = new ButtonArrayClickableControl(buttonArray);
        btnArrayClickableControl.setBtnArrayUnClickable();

        final CountDownTimer puzzleTimer = new CountDownTimer(60* 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer--;
                TextView timeTextView = findViewById(R.id.time_text_view);
                timeTextView.setText("제한시간 : " + timer+ "초" );

                if(timer ==0){
                    SelectGame.secondGameResult1 = timer;
                    Intent intent = new Intent(getApplicationContext(), SecondGameActivity3.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onFinish() {
                TextView timeTextView = findViewById(R.id.time_text_view);
                timeTextView.setText("제한시간 : " + timer+ "초" );
            }
        };


        /*
        cipClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer = 60;
                puzzleTimer.cancel();
                puzzleTimer.onFinish();

                sNum = "1";

                String[] clearNum = {  "1","2","3","4","5", "6","7","8","9","10",
                        "11","12","13","14","15", "16","17","18","19","20",
                        "21","22","23","24","25"};   //차후 오름차순 정렬 함수로 빼야함

                for(int i=0; i<25; i++){
                    buttonArray[i].setText(clearNum[i]);
                    buttonArray[i].setBackgroundColor(getResources().getColor(R.color.colorInitPuzzle));
                    buttonArray[i].setEnabled(false);
                }
                cipStart.setEnabled(true);
            }
        });
        */

        cipStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnArrayClickableControl.setBtnArrayAllClickable();
                String[] randomNum = shuffle(ascNum);

                for(int i=0; i<25; i++){
                    buttonArray[i].setText(randomNum[i]);
                }

                puzzleTimer.cancel();
                timer = 60;
                puzzleTimer.start();
                cipStart.setEnabled(false);
            }
        });

        View.OnClickListener btnOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                for(int i =0; i<buttonArray.length; i++){
                    if(id == buttonArray[i].getId()){
                        if(sNum.equals(buttonArray[i].getText())) {
                            buttonArray[i].setBackground(getResources().getDrawable(R.drawable.custom_button_right_background));
                            buttonArray[i].setEnabled(false);
                            if(sNum.equals("25")){
                                Toast.makeText(getApplicationContext(),"정답입니다", Toast.LENGTH_LONG).show();
                                puzzleTimer.cancel();
                                puzzleTimer.onFinish();

                                SelectGame.secondGameResult1 = timer;
                                Intent intent = new Intent(getApplicationContext(), SecondGameActivity3.class);
                                startActivity(intent);
                                finish();


                            }
                            sNum = Integer.toString(Integer.parseInt(sNum) + 1);

                        }else {
                            buttonArray[i].setBackground(getResources().getDrawable(R.drawable.custom_button_wrong_background));
                            Runnable pauseRunnable = new PauseRunnable(buttonArray, i);
                            Thread pauseThread = new Thread(pauseRunnable);
                            pauseThread.start();
                        }
                    }
                }

            }
        };

        //향상된 for문을 통해 btnOnClickListener에 연결
        for(Button btn: buttonArray){
            btn.setOnClickListener(btnOnClickListener);
        }


    }


    public String[] shuffle(String[] strArray){

        for(int i=0; i<strArray.length; i++){
            int a = (int)(Math.random()*strArray.length);
            int b = (int)(Math.random()*strArray.length);

            String temp = strArray[a];
            strArray[a] = strArray[b];
            strArray[b] = temp;
        }

        return strArray;

    }


    //버튼 전체의 활성/비활성을 컨트롤하기 위한 클래스
    class ButtonArrayClickableControl{
        Button[] btnArray;


        public ButtonArrayClickableControl(Button[] btnArray){
            this.btnArray = btnArray;
        }

        public void setBtnArrayAllClickable(){
            for(Button btn: btnArray){
                btn.setEnabled(true);
            }
        }

        public void setBtnArrayUnClickable(){
            for(Button btn: btnArray){
                btn.setEnabled(false);
            }
        }

    }



    // 틀렸을시 빨간색 표시후 3초간 클릭불가 이후 정상작동
    class PauseRunnable implements Runnable {
        Button[] btnArray;
        int i;


        public PauseRunnable(Button[] btnArray, int i){
            this.btnArray = btnArray;
            this.i = i;

        }

        @Override
        public void run() {
            try {
                for(Button btn: btnArray){
                    btn.setClickable(false);
                }
                Thread.sleep(3 * 1000);
                btnArray[i].setBackground(getResources().getDrawable(R.drawable.custom_button_init_background));
                for(Button btn: btnArray){
                    btn.setClickable(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

    }


}

