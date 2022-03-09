package com.kamrul.simplenoteapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.databinding.MainFragmentBinding;

import java.util.List;

public class MainFragment extends Fragment implements NotesListAdapter.ListItemListener {

    private MainViewModel viewModel;
    private MainFragmentBinding binding;
    private NotesListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), new LinearLayoutManager(getContext()).getOrientation());
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.notesList.observe(getViewLifecycleOwner(), new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> notesList) {
                Log.i(Constants.TAG, notesList.toString());
                adapter = new NotesListAdapter(notesList, MainFragment.this);
                binding.recyclerView.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onItemClick(int noteId) {
        Log.i(Constants.TAG, "Note id: " + noteId);
    }
}