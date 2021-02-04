package com.example.myrecycleritems;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

//Outer class
public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private ArrayList<Person> people;
                    // Memory address: #46598
    private RecyclerViewItemClickCallback callback;

    public PersonListAdapter(ArrayList<Person> people, RecyclerViewItemClickCallback callback) {
        this.people = people;
        this.callback = callback;
    }

    public void setRecyclerItemClickCallback(RecyclerViewItemClickCallback recyclerItemClickCallback) {
        callback = recyclerItemClickCallback;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MYTAG","onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        // You need to bind your ViewHolder wit your data based on the position
        Log.d("MYTAG","onBindViewHolder: " + position);

        if (position == 0) {
            holder.nameTextView.setTextColor(holder.itemView.getResources().getColor(R.color.purple_700));
        } else {
            holder.nameTextView.setTextColor(holder.itemView.getResources().getColor(R.color.black));
        }

        holder.nameTextView.setText(people.get(position).fullName);
        holder.ageTextView.setText(String.valueOf(people.get(position).age));

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    //Inner Java class
    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView ageTextView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_person_name);
            ageTextView = itemView.findViewById(R.id.text_person_age);

            //Will set a clickListener to the View
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // We caught the system click. Now do the logic
                    // deletegate this responsibility to who ever created the adapter.
                    // Using callbacks
                    // position == 0
                    //Inner class can access to outer class variables.
                    callback.onClickHappenedNow(getAdapterPosition());
                    callback.onClickHappened(view);
                    callback.onClickHappenedWithPerson(people.get(getAdapterPosition()));
                    Toast.makeText(view.getContext(),"Hy there, click: " + getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface RecyclerViewItemClickCallback {
        void onClickHappened(View view);
        void onClickHappenedNow(int position);
        void onClickHappenedWithPerson(Person person);
    }
}
