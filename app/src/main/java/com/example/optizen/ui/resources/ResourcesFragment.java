package com.example.optizen.ui.resources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.optizen.databinding.FragmentResourcesBinding;

public class ResourcesFragment extends Fragment {

    private FragmentResourcesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResourcesViewModel resourcesViewModel =
                new ViewModelProvider(this).get(ResourcesViewModel.class);

        binding = FragmentResourcesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textResources;
        resourcesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
