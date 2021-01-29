package com.example.nehabeautysalon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {

    ConstraintLayout constraintLayout;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container,false);
        constraintLayout=view.findViewById(R.id.constraintLayout);


        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment;
                Bundle arguments = new Bundle();
                selectedFragment = ServicesFragment.newInstance();
                selectedFragment.setArguments(arguments);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.addToBackStack("ServicesFragment");
                transaction.replace(R.id.fragment_container, selectedFragment);
                transaction.commit();
            }
        });
        return view;
    }
}
