package com.example.notebook.feature.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.R;
import com.example.notebook.data.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<Note> notes;

    public NoteListAdapter(List<Note> notes) {
        this.notes = notes;

        setHasStableIds(true);
    }

    public void submitList(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public long getItemId(int position) {
        return notes.get(position).getId().hashCode();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_note_list, parent, false);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bindTo(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
