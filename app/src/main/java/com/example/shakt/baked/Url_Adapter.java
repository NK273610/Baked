package com.example.shakt.baked;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;


/**
 * Created by nikhildhirmalani on 26/03/18.
 */

public class Url_Adapter extends RecyclerView.Adapter<Url_Adapter.MyViewHolder> {

    private List<String> dataSet; //list of string with all url for posts
    Context c;

    public Url_Adapter(List<String> data) {
        this.dataSet = data; //url adapter constructor
        this.c=c;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener { //Myview holder class

        WebView wv;
         FancyButton explore;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.wv = (WebView) itemView.findViewById(R.id.webview); //webview id taken
            this.explore=(FancyButton) itemView.findViewById(R.id.explore);//fancy button just to ecplore website
            explore.setOnClickListener(this); //onclick listner
        }


        @Override
        public void onClick(View view) {
            String x=wv.getOriginalUrl(); //we take the url
            Intent intent = new Intent(itemView.getContext(),PostView.class);//intent used to go to this activity to show url
            intent.putExtra("url", x);
            itemView.getContext().startActivity(intent);// start activity this code is to send data from fragment to activity
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()) //we inflate the posts_strain xml
                .inflate(R.layout.posts_strain, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        WebView webview = holder.wv; //we take the webview
        WebSettings webSetting = webview.getSettings();
        webSetting.setJavaScriptEnabled(true);//set the webview with the url
        webSetting.setDisplayZoomControls(true);
        Url_Load.downloadUrl(c,dataSet.get(listPosition),webview);//we call the given method download url

        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(c,"hello",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

class Url_Load {

    public static void downloadUrl(Context c,String url,WebView wv) //download url method created
    {
        if(url != null && url.length()>0)
        {
            wv.setWebViewClient(new WebViewClient()); //we set the webview client
            wv.loadUrl(url);
        }else {

        }
    }
}

class MyWebViewClient extends WebViewClient { //mywebview client class created
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (Uri.parse(url).getHost().equals(url)) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); //we start the intent with the data
        view.getContext().startActivity(intent);
        return true;
    }
}


