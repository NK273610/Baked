# Baked
"Baked" application is built to provide an insight into a variety of strains of marijuana. It can be used by a user who has no prior knowledge about it. It also recommends the users about a strain which may be helpful to them as per their needs. The recommendation system is mostly useful to a new user who is not aware of the impact a given type of strain can cause. It also explains the effects of various strains with different forms of visualizations. One of the amazing features of the app is that it has map functionality which helps the user identify different stores which are selling a variety of strains. The app allows users to log in to their account using Gmail to set their preferences or use the app anonymously.

Our project aims to capitalize on the new law of marijuana legalization which allows recreational marijuana to be distributed and sold on NLSC centers throughout Canada. This law would make marijuana available to much larger audience who are unaware of the effects of taking it . We have created an app which will help specially new users to decide what kind of marijuana they can try and what all effects it could have on them. By providing all relevant information associated with marijuana and a NLSC store locator in the app we have we have made a complete platform to find, search and score marijuana.

## Libraries
Provide a list of **ALL** the libraries you used for your project.

Example:

**google-gson:** Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Source [here](https://github.com/google/gson)

**espresso-core:3.0.1** 

**material-intro-screen:0.0.5**

**play-services-maps:12.0.1** Google maps API for android is used to implement and use Google maps on mobile devices. it comes with a lot of features related to maps and is easy to implement too. Source [here](https://developers.google.com/maps/documentation/android-api/)

**easy-android-splash-screen:0.0.1**

**cardview-v7:26.1.0**

**firebase:12.0.1**

**MPAndroidChart:v2.2.5**

**picasso:2.71828**

**wordcloud:library:0.2.0-beta**

**glide:3.7.0**

**fancybuttons:1.8.4**



## Installation Notes
1. Install the application as ususal from the apk or zip file. 
2. Upon your first run of the application, you will be walked through several screens giving an idea about the application. On third screen you will be asked to grant permissions to the app (eg. acccess location).
3. After a splash screen with Baked logo on it, you will be redirected to the Home page of the application. The home page conatins two tabs on it one for recommending the products and other for accessing the store locator. 
4. The recommendation tab...
5. A simple tap anywhere on Map tab will take you to the 




## Code Examples
You will encounter roadblocks and problems while developing your project. Share 2-3 'problems' that your team solved while developing your project. Write a few sentences that describe your solution and provide a code snippet/block that shows your solution. Example:

**Problem 1: Making custom adapters on customize views on each list**

To customize the view on each list , we had to learn how to make custom adapters.
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
Source: https://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial

**Problem 2: Using Picaso to download url and set image to imageview**

We have used Picaso to load images from url to the imageview.
```
ImageView img;
public static void downloadImage(Context c,String url,ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.get().load(url).placeholder(R.drawable.placeholder).into(img);// picasso class
        }else {

        }
    }
```

**Problem 3: Attaching Srore markers to theri corresponding vicinity cards**

While trying to implement the RecyclerView on the Map fragment in MapActivity, we faced a problem with attaching the markers of the stores to their corresponding vicinity cards. We solved this problem by implementing onMarkerClickListener().

```
 mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                final LatLng markerPosition = marker.getPosition();
                int selected_marker = -1;
                for (int i = 0; i < nearByPlaceList.size(); i++) {
                    LatLng latLng = new LatLng(Double.parseDouble(nearByPlaceList.get(i).get("lat")),
                                                Double.parseDouble(nearByPlaceList.get(i).get("lng")));
                    if (markerPosition.latitude == latLng.latitude && markerPosition.longitude == latLng.longitude) {
                        selected_marker = i;
                    }
                }

                CameraPosition cameraPosition = new CameraPosition.Builder().target(markerPosition).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(selected_marker);

                marker.showInfoWindow();


                return false;
            }
        });
 ```
 Source: [here](https://stackoverflow.com/questions/37058897/connect-cards-of-recyclerview-to-marker-in-google-maps)


## Feature Section
List all the main features of your application with a brief description of each feature.
1. *Store Locator*:
2. *Search Bar* : Using this bar one can easily filter out a particular product they want information of.
3. *Recommendation System*:
4. *Product Info*:
5. *User Account*:


## Final Project Status
Write a description of the final status of the project. Did you achieve all your goals? What would be the next step for this project (if it were to continue)?

#### Minimum Functionality
- Login (Completed)
- Recommendation Tab (Completed)
- Product Search (Completed)

#### Expected Functionality
- Store locator (Completed)
- Product Review and Rating (Completed)
- Location based notifications (Completed)

#### Bonus Functionality
- Intro Activity and splash screen (Completed)
- Store Ranking (Completed)


## Sources
Use IEEE citation style.
What to include in your project sources:
- Stock images
- Design guides
- Programming tutorials
- Research material
- Android libraries
- Everything listed on the Dalhousie [*Plagiarism and Cheating*](https://www.dal.ca/dept/university_secretariat/academic-integrity/plagiarism-cheating.html)
- Remember AC/DC *Always Cite / Don't Cheat* (see Lecture 0 for more info)

[1] "pantrif/EasySplashScreen",En.github.com, 2018.
[Online]. Available:
https://github.com/pantrif/EasySplashScreen

[2] "TangoAgency/material-intro-screen",En.github.com, 2018.
[Online]. Available:
https://github.com/TangoAgency/material-intro-screen

[3] "alhazmy13/AndroidWordCloud", En.github.com, 2018.
[Online]. Available:
https://github.com/alhazmy13/AndroidWordCloud

[4] "PhilJay/MPAndroidChart", En.github.com, 2018.
[Online]. Available:
https://github.com/PhilJay/MPAndroidChart

