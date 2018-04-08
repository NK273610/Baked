package com.example.shakt.baked;

/**
 * Created by nikhildhirmalani on 16/03/18.
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import net.alhazmy13.wordcloud.ColorTemplate;
import net.alhazmy13.wordcloud.WordCloud;
import net.alhazmy13.wordcloud.WordCloudView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;


public class InfoClass extends Fragment {

    //Declaring sll the variables used in this class
    FancyButton effects,negative,happy; //buttons
    public static final String FIREBASE_CHILD_PRODUCTS = "Products";//firebase child variable
    Product_Class Data_obj;//product class object
    BarChart barchart;//barchart obj
    private LinearLayoutManager mLinearLayoutManager;
    private StorageReference mStorageRef;
    private RecyclerView image_recyclerview, post_rrecyclerview;//recyclerview object
    MyAdapter adapter;
    List<WordCloud> word_list ;//list of word clouds
    Url_Adapter urladp;//urladapter object created
    PieChart pieChart ;
    public String strain_value;
    TextView descrip_text;




    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle saveInstanceState) {
        Bundle bundle = this.getArguments();//take the strain value from any class
        if (bundle.containsKey("strain")){//if it contains key for strain
            strain_value= bundle.getString("strain");
        }
        if (bundle.containsKey("strainName")){//if it contains key for strain name
            strain_value= bundle.getString("strainName");
        }
        if (bundle.containsKey("favorite")){//if it contains key for favourite
            strain_value= bundle.getString("favorite");

        }

        return inflater.inflate(R.layout.infotab, container, false);//we will inflate our page
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //All variables declared above are initialised here with their values
        effects = (FancyButton) getView().findViewById(R.id.Effects);
        negative = (FancyButton)getView().findViewById(R.id.Negative);
        happy = (FancyButton)getView().findViewById(R.id.Happy);
        descrip_text=getView().findViewById(R.id.textdesc);
        barchart = getView().findViewById(R.id.chart);
        pieChart=getView().findViewById(R.id.chart1);
        pieChart.setUsePercentValues(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();//Firebase object created
        DatabaseReference myRef = database.getReference(FIREBASE_CHILD_PRODUCTS);//we get reference to our database name name to firebase object
        image_recyclerview = getView().findViewById(R.id.mRecylcerID);
        post_rrecyclerview =getView().findViewById(R.id.PostRecylcerID);

        LinearLayoutManager  layoutManager//linear layout manager created for activity with horizontal layout
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager  layoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        image_recyclerview.setLayoutManager(layoutManager);//image and post recyclerviewer is given
        post_rrecyclerview.setLayoutManager(layoutManager2);//both layout managers declared
        Data_obj = new Product_Class();

        myRef = myRef.child(strain_value);//firebase reference to child
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                    Data_obj = dataSnapshot.getValue(Product_Class.class);//all data taken in object class

                }
                descrip_text.setText(Data_obj.getDescription());//we set the text value
                effects.setPressed(true);
                adapter=new MyAdapter(getContext(),Data_obj.getImageUrl());//Myadapter object created for images
                image_recyclerview.setAdapter(adapter);//we set image recyclerviewer to given adapter
                getEffects(Data_obj);//get Effects method called

                getPie_method(Data_obj.getThcCbd());
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        getNegative(Data_obj);//get Negative method called

                    }
                });
                happy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                   getMedical(Data_obj);//get medical method called


                    }
                });
                effects.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       getEffects(Data_obj);//get Effcts method called

                    }
                });

                              urladp=new Url_Adapter(Data_obj.getWebUrl());//Url Adapter created
                              post_rrecyclerview.setAdapter(urladp);//post recyclerview set to this adapter


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        String text ="The Mango OG flower displays elongated and dense buds that resemble a diamond, at full maturation. Dusted in trichomes, her intoxicating flowers enjoy trippy colors with the underlying hues of yellow, lime green, and orange. Her distinctive pistils cover the Mango OG flower like spikes on a porcupine. Secreting THC-laced crystals from every calyx; Mango OG makes the connoisseur in you want to dissect her carefully with your favorite set of tweezers.";

        generateRandomText(text);//generate random text method called
        WordCloudView wordCloud = (WordCloudView)getView().findViewById(R.id.wordCloud);

        wordCloud.setDataSet(word_list);
        wordCloud.setColors(ColorTemplate.MATERIAL_COLORS);
        wordCloud.setScale(50,10);
        wordCloud.notifyDataSetChanged();


    }

    //created word cloud using this livbrary https://github.com/alhazmy13/AndroidWordCloud
    //the code was taken from this link on igthub to created word cloud
    private void generateRandomText(String text) {
        String[] data = text.split(" ");
        word_list = new ArrayList<>();
        Random random = new Random();
        for (String s : data) {
            word_list.add(new WordCloud(s,random.nextInt(50)));
        }
    }

    public void getEffects(Product_Class Data_obj)//get effects method
    {
        final ArrayList<String> effects_type = new ArrayList<>();
        effects_type.add("Relaxed");
        effects_type.add("Happy");
        effects_type.add("Euphoric");
        effects_type.add("Uplifted");
        effects_type.add("Giggly");
        getBar_method(Data_obj.getEffects(),effects_type);
    }

    public void getNegative(Product_Class Data_obj)
    {final ArrayList<String> negative_values = new ArrayList<>();
        negative_values.add("Dry Mouth");
        negative_values.add("Dry eyes");
        negative_values.add("Anxious");
        negative_values.add("Paranoid");
        negative_values.add("Headache");
        getBar_method(Data_obj.getNegative(),negative_values);
    }

    public void getMedical(Product_Class Data_obj)
    {
        final ArrayList<String> ef = new ArrayList<>();
        ef.add("Stress");
        ef.add("Depression");
        ef.add("Pain");
        ef.add("Insomnia");
        ef.add("Lack of Appetite");
        getBar_method(Data_obj.getMedical(),ef);
    }

    public void getBar_method(List<Integer> Data_obj, ArrayList<String> ef)
    {


        final ArrayList<BarEntry> barentry = new ArrayList<>();
        for (int i = 0; i < Data_obj.size(); i++) {

            barentry.add(new BarEntry((float) Data_obj.get(i), i));
            BarDataSet set1 = new BarDataSet(barentry, "entry1");

            BarData data = new BarData(ef, (IBarDataSet) set1);
            barchart.setData(data);
            barchart.animateXY(2000, 2000);
            barchart.invalidate();
        }
    }
    public void getPie_method(List<Integer> Data_obj)
    {
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        for (int i = 0; i < Data_obj.size(); i++) {

            yvalues.add(new Entry(Data_obj.get(i), i));

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


