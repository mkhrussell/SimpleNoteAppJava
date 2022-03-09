package com.kamrul.simplenoteapp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
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
        binding = EditorFragmentBinding.inflate(inflater, container, false);
        args = EditorFragmentArgs.fromBundle(getArguments());

        binding.editor.setText("You selected note # " + args.getNoteId());

        mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);

        return binding.getRoot();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }

}