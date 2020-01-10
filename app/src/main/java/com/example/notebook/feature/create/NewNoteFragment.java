package com.example.notebook.feature.create;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notebook.R;
import com.example.notebook.data.model.Note;
import com.example.notebook.data.store.NoteStoreProvider;

public class NewNoteFragment extends Fragment {

    private Note note;

    private EditText record;
    private Button topicQuickly;
    private Button topicMake;
    private Button topicCreate;
    private Button save;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        note = new Note();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        record = view.findViewById(R.id.new_record);
        topicQuickly = view.findViewById(R.id.topic_new_note_quickly);
        topicMake = view.findViewById(R.id.topic_new_note_make);
        topicCreate = view.findViewById(R.id.topic_new_note_create);
        save = view.findViewById(R.id.button_save);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        record.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                note.setRecord(s.toString());
            }
        });

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button_save) {
                    saveNoteObject(note);
                    getFragmentManager().popBackStack();
                }
                else {
                    textButtonTopic(v);
                }
            }
        };

        topicQuickly.setOnClickListener(buttonListener);
        topicMake.setOnClickListener(buttonListener);
        topicCreate.setOnClickListener(buttonListener);
        save.setOnClickListener(buttonListener);
    }

    private void textButtonTopic(View v) {
        Button button = (Button) v;
        String textButton = button.getText().toString();
        note.setTopic(textButton);
    }

    private void saveNoteObject(Note note) {
        NoteStoreProvider.getInstance(getContext()).insert(note);
    }
}
