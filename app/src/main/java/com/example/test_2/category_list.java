package com.example.test_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class category_list extends ArrayAdapter {


    private final Context context;
    private final int resource;
    private final List<notes_model> notes_models;
    private final DBhelper dbHelper;
    public category_list(@NonNull Context context, int resource, @NonNull List<notes_model> notes_models, DBhelper dbHelper
    ) {
        super(context, resource, notes_models);
        this.context = context;
        this.resource = resource;
        this.notes_models = notes_models;
        this.dbHelper = dbHelper;
    }
    @Override
    public int getCount() {
        return notes_models.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(resource, null);

            TextView category = v.findViewById(R.id.title);
            category.setText(notes_models.get(position).getCategory());


        return v;

    }



}