package com.example.notebook.feature.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notebook.R;
import com.example.notebook.data.model.Note;
import com.example.notebook.data.store.NoteStoreProvider;

import java.util.UUID;

public class NoteFragment extends Fragment {

    private static final String KEY_NOTE_ID = "key_note_id";

    private Note note;

    private EditText record;
    private TextView number;
    private TextView date;
    private Button topic;
    private CheckBox done;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID id = (UUID) getArguments().getSerializable(KEY_NOTE_ID);
        note = NoteStoreProvider.getInstance(getContext()).getById(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        record = view.findViewById(R.id.record);
        number = view.findViewById(R.id.number_note);
        date = view.findViewById(R.id.date_note);
        topic = view.findViewById(R.id.topic);
        done = view.findViewById(R.id.done_note);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        record.setText(note.getRecord());
        number.setText(note.getNumber());
        date.setText(note.getData().toString());
        topic.setText(note.getTopic());
        done.setChecked(note.isDone());
    }

    public static NoteFragment makeInstance(UUID id) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_NOTE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
}
