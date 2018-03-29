package com.example.shakt.baked;

import android.app.Fragment;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ReviewClass extends android.support.v4.app.Fragment {

    TextView text;
    private RecyclerView mRecyclerView;

    private DatabaseReference mDatabaseReference;

    private FirebaseRecyclerAdapter<Product_Class,ReviewViewHolder> firebaseRecyclerAdapter ;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.review, container, false);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabaseReference.keepSynced(true);

        mRecyclerView = v.findViewById(R.id.recyclerViewReviews) ;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = mDatabaseReference.orderByKey();
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Product_Class>().setQuery(query, Product_Class.class).build() ;

        firebaseRecyclerAdapter  = new FirebaseRecyclerAdapter<Product_Class,ReviewViewHolder>(options){
            @Override
            public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_card,parent,false) ;

                return new ReviewClass.ReviewViewHolder(view);

            }
            @Override
            protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull Product_Class model) {
                holder.setName( model.getproduct_info());
                holder.setReview(model.getdescription());

            }
        };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseRecyclerAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        View view;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setName(String name){
            TextView name_1  = view.findViewById(R.id.name);
            name_1.setText(name);
        }

        public void setReview (String review){
            TextView review_1 = view.findViewById(R.id.review_text);
            review_1.setText(review);
        }
    }
}
