package com.example.a46animalswithtabs.view.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a46animalswithtabs.R;
import com.example.a46animalswithtabs.data.Animal;
import com.example.a46animalswithtabs.view.MainActivity;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ArrayList<Animal> animals_list = new ArrayList<>();
    private RecyclerView recyclerView;
    private AnimalListAdapter animalListAdapter;

    public static final String KEY_ANIMAL_BUNDLE = "key_position";
    public static final String LIST_FRAGMENT_REQUEST_CODE = "pass_animal_request_code";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.list_fragment_recyclerView);
        // Populate the list before passing into adapter
        populateAnimalsList();
        // Crate instance of your adapter by passing the list of animals
        animalListAdapter = new AnimalListAdapter(animals_list);
        // Set LinearLayoutManager to your adapter so it will be linear scrollable
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Finally set your adapter into your recyclerView
        recyclerView.setAdapter(animalListAdapter);

        // Callback for click events on the list's items.
        animalListAdapter.setItemViewClickListener(new AnimalListAdapter.ItemViewClickListener() {
            // Item on 'position' is clicked
            @Override
            public void onItemClick(int position) {
                // Create a bundle
                Bundle result = new Bundle();
                // We can pass instance of Animal objects because on its class file it implements Parcelable
                result.putParcelable(KEY_ANIMAL_BUNDLE, animals_list.get(position));
                //Set result using FragmentManager so other's can listen and retrieve
                getParentFragmentManager().setFragmentResult(LIST_FRAGMENT_REQUEST_CODE, result);

                // Challenge 2: When the click happens, ViewPager will be scrolled on to page 2 (DetailsFragment screen)
                // You need to scroll manually to page 2 first to make it work afterwards
                // getActivity will return parent FragmentActivity that the fragments live in.
                // We need to cast to MainActivity to access our methods.
                ((MainActivity) getActivity()).scrollViewPager(1);

            }
        });
    }

    public void populateAnimalsList() {
        String[] animals = getResources().getStringArray(R.array.animals_english);
        String[] images = getResources().getStringArray(R.array.pictures);

        for (int i = 0; i < animals.length; i++) {
            int imageId = getResources().getIdentifier(images[i], "drawable", getActivity().getPackageName());
            animals_list.add(new Animal(animals[i], imageId));
        }
    }

}