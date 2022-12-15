package be.ehb.myrestapi.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import be.ehb.myrestapi.databinding.FragmentFirstBinding;
import be.ehb.myrestapi.fragments.util.PostAdapter;
import be.ehb.myrestapi.model.PostData;
import be.ehb.myrestapi.viewmodel.OurViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PostAdapter adapter = new PostAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        binding.rvPosts.setAdapter(adapter);
        binding.rvPosts.setLayoutManager(layoutManager);

        OurViewModel model = new ViewModelProvider(getActivity()).get(OurViewModel.class);
        model.getPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList<PostData>>() {
            @Override
            public void onChanged(ArrayList<PostData> postData) {
                adapter.setItems(postData);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}