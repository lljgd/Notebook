package com.example.notebook.feature.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.notebook.R;

import java.util.UUID;

public class NoteActivity extends AppCompatActivity {

    private static final String KEY_NOTE = "key_note";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_container_note_details) == null) {
            UUID noteId = (UUID) getIntent().getSerializableExtra(KEY_NOTE);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_note_details, NoteFragment.makeInstance(noteId))
                    .commit();
        }
    }

    public static Intent makeIntent(Context context, UUID noteId) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(KEY_NOTE, noteId);
        return intent;
    }
}
