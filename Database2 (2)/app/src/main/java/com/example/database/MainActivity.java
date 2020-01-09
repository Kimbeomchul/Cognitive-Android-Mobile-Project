package com.example.database;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{3,14}$"); //비밀번호 암호 자릿수 영문숫자특수기호

    private FirebaseAuth firebaseAuth; // 파이어베이스 인증객체

    private EditText editTextEmail;     //이메일
    private EditText editTextPassword; // 비밀번호

    private String email = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Button startSwapPuzzleBtn = findViewById(R.id.start_swap_puzzle_button);
//        Button startSequencePuzzleBtn = findViewById(R.id.start_sequence_puzzle_button);

//        startSwapPuzzleBtn.setOnClickListener(this);
//        startSequencePuzzleBtn.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance(); // 인증객체선언


        editTextEmail = findViewById(R.id.et_eamil);
        editTextPassword = findViewById(R.id.et_password);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
    }

/*
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;


        switch (id) {
            case R.id.start_swap_puzzle_button:
                intent = new Intent(MainActivity.this, CognitiveSwap.class);
                startActivity(intent);
                break;

            case R.id.start_sequence_puzzle_button:
                intent = new Intent(MainActivity.this, CognitiveSequence.class);
                startActivity(intent);
                break;

            default:
                break;
        }
*/


    public void singUp(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if (isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    public void signIn(View view) {
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        if (isValidEmail() && isValidPasswd()) {

            loginUser(email, password);
        }
    }

    // 이메일 검사
    private boolean isValidEmail() {
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "이메일칸이 비어있습니다.", Toast.LENGTH_LONG).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "이메일형식이 올바르지 않습니다.", Toast.LENGTH_LONG).show();
            return false;
        } else {

            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "비밀번호칸이 비어있습니다.", Toast.LENGTH_LONG).show();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Toast.makeText(getApplicationContext(), "비밀번호형식이 올바르지 않습니다.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공
                            Toast.makeText(MainActivity.this, R.string.success_signup, Toast.LENGTH_SHORT).show();
                        } else {
                            // 회원가입 실패
                            Toast.makeText(MainActivity.this, R.string.failed_signup, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // 로그인
    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                             Toast.makeText(MainActivity.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(MainActivity.this, Profile.class);
                    //      intent.putExtra("UID",editTextEmail.getText().toString());
                           startActivity(intent);
                           finish();
                        } else {
                            // 로그인 실패
                            Toast.makeText(MainActivity.this, R.string.failed_login, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


