package com.example.database;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static java.sql.Types.NULL;

public class SelectGame extends AppCompatActivity {
    public  static int firstGameResult1=0;
    public  static int firstGameResult2=0;
    public  static int firstGameResult3=0;
    public  static int secondGameResult1=0;
    public  static int secondGameResult2=0;
    public  static int secondGameResult3=0;
    public  static int thirdGameResult1=0;

    MediaPlayer mediaPlayer;
    Boolean musicControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgame);

        final Button startSwapPuzzleBtn = findViewById(R.id.start_swap_puzzle_button);
        final Button startSequencePuzzleBtn = findViewById(R.id.start_sequence_puzzle_button);
        final Button startThirdGameBtn = findViewById(R.id.start_equal_image_button);
        final Button Toresult1 = findViewById(R.id.toresult);
        Button musicBtn = findViewById(R.id.music_button);



        View.OnClickListener selectGameListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                Intent intent;

                switch (id) {
                    case R.id.start_swap_puzzle_button:

                        startSwapPuzzleBtn.setEnabled(false);
                        intent = new Intent(getApplication(), FirstGameActivity1.class);
                        startActivity(intent);
                        break;

                    case R.id.start_sequence_puzzle_button:

                        startSequencePuzzleBtn.setEnabled(false);
                        intent = new Intent(getApplication(), SecondGameActivity1.class);
                        startActivity(intent);
                        break;

                    case R.id.start_equal_image_button:

                        startThirdGameBtn.setEnabled(false);
                        intent = new Intent(getApplication(), ThirdGameActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.toresult:
                        intent = new Intent(getApplication(), ResultMain.class);
                        //    intent.putExtra("age", age);
                        intent.putExtra("f1", firstGameResult1);
                        intent.putExtra("f2", firstGameResult2);
                        intent.putExtra("f3", firstGameResult3);
                        intent.putExtra("s1", secondGameResult1);
                        intent.putExtra("s2", secondGameResult2);
                        intent.putExtra("s3", secondGameResult3);
                        intent.putExtra("z1", thirdGameResult1);

                        startActivity(intent);
                        break;

                    default:
                        break;
                }
            }
        };
       //  age = intent.getStringExtra("age");
        startSwapPuzzleBtn.setOnClickListener(selectGameListener);
        Toresult1.setOnClickListener(selectGameListener);
        startSequencePuzzleBtn.setOnClickListener(selectGameListener);
        startThirdGameBtn.setOnClickListener(selectGameListener);

        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(musicControl == false) {
                    mediaPlayer = MediaPlayer.create(SelectGame.this, R.raw.main_theme);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    musicControl = true;
                }else {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    musicControl = false;
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }




    }

