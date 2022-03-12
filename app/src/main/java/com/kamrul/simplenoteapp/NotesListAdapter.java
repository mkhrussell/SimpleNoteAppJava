package com.kamrul.simplenoteapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ListItemBinding.bind(itemView);
        }
    }

    private final List<NoteEntity> notesList;
    private final ListItemListener listener;

    private final List<NoteEntity> selectedNotes = new ArrayList<NoteEntity>();

    public NotesListAdapter(List<NoteEntity> notesList, ListItemListener listener) {
        this.notesList = notesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteEntity note = notesList.get(position);
        holder.binding.noteText.setText(note.getText());
        holder.binding.getRoot().setOnClickListener(view -> listener.editNote(view, note.getId()));
        holder.binding.fab.setOnClickListener(view -> {
            if(selectedNotes.contains(note)) {
                selectedNotes.remove(note);
                holder.binding.fab.setImageResource(R.drawable.ic_note);
            } else {
                selectedNotes.add(note);
                holder.binding.fab.setImageResource(R.drawable.ic_check);
            }
            listener.onItemSelectionChanged();
        });

        if(selectedNotes.contains(note)) {
            holder.binding.fab.setImageResource(R.drawable.ic_check);
        } else {
            holder.binding.fab.setImageResource(R.drawable.ic_note);
        }
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public List<NoteEntity> getSelectedNotes() {
        return selectedNotes;
    }

    interface ListItemListener {
        void editNote(View view, int noteId);
        void onItemSelectionChanged();
    }
}
