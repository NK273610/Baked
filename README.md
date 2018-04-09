# Baked
"Baked" application is built to provide an insight into a variety of strains of marijuana. It can be used by a user who has no prior knowledge about it. It can also recommend strains for the user according to their needs. The recommendation system is useful for a new user who is not aware of the effects a given type of strain has. It also explains the effects of various strains with meaningful graphs displaying the type effects it produces. In addition, the map functionality helps the user identify and locate the store nearest to them. The app allows users to log in to their account using Gmail to set their preferences or use the app anonymously.

Our project aims to capitalize on the new law of marijuana legalization which allows recreational marijuana to be distributed and sold on NLSC centers throughout Canada. This law would make marijuana available to a much larger audience who are unaware of its effects. Our app wishes to guide the new users to help them decide what kind of marijuana strain they should try based on their preferences. The app will also be appreciated by veteran users, and provide them a platform to share their knowledge and expertise.

## Libraries
Provide a list of **ALL** the libraries you used for your project.

**google-gson:** Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Source [here](https://github.com/google/gson)

**espresso-core:3.0.1** 

**material-intro-screen:0.0.5**

**play-services-maps:12.0.1** Google maps API for Android is used to implement and use Google maps on mobile devices. it comes with a lot of features related to maps and is easy to implement too. Source [here](https://developers.google.com/maps/documentation/android-api/)

**easy-android-splash-screen:0.0.1**Easy splash screen library allows you to generate beautiful and customizable Splash screens with less effort. Source [here](https://android-arsenal.com/details/1/3514)

**cardview-v7:26.1.0** The main purpose CardView serves in the Material Design universe is to provide a unified look to all of the card based UIs making it easier for developers to create seamless interfaces.                                                        Source [here](https://developer.android.com/reference/android/support/v7/widget/CardView.html)

**firebase:12.0.1** FirebaseUI is an open-source library for Android that allows you to quickly connect common UI elements to Firebase APIs.FirebaseUI has separate modules for using Firebase Real-time Database, Cloud Firestore, Firebase Auth, and Cloud Storage.
Source[here](https://github.com/firebase/FirebaseUI-Android)

**MPAndroidChart:v2.2.5** MPAndroidChart is a powerful & easy to use chart library for Android.                                        Source [here](https://github.com/PhilJay/MPAndroidChart)

**picasso:2.71828** Picasso is a powerful image downloading and caching library for Android.Source [here](https://github.com/square/picasso)

**wordcloud:library:0.2.0-beta** Word cloud gives a graphical representations of word frequency that give greater prominence to words that appear more frequently in a source text.Source [here](https://github.com/alhazmy13/AndroidWordCloud)

**glide:3.7.0** Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.Source [here](https://github.com/bumptech/glide)

**fancybuttons:1.8.4** Icons, Borders, Radius for Android buttons.Source [here](https://github.com/medyo/Fancybuttons)



## Installation Notes

1. Install the application as ususal from the apk or zip file.
2. Upon your first run of the application, you will be walked through several screens giving an idea about the application. On third screen you will be asked to grant permissions to the app (eg. acccess location).
3. After a splash screen with Baked logo on it, you will be redirected to the Home page of the application. The home page conatins two tabs on it one for recommending the products and other for accessing the store locator.
4. The recommendation tab have buttons that wil take you to the list of products corresponding to that class of products. (for eg. Brain  section will take you to the Cannabis products which are responsible for enhancing Brain activity.)
5. A simple tap anywhere on Map tab will take you to the Map Activity where you can use the store locator functionlaity. The default location is set to your current location with a green marker on it. Upon tapping the Find Stores button, The map will show nearby stores with a NSLC marker on them (As we all know that the distribution of Cannabis will be controlled by government) source.
6. Upon tapping the store markers, the vivinity of the store will be shown in the cards on top of the map and in marker's title. Back 7. action on this activity will take you to the Home activity.
7. You can also access side navigation drawer on the Home acivity. It contain links to The User account page, Login Page and map page.
8. User account link will take you to the account page where the user can see their reviews, Favorites and Order history.
9. Search button can be located on the Home page top right corner and can be used to search the directory of available products.
10. The Info page of a product can be accessed form the list of products after recommendation , or by performing a search from Home page.
11. The Info page conatins all the information about the product, its composition, pictures and link to the reviews. The reviews can be accessed at the bottom of the info page in a horizontal swipe card view.




## Code Examples
You will encounter roadblocks and problems while developing your project. Share 2-3 'problems' that your team solved while developing your project. Write a few sentences that describe your solution and provide a code snippet/block that shows your solution. Example:

**Problem 1: Making custom adapters to customize views on each list**

To customize the view on each list, we had to learn how to make custom adapters.
```
// The class we made for one of our custom lists
    class CustomListadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return (int)(favourites.size()/2);
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int name_position;
            int description_position;
            name_position = 2*position;
            description_position = name_position+1;
            convertView = getLayoutInflater().inflate(R.layout.customlayoutratings,null);
            ImageView img = convertView.findViewById(R.id.product_pic);
            TextView product_name = convertView.findViewById(R.id.product_name);
            RatingBar ratingBar = convertView.findViewById(R.id.ratingBar);
            img.setImageResource(imagearray[position]);
            product_name.setText(favourites.get(name_position));
            ratingBar.setRating(Integer.parseInt(favourites.get(description_position)));

            return convertView;
        }
    }
}
```
**Problem 2: Create Bar chart and Pie Chart from data**

We have used MPAndroid chart library to created bar chart and Pie Chart from the data array
```
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
```
Source: https://github.com/PhilJay/MPAndroidChart

**Problem 3: Attaching store markers to their corresponding vicinity cards**

While implementing cardView on the map activity, we found it challenging to attach a selected marker to its corresponding vicinity card. We used setOnMarkerClickListener() to implement that with some help from the mentioned source.

```
        // Listener for the map markers to attach them to vicinity cards
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                final LatLng markerPosition = marker.getPosition();
                int selected_marker = -1;

                // getting the position of selected marker
                for (int i = 0; i < nearByPlaceList.size(); i++) {
                    LatLng latLng = new LatLng(Double.parseDouble(nearByPlaceList.get(i).get("lat")),
                                                Double.parseDouble(nearByPlaceList.get(i).get("lng")));
                    if (markerPosition.latitude == latLng.latitude && markerPosition.longitude == latLng.longitude) {
                        selected_marker = i;
                    }
                }

                // Move camera and show corresponding card in front
                CameraPosition cameraPosition = new CameraPosition.Builder().target(markerPosition).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(selected_marker);

                marker.showInfoWindow();


                return false;
            }
        });
```
Source : [here](https://stackoverflow.com/questions/37058897/connect-cards-of-recyclerview-to-marker-in-google-maps)

**Problem 4: Show webview of url list inside carview**

While implementing cardView on the info activity, we found it challenging to attach all the webviews generated from the url string to cardview. We took use of webview client in order to solve it.

```
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

```
Source : https://stackoverflow.com/questions/6850017/how-to-set-webview-client

## Feature Section
List all the main features of your application with a brief description of each feature.
1. *Store Locator*:  Users can locate the stores nearby through their current location and can see the vicinity of the individual stores in the cards.
2. *Search Bar*: Using this bar one can conveniently search the database for the information of the product they want. Will be useful when the database size increase.
3. *Recommendation System*: An interactive screen which allows the user to choose whether they want a brain or body high.This feature will particularly be useful for beginners. 
4. *Product Info*: The info page shows all the information of selected strain. It shows the effects (positive, negative and medical), images, posts, description and wordcloud of selected strain. We have created the word cloud from the text in the description. But in future, we will apply TFIDF ML algorithm to find the most frequent word from reviews of a particular strain and display that words in a word cloud. 
5. *User Account*: This tab displays info of the logged in user. This includes personal info of the user and information related to activities on the app, such as the number of reviews posted and following.


## Final Project Status
Write a description of the final status of the project. Did you achieve all your goals? What would be the next step for this project (if it were to continue)?

#### Minimum Functionality
- Login (Completed)
- Recommendation Tab (Completed)
- Product Search (Completed)

#### Expected Functionality
- Store locator (Completed)
- Product Review and Rating (Completed)
- Location-based notifications (Completed)

#### Bonus Functionality
- Intro Activity and splash screen (Completed)
- Store Ranking (Completed)




**Design guides**
[1] "pantrif/EasySplashScreen",En.github.com, 2018. [Online]. Available: https://github.com/pantrif/EasySplashScreen

[2] "TangoAgency/material-intro-screen",En.github.com, 2018. [Online]. Available:
https://github.com/TangoAgency/material-intro-screen

[3] "alhazmy13/AndroidWordCloud", En.github.com, 2018. [Online]. Available: 
https://github.com/alhazmy13/AndroidWordCloud

[4] "medyo/Fancybuttons", En.github.com, 2018. [Online]. Available: 
https://github.com/medyo/Fancybuttons


**Android Libraries**


[1] "PhilJay/MPAndroidChart", En.github.com, 2018. [Online]. Available: 
https://github.com/PhilJay/MPAndroidChart

[2] "wordcloud:library:0.2.0-beta", En.github.com, 2017. [Online]. Available:
https://github.com/alhazmy13/AndroidWordCloud

[3] "glide:3.7.0", En.github.com, 2017. [Online]. Available:
https://github.com/bumptech/glide

[4] "fancybuttons:1.8.4", En.github.com, 2017. [Online]. Available:
https://github.com/medyo/Fancybuttons

[5] "google-gson:", En.github.com, 2016. [Online]. Available:
https://github.com/google/gson

[6] "fancybuttons:1.8.4", En.github.com, 2017. [Online]. Available:
https://github.com/medyo/Fancybuttons


**Programming tutorials**

[1]"GoogleMapsNearbyPlacesDemo", GitHub, 2017. [Online]. Available: https://github.com/priyankapakhale/GoogleMapsNearbyPlacesDemo.




**Stock Images**


[1] "Blue Dream" . Retrieved from 
http://lotuslandclub.com/product/sativa-blue-dream/

[2] "OG KuSh" . Retrieved from 
http://lotuslandclub.com/product/og-kush/

[3] "Acapulco Gold". Retrieved from 
http://lotuslandclub.com/product/Acapulo-Gold/

[4] "Ghost Train Haze". Retrieved from 
http://lotuslandclub.com/product/Ghost-Train-Haze/

[5] "Great White Shark". Retrieved from 
http://lotuslandclub.com/product/Great-White-Shark/

[6] "Afgoo". Retrieved from
https://www.leafly.com/sativa/Afgoo

[7] "Headband". Retrieved from
https://www.leafly.com/sativa/Headband

[8] "LSD". Retrieved from
https://www.leafly.com/sativa/LSD

[9] "Lavender". Retrieved from
https://www.leafly.com/sativa/Lavender

[10] "Obama Kush". Retrieved from
https://www.leafly.com/sativa/Obama-Kush

**Research material**

[1] Information about product gathered from 
https://www.leafly.com/

[2]Medical Information about the product gathered from
https://www.health.harvard.edu/blog/medical-marijuana-2018011513085

