package com.example.notebook.feature.list.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.R;
import com.example.notebook.data.model.Note;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private NoteListAdapter.ItemListener itemListener;

    private Note note;

    private TextView recordView;
    private CheckBox doneView;
    private TextView dataView;
    private TextView topicView;

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            itemListener.onNoteClicked(note);
        }
    };

    public NoteViewHolder(@NonNull View itemView, NoteListAdapter.ItemListener itemListener) {
        super(itemView);

        recordView = itemView.findViewById(R.id.record);
        doneView = itemView.findViewById(R.id.done);
        dataView = itemView.findViewById(R.id.data);
        topicView = itemView.findViewById(R.id.topic);

        itemView.setOnClickListener(clickListener);

        this.itemListener = itemListener;
    }

    public void bindTo(Note note) {
        this.note = note;

        recordView.setText(note.getRecord());
        doneView.setChecked(note.isDone());
        dataView.setText(note.getData().toString());
        topicView.setText(note.getTopic());
    }

    public Note getNote() {
        return note;
    }
}
