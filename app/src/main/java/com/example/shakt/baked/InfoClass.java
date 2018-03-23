package com.example.shakt.baked;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;



import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;


public class InfoClass extends Fragment {


    Button effects;
    public static final String FIREBASE_CHILD_PRODUCTS = "Products";
    Product_Class obj;
    BarChart chart;
    private LinearLayoutManager mLinearLayoutManager;
    private StorageReference mStorageRef;
    private RecyclerView rv;
    MyAdapter adapter;


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState) {
        return inflater.inflate(R.layout.infotab, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        effects = getView().findViewById(R.id.Effects);
        chart = getView().findViewById(R.id.chart);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FIREBASE_CHILD_PRODUCTS);
        rv= getView().findViewById(R.id.mRecylcerID);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);



        final ArrayList<BarEntry> barentry = new ArrayList<>();

        final ArrayList<String> ef = new ArrayList<>();
        ef.add("1");
        ef.add("2");
        ef.add("3");
        ef.add("4");
        ef.add("5");
        obj = new Product_Class();
        myRef = myRef.child("Blue Dream");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    obj = dataSnapshot.getValue(Product_Class.class);


                }
                adapter=new MyAdapter(getContext(),obj.getImage_Url());
                rv.setAdapter(adapter);

                for (int i = 0; i < obj.getEffects().size(); i++) {

                    barentry.add(new BarEntry((float) obj.getEffects().get(i), i));
                    BarDataSet set1 = new BarDataSet(barentry, "entry1");

                    BarData data = new BarData(ef, (IBarDataSet) set1);
                    chart.setData(data);
                    chart.animateXY(2000, 2000);
                    chart.invalidate();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });


    }

}

class MyHolder extends RecyclerView.ViewHolder {


    ImageView img;

    public MyHolder(View itemView) {
        super(itemView);


        img= (ImageView) itemView.findViewById(R.id.imageView);

    }
}
class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    List<String> url;

    public MyAdapter(Context c, List<String> url) {
        this.c = c;
        this.url = url;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.strain_images,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {


        PicassoClient.downloadImage(c,url.get(position),holder.img);
    }

    @Override
    public int getItemCount() {
        Log.e("TAG", String.valueOf(url.size()));
        return url.size();
    }
}

class PicassoClient {

    public static void downloadImage(Context c,String url,ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.get().load(url).placeholder(R.drawable.placeholder).into(img);
        }else {

        }
    }
}