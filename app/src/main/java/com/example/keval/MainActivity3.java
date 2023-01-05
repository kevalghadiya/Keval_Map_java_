package com.example.keval;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.keval.RoomDataBase.AppDatabase;
import com.example.keval.RoomDataBase.GetDataAdapter;
import com.example.keval.RoomDataBase.User;
import com.example.keval.RoomDataBase.UserDao;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView rvGetData;
    private static final String TAG = "MainActivity3";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        rvGetData = findViewById(R.id.rvGetData);
        getData();
    }

    private void getData() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "keval_db").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        List<User> users = userDao.getAll();
        Log.d(TAG, "getData: "+users);
        rvGetData.setLayoutManager(new LinearLayoutManager(this));

        GetDataAdapter getDataAdapter = new GetDataAdapter(users);
        rvGetData.setAdapter(getDataAdapter);
    }
    public class ExampleAsync extends AsyncTask<Integer,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }
    }
}