package com.example.myrecycleritems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    ArrayList<Person> people = new ArrayList<>();

    // Memory address: #44567
    private PersonListAdapter.RecyclerViewItemClickCallback callbackInActivity = new PersonListAdapter.RecyclerViewItemClickCallback() {
        @Override
        public void onClickHappened(View view) {
            // DO something
        }

        @Override
        public void onClickHappenedNow(int position) {
            // DO something
        }

        @Override
        public void onClickHappenedWithPerson(Person person) {
            // DO something
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        actionButton = findViewById(R.id.floatingActionButton);

        createPeopleList();

        PersonListAdapter personListAdapter = new PersonListAdapter(people, callbackInActivity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //GridLayoutManager linearLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(personListAdapter);

        //personListAdapter.setRecyclerItemClickCallback(callbackInActivity);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                people.add(new Person("Leyle" + " " + people.size() + 1));
                people.add(new Person("Mecnun" + " " + people.size() + 1));
                personListAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(people.size()-1);
            }
        });
    }

    public void createPeopleList() {
        for (int i = 0; i < 10; i++) {
            people.add(new Person("Omer Faruk", i));
            people.add(new Person("Hasan" , i));
            people.add(new Person("John Lennon" , i));
            people.add(new Person("Bluesss", i));
            people.add(new Person("Pink Floyd" , i));
        }
    }
}