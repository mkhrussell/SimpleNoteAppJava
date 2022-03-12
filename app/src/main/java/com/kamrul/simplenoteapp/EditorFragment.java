package com.kamrul.simplenoteapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.kamrul.simplenoteapp.data.NoteEntity;
import com.kamrul.simplenoteapp.databinding.ActivityMainBinding;
import com.kamrul.simplenoteapp.databinding.EditorFragmentBinding;

public class EditorFragment extends Fragment {

    private EditorViewModel viewModel;
    private EditorFragmentArgs args;
    private EditorFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_check);
        }
        setHasOptionsMenu(true);

        args = EditorFragmentArgs.fromBundle(getArguments());
        if(args.getNoteId() == Constants.NEW_NOTE_ID) {
            requireActivity().setTitle(getString(R.string.new_note));
        } else {
            requireActivity().setTitle(getString(R.string.edit_note));
        }

        viewModel = new ViewModelProvider(this).get(EditorViewModel.class);

        binding = EditorFragmentBinding.inflate(inflater, container, false);
        binding.editor.setText("");

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                saveAndReturn();
            }
        });

        viewModel.getCurrentNote().observe(getViewLifecycleOwner(), note -> {
            String savedText = null;
            int cursorPosition = -1;
            if(savedInstanceState != null) {
                savedText = savedInstanceState.getString(Constants.NOTE_TEXT_KEY);
                cursorPosition = savedInstanceState.getInt(Constants.CURSOR_POSITION_KEY);
            }
            if(savedText != null) {
                binding.editor.setText(note.getText());
                if(cursorPosition >= 0)
                    binding.editor.setSelection(cursorPosition);
            } else {
                binding.editor.setText(note.getText());
            }
        });

        viewModel.getNoteById(args.getNoteId());

        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(Constants.NOTE_TEXT_KEY, binding.editor.getText().toString());
        outState.putInt(Constants.CURSOR_POSITION_KEY, binding.editor.getSelectionStart());

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return saveAndReturn();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean saveAndReturn() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);

        if(viewModel.getCurrentNote().getValue() != null) {
            viewModel.getCurrentNote().getValue().setText(binding.editor.getText().toString());
            viewModel.updateNote();
        }

        Navigation.findNavController(getActivity(), R.id.navHostFragment).navigateUp();
        return true;
    }
}