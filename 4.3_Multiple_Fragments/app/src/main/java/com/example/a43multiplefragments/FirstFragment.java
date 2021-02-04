package com.example.a43multiplefragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment {

    private Button button_navigate;

    //Lifecycle event
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment (Creates the View)
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    //LifeCycle event
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Reference to your views on onViewCreated
        button_navigate = view.findViewById(R.id.first_fragment_button);
        button_navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a bundle for arguments
                Bundle bundle = new Bundle();
                bundle.putString("MY_STRING_KEY","My Second Fragment is created with arguments");

                ((MainActivity) getActivity()).gotoAnotherActibvity();

                // Navigate to SecondFragment
                getParentFragmentManager()
                        .beginTransaction()
                        //To enable backstacking mechanism
                        .setReorderingAllowed(true)
                        //Add to backstack
                        .addToBackStack(null)
                        //removes First Fragment
                        //Adds second fragment
                        .replace(R.id.fragment_container, SecondFragment.class, null)
                        .commit();
            }
        });
    }

    public void gotoAnotherActibvity(){

    }
}