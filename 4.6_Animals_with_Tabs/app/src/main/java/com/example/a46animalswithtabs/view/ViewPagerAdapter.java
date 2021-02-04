package com.example.a46animalswithtabs.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a46animalswithtabs.view.details.DetailsFragment;
import com.example.a46animalswithtabs.view.list.ListFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    // Constructor
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // Create a Fragment based on the position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ListFragment();
        } else if (position == 1) {
            return new DetailsFragment();
        } else {
            return null;
        }
    }

    // Will return the amount of the Fragment that ViewPager will hold
    @Override
    public int getItemCount() {
        return 2;
    }
}
