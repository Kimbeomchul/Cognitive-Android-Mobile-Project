package com.example.database;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class ResultMain extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference reference;
    private Button result1;
    private Button result2;
    private Button result3;
    private int count1 = 1;
    private int count2 = 1;
    private int count3 = 1;

    private Button returnhome ;
    public int game01,game02,game03,game11,game12,game13,game21;
    public int a1 = -1;
    public int a2 = -1;
    public int a3 = -1;
    public int a4 = -1;
    public int a5 = -1;
    public int a6 = -1;
    public int a7 = -1;
    Random random = new Random();
    int i= random.nextInt(10000);
    int j= random.nextInt(10000);
    int k= random.nextInt(10000);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultmain);



        reference = FirebaseDatabase.getInstance().getReference();
        result1 = (Button) findViewById(R.id.Gameresult1);
        result2 = (Button) findViewById(R.id.Gameresult2);
        result3 = (Button) findViewById(R.id.Gameresult3);
        returnhome = (Button) findViewById(R.id.returnhome);


        result1.setOnClickListener(this);
        result2.setOnClickListener(this);
        result3.setOnClickListener(this);
        returnhome.setOnClickListener(this);

        Intent intent = getIntent();
        game01 = intent.getIntExtra("f1",0);
        game02 = intent.getIntExtra("f2",0);
        game03 = intent.getIntExtra("f3",0);
        game11 = intent.getIntExtra("s1",0);
        game12 = intent.getIntExtra("s2",0);
        game13 = intent.getIntExtra("s3",0);
        game21 = intent.getIntExtra("z1",0);
        count1 = intent.getIntExtra("count1",0);
        count2 = intent.getIntExtra("count2",0);
        count3 = intent.getIntExtra("count3",0);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        switch (id) {
            case R.id.Gameresult1:
                Intent intent = new Intent(ResultMain.this, Result1.class);
                if(a1 != game03 || a2!= game02 || a3 != game01) {
                    if (game03 != 0 || game02 != 0 || game01 != 0 ) {

                        reference.child(user.getUid()).child("인지력향상퍼즐").child("Game1" + i).child("인지력향상퍼즐3").setValue(game03);
                        reference.child(user.getUid()).child("인지력향상퍼즐").child("Game1" + i).child("인지력향상퍼즐2").setValue(game02);
                        reference.child(user.getUid()).child("인지력향상퍼즐").child("Game1" + i).child("인지력향상퍼즐1").setValue(game01);
                        a1 = game03;
                        a2 = game02;
                        a3 = game01;
                }
                }
                startActivity(intent);
                break;

            case R.id.Gameresult2:
                intent = new Intent(ResultMain.this, Result2.class);
                if(a4 != game13 || a5!= game12 || a6 != game11) {
                    if (game13 != 0 || game12 != 0 || game11 != 0 ) {
                        reference.child(user.getUid()).child("순서맞추기").child("Game2" + j).child("순서맞추기3").setValue(game13);
                        reference.child(user.getUid()).child("순서맞추기").child("Game2" + j).child("순서맞추기2").setValue(game12);
                        reference.child(user.getUid()).child("순서맞추기").child("Game2" + j).child("순서맞추기1").setValue(game11);
                        a4 = game13;
                        a5 = game12;
                        a6 = game11;
                    }
                }
               startActivity(intent);
                break;

            case R.id.Gameresult3:
                intent = new Intent(ResultMain.this, Result3.class);
                if(a7 != game21) {
                    if (game21 != 0) {
                        reference.child(user.getUid()).child("같은그림찾기").child("Game3" + k).child("같은그림찾기").setValue(game21);
                        a7 = game21;
                    }
                }
                startActivity(intent);
                break;

            case R.id.returnhome:
                intent = new Intent(ResultMain.this, SelectGame.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}

