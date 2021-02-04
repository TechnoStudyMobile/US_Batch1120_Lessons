package com.example.a46animalswithtabs.view.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a46animalswithtabs.R;
import com.example.a46animalswithtabs.data.Animal;

import java.util.ArrayList;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.PersonViewHolder> {

    private ArrayList<Animal> animals;
    private ItemViewClickListener itemViewClickListener;

    //Constructor: Pass list of animals to adapter
    public AnimalListAdapter(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    // Register the listener
    public void setItemViewClickListener(ItemViewClickListener itemViewClickListener) {
        this.itemViewClickListener = itemViewClickListener;
    }

    // In here adapter will configure which ViewHolder should be created and passed to RecyclerView
    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Will create a view using the custom layout (item_list_animal)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_animal, parent, false);
        // Need to create an instance of ViewHolder
        return new PersonViewHolder(view);
    }

    // This method will be called every-time RecyclerView is recycling its item and sets a new values
    // to it. Create a logic to set the text and images based on the positions.
    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.textView.setText(animals.get(position).getName());
        holder.imageView.setImageResource(animals.get(position).getImageId());
        Log.d("MyApp", "Binding the ViewHolder on position: " + position);
    }

    // Return the count of the items that will be in the RecyclerView
    @Override
    public int getItemCount() {
        return animals.size();
    }

    // Nested Java class. By nesting our class, we are able to access to
    // parent class variables. In this case 'itemViewClickListener'
    public class PersonViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_animal_name);
            imageView = itemView.findViewById(R.id.image_animal);
            // Set a click listener to root view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Position: " + getAdapterPosition() + " clicked.", Toast.LENGTH_SHORT).show();
                    // Trigger the callback method
                    itemViewClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    // Custom interface to create the callback functionality
    public interface ItemViewClickListener {
        void onItemClick(int position);
    }
}
