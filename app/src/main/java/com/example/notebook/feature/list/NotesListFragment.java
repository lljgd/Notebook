package com.example.notebook.feature.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.R;
import com.example.notebook.data.model.Note;
import com.example.notebook.data.store.NoteStoreProvider;
import com.example.notebook.feature.create.NewNoteFragment;
import com.example.notebook.feature.list.adapter.NoteListAdapter;
import com.example.notebook.feature.list.adapter.NoteViewHolder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class NotesListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoteListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NoteListAdapter(NoteStoreProvider.getInstance(getContext()).getAllNotes());
        recyclerView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        NoteViewHolder noteViewHolder = (NoteViewHolder) viewHolder;
                        Note note = noteViewHolder.getNote();
                        deleteItem(note);
                    }
                }
        );
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void deleteItem(final Note note) {
        NoteStoreProvider.getInstance(getContext()).deleteNote(note);
        updateList();
        Snackbar.make(recyclerView, R.string.snack_bar_list_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.snack_bar_list_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NoteStoreProvider.getInstance(getContext()).insert(note);
                        updateList();
                    }
                })
                .show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.item_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new NewNoteFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        List<Note> notes = NoteStoreProvider.getInstance(getContext()).getAllNotes();
        adapter.submitList(notes);
    }
}
