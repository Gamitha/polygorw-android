package com.orenda.polygrow.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orenda.polygrow.R;
import com.orenda.polygrow.ui.proj.ProjectItem;
import com.orenda.polygrow.ui.proj.ProjectListAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        List<ProjectItem> projectList = List.of(
                new ProjectItem("Project 1", "Description 1", "Date 1", "Time 1", "Location 1", "Status 1"),
                new ProjectItem("Project 2", "Description 2", "Date 2", "Time 2", "Location 2", "Status 2"),
                new ProjectItem("Project 3", "Description 3", "Date 3", "Time 3", "Location 3", "Status 3"),
                new ProjectItem("Project 4", "Description 4", "Date 4", "Time 4", "Location 4", "Status 4"),
                new ProjectItem("Project 5", "Description 5", "Date 5", "Time 5", "Location 5", "Status 5")
        );

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ProjectListAdapter(projectList));
        return view;
    }

}