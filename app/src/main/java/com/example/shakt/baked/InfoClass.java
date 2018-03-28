package com.example.shakt.baked;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import net.alhazmy13.wordcloud.ColorTemplate;
import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class InfoClass extends Fragment {


    Button effects,negative,happy;
    public static final String FIREBASE_CHILD_PRODUCTS = "Products";
    Product_Class obj;
    BarChart chart;
    private LinearLayoutManager mLinearLayoutManager;
    private StorageReference mStorageRef;
    private RecyclerView rv,pv;
    MyAdapter adapter;
    List<WordCloud> list ;
    Url_Adapter urladp;


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
        negative = getView().findViewById(R.id.Negative);
        happy = getView().findViewById(R.id.Happy);
        chart = getView().findViewById(R.id.chart);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FIREBASE_CHILD_PRODUCTS);
        rv= getView().findViewById(R.id.mRecylcerID);
        pv=getView().findViewById(R.id.PostRecylcerID);
        LinearLayoutManager  layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager  layoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        pv.setLayoutManager(layoutManager2);
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
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final ArrayList<String> ef = new ArrayList<>();
                        ef.add("1");
                        ef.add("2");
                        ef.add("3");
                        ef.add("4");
                        ef.add("5");

                        new_method(obj.getNegative(),ef);

                    }
                });
                happy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final ArrayList<String> ef = new ArrayList<>();
                        ef.add("1");
                        ef.add("2");
                        ef.add("3");
                        ef.add("4");
                        ef.add("5");
                        new_method(obj.getMedical(),ef);

                    }
                });
                effects.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final ArrayList<String> ef = new ArrayList<>();
                        ef.add("1");
                        ef.add("2");
                        ef.add("3");
                        ef.add("4");
                        ef.add("5");

                        new_method(obj.getEffects(),ef);

                    }
                });

                              urladp=new Url_Adapter(obj.getWeb_url());
                rv.setAdapter(urladp);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        String text ="The Mango OG flower displays elongated and dense buds that resemble a diamond, at full maturation. Dusted in trichomes, her intoxicating flowers enjoy trippy colors with the underlying hues of yellow, lime green, and orange. Her distinctive pistils cover the Mango OG flower like spikes on a porcupine. Secreting THC-laced crystals from every calyx; Mango OG makes the connoisseur in you want to dissect her carefully with your favorite set of tweezers.";

        generateRandomText(text);
        WordCloudView wordCloud = (WordCloudView)getView().findViewById(R.id.wordCloud);

        wordCloud.setDataSet(list);
        wordCloud.setColors(ColorTemplate.MATERIAL_COLORS);
        wordCloud.setScale(50,10);
        wordCloud.notifyDataSetChanged();


    }
    private void generateRandomText(String text) {
        String[] data = text.split(" ");
        list = new ArrayList<>();
        Random random = new Random();
        for (String s : data) {
            list.add(new WordCloud(s,random.nextInt(50)));
        }
    }



    public void new_method(List<Integer> obj,ArrayList<String> ef)
    {


        final ArrayList<BarEntry> barentry = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {


            barentry.add(new BarEntry((float) obj.get(i), i));
            BarDataSet set1 = new BarDataSet(barentry, "entry1");

            BarData data = new BarData(ef, (IBarDataSet) set1);
            chart.setData(data);
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }
    }

}


