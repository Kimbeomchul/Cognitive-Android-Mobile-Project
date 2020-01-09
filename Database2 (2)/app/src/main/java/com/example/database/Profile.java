package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.String.valueOf;


public class Profile extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button button;
    private Button btn;
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference rootref = reference.child(user.getUid()).child("나이").child("Age");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        textView = (TextView) findViewById(R.id.showAge);
        editText = (EditText) findViewById(R.id.Age);
        button = (Button) findViewById(R.id.iprofile);
        btn = (Button) findViewById(R.id.secbtn);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    }

    @Override
    protected void onStart() {
        super.onStart();

        rootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textView.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootref.setValue(editText.getText().toString());
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.setValue(AgeChange.getText().toString());
                int id = v.getId();
                Intent intent;

                switch (id) {
                    case R.id.secbtn:
                        intent = new Intent(getApplicationContext(), SelectGame.class);
                        intent.putExtra("age", valueOf(editText));

                        startActivity(intent);
                        break;

                    default:
                        break;
                }
            }
        });
    }
}