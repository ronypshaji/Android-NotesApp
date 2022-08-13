package com.example.test_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_2.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class notes_list extends ArrayAdapter {

    private final Context context;
    private final int resource;
    private final List<notes_model> notes_models;
    private final DBhelper dbHelper;
    private ActivityMain2Binding binding2;
    public notes_list(@NonNull Context context, int resource, @NonNull List<notes_model> notes_models, DBhelper dbHelper
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

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(resource, null);

        TextView notes = v.findViewById(R.id.notesList);
        notes.setText(notes_models.get(position).getNote());

        Button delButton = v.findViewById(R.id.button);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: rrrrrrrrrrrrrrr "+ notes_models.get(position).getId());
                int count = dbHelper.deleteNote(notes_models.get(position).getId());
                notes_models.remove(notes_models.get(position));


                if (count > 0)
                {
                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getContext(), "Error Deleteing", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return v;



    }


}