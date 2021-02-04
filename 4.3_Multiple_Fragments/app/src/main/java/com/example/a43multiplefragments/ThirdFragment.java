package com.example.a43multiplefragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private TextView textViewTitle;

    // The key for passing argument
    private static final String MY_TEXT_KEY = "my_key_text_second_fragment";
    private String myString;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param param1 Parameter 1 is required to populate the Main text.
     * @return A new instance of fragment ThirdFragmentFragment.
     */
    public static ThirdFragment newInstance(String param1) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(MY_TEXT_KEY, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //myString = getArguments().getString("MY_STRING_KEY");
            myString = getArguments().getString(MY_TEXT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewTitle = view.findViewById(R.id.third_fragment_text_view);
        textViewTitle.setText(myString);
    }
}