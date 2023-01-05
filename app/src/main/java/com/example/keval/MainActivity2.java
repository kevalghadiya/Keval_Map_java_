package com.example.keval;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.keval.RoomDataBase.AppDatabase;
import com.example.keval.RoomDataBase.User;
import com.example.keval.RoomDataBase.UserDao;

public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = "MainActivity2";
    EditText tvFirstName, tvLastName;
    Button btnSubmit,btnFeatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnFeatch = findViewById(R.id.btnFeatch);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTharead().start();
            }
        });

        btnFeatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }
        });

    }

    public class MyTharead extends Thread {
        @Override
        public void run() {
            super.run();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "keval_db").build();
            UserDao userDao = db.userDao();
            userDao.insertAllData(new User(tvFirstName.getText().toString(), tvLastName.getText().toString()));
            Log.d(TAG, "run: ");


        }
    }
}