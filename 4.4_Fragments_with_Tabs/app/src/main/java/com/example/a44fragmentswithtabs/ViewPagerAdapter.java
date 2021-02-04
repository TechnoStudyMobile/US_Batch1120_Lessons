package com.example.a44fragmentswithtabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new FirstFragment();
        } else if (position == 1){
            return new SecondFragment();
        } else if (position == 2){
            return new ThirdFragment();
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
