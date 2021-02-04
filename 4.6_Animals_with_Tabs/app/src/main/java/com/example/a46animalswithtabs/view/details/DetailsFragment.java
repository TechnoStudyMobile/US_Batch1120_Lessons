package com.example.a46animalswithtabs.view.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.a46animalswithtabs.R;
import com.example.a46animalswithtabs.data.Animal;
import com.example.a46animalswithtabs.view.MainActivity;

import static com.example.a46animalswithtabs.view.list.ListFragment.KEY_ANIMAL_BUNDLE;
import static com.example.a46animalswithtabs.view.list.ListFragment.LIST_FRAGMENT_REQUEST_CODE;

public class DetailsFragment extends Fragment {

    private TextView titleText;
    private TextView detailsText;
    private ImageView animalImage;
    Animal animal;

    // Initial values
    private String title = "Bird";
    private String decription = "Birds are a group of warm-blooded vertebrates constituting " +
            "the class Aves /ˈeɪviːz/, characterized by feathers, toothless beaked jaws, " +
            "the laying of hard-shelled eggs, a high metabolic rate, a four-chambered heart, " +
            "and a strong yet lightweight skeleton. Birds live worldwide and range in size from the " +
            "5.5 cm (2.2 in) bee hummingbird to the 2.8 m (9 ft 2 in) ostrich. There are about ten " +
            "thousand living species, more than half of which are passerine, or \"perching\" birds. " +
            "Birds have wings whose development varies according to species; the only known groups " +
            "without wings are the extinct moa and elephant birds. Wings, which evolved from forelimbs, " +
            "gave birds the ability to fly, although further evolution has led to the loss of flight in some birds, " +
            "including ratites, penguins, and diverse endemic island species. The digestive and respiratory systems of " +
            "birds are also uniquely adapted for flight. Some bird species of aquatic environments, " +
            "particularly seabirds and some waterbirds, have further evolved for swimming.";

    private int imageID = R.drawable.picture4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState!= null) {
            animal = savedInstanceState.getParcelable("KEY_ANIMAL");
        }
        // Reference your views
        titleText = view.findViewById(R.id.fragment_details_title_text);
        detailsText = view.findViewById(R.id.fragment_details_details_text);
        animalImage = view.findViewById(R.id.fragment_details_image);

        titleText.setText(title);
        detailsText.setText(decription);
        animalImage.setImageResource(imageID);

        // Listens to the result in FragmentManager
        // If anyone sets a result using LIST_FRAGMENT_REQUEST_CODE key, we will capture it in here
        getParentFragmentManager().setFragmentResultListener(LIST_FRAGMENT_REQUEST_CODE,
                DetailsFragment.this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        //Retrieve Animal object from bundle
                        animal = bundle.getParcelable(KEY_ANIMAL_BUNDLE);
                        // Do something with the result
                        Log.d("MyApp", "Animal: " + animal.toString());
                        animalImage.setImageResource(animal.getImageId());
                        titleText.setText(animal.getName());
                        //detailsText.setText(animal.getName());
                    }
                });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("KEY_ANIMAL",animal);
    }
}