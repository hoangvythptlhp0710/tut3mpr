package com.asm1.tut5.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asm1.tut5.R;
import com.asm1.tut5.models.Friend;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder>{

    //dataset
    private List<Friend> friends;

    public FriendAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate item view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_friend, parent, false);

        //return view holder
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        //get data item from dataset at position
        Friend friend = friends.get(position);

        //bind data to view holder
        holder.bind(friend);

    }


    public class FriendHolder extends RecyclerView.ViewHolder {


        public FriendHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Friend friend) {
            //bind data to view
            TextView txName = itemView.findViewById(R.id.textView);
            txName.setText(friend.getName());
        }
    }
}
