package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstGameActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_game1);

        Button btnFirstGameStart = findViewById(R.id.first_game_start_button);
        btnFirstGameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstGameActivity1.this, FirstGameActivity2.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
