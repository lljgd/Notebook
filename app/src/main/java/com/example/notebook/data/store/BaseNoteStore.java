package com.example.notebook.data.store;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseNoteStore implements NoteStore {

    private final Set<Listener> listenersSet = new HashSet<>();

    protected void notifyListeners() {
        for (Listener e: listenersSet) {
            e.onNotesListChanged();
        }
    }

    @Override
    public void addListener(Listener listener) {
        listenersSet.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listenersSet.remove(listener);
    }
}
