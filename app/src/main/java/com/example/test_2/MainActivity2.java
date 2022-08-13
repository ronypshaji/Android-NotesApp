package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.test_2.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private List<notes_model> orderList;
    private DBhelper dbHelper;
    private ActivityMain2Binding binding2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbHelper = new DBhelper(this);
        //Button button;
        binding2 = ActivityMain2Binding.inflate(getLayoutInflater());

        setContentView(binding2.getRoot());



        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        getNotesWithCategory(message);
        Toast.makeText(MainActivity2.this, message, Toast.LENGTH_LONG).show();

        binding2.lvOrders11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(MainActivity2.this, "Note Clicked", Toast.LENGTH_LONG).show();


            }
        });

    }

    private void getNotesWithCategory(String category) {

        orderList = new ArrayList<>();
        Cursor cursor = dbHelper.getNoteswithCategory(category);
        if ((cursor != null))
        {
            if (cursor.moveToFirst()) {
                do {
                    // fetch items of each column
                    Log.d("DEBUG", "loadEmployees: " + cursor.getString(0));
                    orderList.add(
                            new notes_model(
                                    cursor.getString(0),
                                    cursor.getInt(1)
                            )
                    );
                } while (cursor.moveToNext());
                cursor.close();
            }

            notes_list adapter = new notes_list(
                    this,
                    R.layout.activity_notes_list,
                    orderList,
                    dbHelper
            );
            binding2.lvOrders11.setAdapter(adapter);


        }

    }

}