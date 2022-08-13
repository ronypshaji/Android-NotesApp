package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_2.databinding.ActivityMain2Binding;
import com.example.test_2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "";
    private ActivityMainBinding binding;
    Dialog add_data_dialog;
    private SQLiteDatabase sqLiteDatabase;
    private DBhelper dbHelper;
    private List<notes_model> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addDataButton;
        EditText item_title;
        TextView title;
        Spinner categorySpinner;
        dbHelper = new DBhelper(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sqLiteDatabase = openOrCreateDatabase("NotesDb", MODE_PRIVATE, null);

        add_data_dialog = new Dialog(MainActivity.this);
        add_data_dialog.setContentView(R.layout.activity_add_item);
        add_data_dialog.setCanceledOnTouchOutside(true);
        add_data_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_data_dialog.show();
            }
        });

        addDataButton = (Button) add_data_dialog.findViewById(R.id.add_data_item);
        item_title = (EditText) add_data_dialog.findViewById(R.id.item_title);
        categorySpinner = (Spinner) add_data_dialog.findViewById(R.id.group_spinner);



        addDataButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                String note = item_title.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();

                if (category.equals("Please select a category")  || note.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
                }
                else
                {

                    if(dbHelper.AddNote(category, note))
                    {
                        Toast.makeText(MainActivity.this, "Your Note has been added", Toast.LENGTH_LONG).show();
                        add_data_dialog.dismiss();
                        restartActivity();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error Adding your note", Toast.LENGTH_LONG).show();
                    }

                }

            }



        });






        getAllCategory();

        binding.lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String category_selected = orderList.get(position).getCategory();
                  openActivity2(category_selected);


            }
        });




    }

    private void openActivity2(String category_selected) {
        Intent intent = new Intent(this, MainActivity2.class);

       intent.putExtra(EXTRA_MESSAGE, category_selected);
         startActivity(intent);
    }




    public void restartActivity(){

        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }

    private void getAllCategory() {

        orderList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllCategory();

        if ((cursor != null))
        {
            //Log.d("cursor",(String) cursor.getCount());
            if (cursor.moveToFirst()) {
                do {
                    // fetch items of each column
                    Log.d("DEBUG", "loadEmployees: " + cursor.getString(0));
                    orderList.add(
                            new notes_model(
                                    cursor.getString(0)
                            )
                    );
                } while (cursor.moveToNext());
                cursor.close();
            }

            category_list adapter = new category_list(
                    this,
                    R.layout.activity_category_list,
                    orderList,
                    dbHelper
            );
            binding.lvOrders.setAdapter(adapter);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllCategory();

    }
}