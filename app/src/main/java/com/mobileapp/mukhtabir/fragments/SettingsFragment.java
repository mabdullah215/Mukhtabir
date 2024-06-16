package com.mobileapp.mukhtabir.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.mobileapp.mukhtabir.EditProfile;
import com.mobileapp.mukhtabir.R;

public class SettingsFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_settings, container, false);
        RelativeLayout editProfile=view.findViewById(R.id.layout_edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), EditProfile.class));
                Animatoo.INSTANCE.animateSlideLeft(getActivity());
            }
        });
        return view;
    }
}