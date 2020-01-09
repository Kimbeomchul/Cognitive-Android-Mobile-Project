package com.example.database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Result1 extends AppCompatActivity {
    public int g1, g2, g3, g4, g5;
    public int count = 1;
    public int count2 = 1;
    private TextView txt;
    private Button btn;
    private TextView txt2;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    List<Object> Array = new ArrayList<Object>();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference rootref = reference.child(user.getUid()).child("인지력향상퍼즐");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        listView = (ListView) findViewById(R.id.gameresult);

        initDatabase();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, new ArrayList<String>());
        listView.setAdapter(adapter);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    }
    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();


        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {


                        String str = messageData.getValue().toString();
                        Array.add(str);
                        adapter.add("인지력향상퍼즐"+count + "-" + count2 + "번째 게임점수 : " + str);
                        count2++;

                }
              adapter.notifyDataSetChanged();
              listView.setSelection(adapter.getCount() - 1);
                count ++;
                count2 = 1;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String str = messageData.getValue().toString();
                 //  Array.add(str);
                  // adapter.add(str);

                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        rootref.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootref.removeEventListener(mChild);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();

        //mReference = mDatabase.getReference(user.getUid());
        rootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String str = messageData.getValue().toString();
                    Array.add(count+"번째게임"+str);

                    adapter.add(str);


                }
                adapter.notifyDataSetChanged();
                listView.setSelection(adapter.getCount() - 1);
                count++;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    */
}