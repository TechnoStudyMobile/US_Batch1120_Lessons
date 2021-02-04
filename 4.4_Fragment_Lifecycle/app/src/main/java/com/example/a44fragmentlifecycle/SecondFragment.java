package com.example.a44fragmentlifecycle;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onViewCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onStop");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(Constants.LOG_TAG, SecondFragment.class.getName() + " is onDestroy");
    }
}