package com.mobileapp.mukhtabir.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.mukhtabir.R;
import com.mobileapp.mukhtabir.adapter.SubjectListAdapter;
import com.mobileapp.mukhtabir.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.data_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Subject>subjects=new ArrayList<>();
        SubjectListAdapter adapter=new SubjectListAdapter(getContext(),subjects);
        recyclerView.setAdapter(adapter);
        return view;
    }
}