package com.example.a44fragmentlifecycle;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment {

    private Button navigateButton;

    public FirstFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigateButton = view.findViewById(R.id.first_fragment_navigate);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .replace(R.id.fragment_container, SecondFragment.class,null)
                .commit();
            }
        });

        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onViewCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.LOG_TAG, FirstFragment.class.getName() + " is onDestroy");
    }
}