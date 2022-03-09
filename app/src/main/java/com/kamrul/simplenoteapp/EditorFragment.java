package com.kamrul.simplenoteapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.kamrul.simplenoteapp.databinding.EditorFragmentBinding;

public class EditorFragment extends Fragment {

    private EditorViewModel mViewModel;
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

        binding = EditorFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
        args = EditorFragmentArgs.fromBundle(getArguments());

        binding.editor.setText("You selected note # " + args.getNoteId());

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                saveAndReturn();
            }
        });

        return binding.getRoot();
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
        Navigation.findNavController(getActivity(), R.id.navHostFragment).navigateUp();
        return true;
    }
}