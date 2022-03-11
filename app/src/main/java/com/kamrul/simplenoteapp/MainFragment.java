package com.kamrul.simplenoteapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
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
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        setHasOptionsMenu(true);

        binding = MainFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), new LinearLayoutManager(getContext()).getOrientation());
        binding.recyclerView.addItemDecoration(divider);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getNotesList().observe(getViewLifecycleOwner(), notesList -> {
            Log.i(Constants.TAG, notesList.toString());

            adapter = new NotesListAdapter(notesList, MainFragment.this);
            binding.recyclerView.setAdapter(adapter);
        });

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        int menuId = R.menu.menu_main;
        if(adapter != null && !adapter.getSelectedNotes().isEmpty()) {
            menuId = R.menu.menu_main_selected_items;
        }

        inflater.inflate(menuId, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sample_data:
                return addSampleData();
            case R.id.action_delete:
                return deleteSelectedNotes();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean deleteSelectedNotes() {
        viewModel.deleteNotes(adapter.getSelectedNotes());

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            adapter.getSelectedNotes().clear();
            requireActivity().invalidateOptionsMenu();
        }, 100);

        return true;
    }

    private boolean addSampleData() {
        viewModel.addSampleData();
        return true;
    }

    @Override
    public void onItemClick(View view, int noteId) {
        Log.i(Constants.TAG, "Note id: " + noteId);

        MainFragmentDirections.ActionEditNote actionEditNote = MainFragmentDirections.actionEditNote();
        actionEditNote.setNoteId(noteId);
        Navigation.findNavController(view).navigate(actionEditNote);
    }

    @Override
    public void onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu();
    }
}