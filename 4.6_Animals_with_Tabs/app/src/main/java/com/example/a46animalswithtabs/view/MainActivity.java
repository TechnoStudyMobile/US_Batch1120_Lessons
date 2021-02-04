package com.example.a46animalswithtabs.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a46animalswithtabs.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends FragmentActivity {

    private ViewPager2 viewPager;
    private TabLayout tabs;
    private String[] names = {"Main", "Details"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference your views
        viewPager = findViewById(R.id.main_activity_view_pager);
        tabs = findViewById(R.id.main_activity_tabs);

        // Create an instance of your adapter and set it to Viewpager
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Configure your TabLayout (Title section on the top of the screen)
        new TabLayoutMediator(tabs, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // Configure each tab based on their position
                tab.setText(names[position]);
            }
        }).attach();
    }

    public void scrollViewPager(int position) {
        viewPager.setCurrentItem(position);
    }
}