package com.example.shakt.baked;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import net.alhazmy13.wordcloud.ColorTemplate;
import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;


public class InfoClass extends Fragment {


    FancyButton effects,negative,happy;
    public static final String FIREBASE_CHILD_PRODUCTS = "Products";
    Product_Class obj;
    BarChart chart;
    private LinearLayoutManager mLinearLayoutManager;
    private StorageReference mStorageRef;
    private RecyclerView rv,pv;
    MyAdapter adapter;
    List<WordCloud> list ;
    Url_Adapter urladp;
    PieChart pieChart ;
    public String value;
    TextView text;




    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle.containsKey("strain")){
            value= bundle.getString("strain");
        }
        if (bundle.containsKey("strainName")){
            value= bundle.getString("strainName");
        }
        if (bundle.containsKey("favorite")){
            value= bundle.getString("favorite");

        }

        return inflater.inflate(R.layout.infotab, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        effects = (FancyButton) getView().findViewById(R.id.Effects);
        negative = (FancyButton)getView().findViewById(R.id.Negative);
        happy = (FancyButton)getView().findViewById(R.id.Happy);
        text=getView().findViewById(R.id.textdesc);
        chart = getView().findViewById(R.id.chart);
        pieChart=getView().findViewById(R.id.chart1);
        pieChart.setUsePercentValues(true);


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
        myRef = myRef.child(value);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    obj = dataSnapshot.getValue(Product_Class.class);

                }
                text.setText(obj.getDescription());
                effects.setPressed(true);
                adapter=new MyAdapter(getContext(),obj.getImageUrl());
                rv.setAdapter(adapter);
                getEffects(obj);

                new_method2(obj.getThcCbd());
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        getNegative(obj);

                    }
                });
                happy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                   getMedical(obj);


                    }
                });
                effects.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       getEffects(obj);

                    }
                });

                              urladp=new Url_Adapter(obj.getWebUrl());
                              pv.setAdapter(urladp);


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

    public void getEffects(Product_Class obj)
    {
        final ArrayList<String> ef = new ArrayList<>();
        ef.add("Relaxed");
        ef.add("Happy");
        ef.add("Euphoric");
        ef.add("Uplifted");
        ef.add("Giggly");
        new_method(obj.getEffects(),ef);
    }

    public void getNegative(Product_Class obj)
    {final ArrayList<String> ef = new ArrayList<>();
        ef.add("Dry Mouth");
        ef.add("Dry eyes");
        ef.add("Anxious");
        ef.add("Paranoid");
        ef.add("Headache");
        new_method(obj.getNegative(),ef);
    }

    public void getMedical(Product_Class obj)
    {
        final ArrayList<String> ef = new ArrayList<>();
        ef.add("Stress");
        ef.add("Depression");
        ef.add("Pain");
        ef.add("Insomnia");
        ef.add("Lack of Appetite");
        new_method(obj.getMedical(),ef);
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
    public void new_method2(List<Integer> obj)
    {
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        for (int i = 0; i < obj.size(); i++) {

            yvalues.add(new Entry(obj.get(i), i));

        }
        PieDataSet dataSet = new PieDataSet(yvalues, "THC-CBD ratio");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("THC");
        xVals.add("CBD");
        PieData data = new PieData(xVals, dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.setDescription("This is Pie Chart");

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.animateXY(1400, 1400);
    }

}


