package com.example.a43multiplefragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    private String myText;
    private TextView textView;
    private Button changeColorButton;
    private int color = R.color.black;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Retrieve your arguments starting from onCreate
        if (getArguments() != null) {
            myText = getArguments().getString("MY_STRING_KEY");
        }
        // After configuration change happens or system kills your Fragment because there
        // is no more memory
        if (savedInstanceState != null) {
            color = savedInstanceState.getInt("KEY_COLOR_STATE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.second_fragment_text);
        changeColorButton = view.findViewById(R.id.second_fragment_change_color_button);

        changeColorButton.setBackgroundColor(getResources().getColor(color));
        textView.setText(myText);
        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                color = R.color.teal_200;
                //changeColorButton.setBackgroundColor(getResources().getColor(color));

                //Create a bundle for arguments
                Bundle bundle = new Bundle();
                bundle.putString("MY_STRING_KEY","Third Fragment is initiated");

                ThirdFragment thirdFragment = ThirdFragment.newInstance("This is a new third fragment");

                //Replace with ThirdFragment
                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        //.replace(R.id.fragment_container, ThirdFragmentFragment.class, bundle)
                        .replace(R.id.fragment_container, thirdFragment)
                        .commit();

            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("KEY_COLOR_STATE",color);
    }
}