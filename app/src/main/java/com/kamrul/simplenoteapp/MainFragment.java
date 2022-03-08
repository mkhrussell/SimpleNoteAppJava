package com.kamrul.simplenoteapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kamrul.simplenoteapp.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private MainFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.recyclerView.setHasFixedSize(true);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), new LinearLayoutManager(getContext()).getOrientation());
        binding.recyclerView.addItemDecoration(divider);

        return binding.getRoot();
    }

}