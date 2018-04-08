# Baked
Our project aims to capitalize on the new law of marijuana legalization which allows recreational marijuana to be distributed and sold on NLSC centers throughout Canada. This law would make marijuana available to much larger audience who are unaware of the effects of taking it . We have created an app which will help specially new users to decide what kind of marijuana they can try and what all effects it could have on them. By providing all relevant information associated with marijuana and a NLSC store locator in the app we have we have made a complete platform to find, search and score marijuana.

## Libraries
Provide a list of **ALL** the libraries you used for your project.
Example:

**espresso-core:3.0.1** __________

**material-intro-screen:0.0.5**

**play-services-maps:12.0.1**

**easy-android-splash-screen:0.0.1**

**cardview-v7:26.1.0**

**firebase:12.0.1**

**MPAndroidChart:v2.2.5**

**picasso:2.71828**

**wordcloud:library:0.2.0-beta**

**glide:3.7.0**

**fancybuttons:1.8.4**


**google-gson:** Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Source [here](https://github.com/google/gson)



## Installation Notes
Installation instructions for markers.




## Code Examples
You will encounter roadblocks and problems while developing your project. Share 2-3 'problems' that your team solved while developing your project. Write a few sentences that describe your solution and provide a code snippet/block that shows your solution. Example:

**Problem 1: We needed a method to calculate a Fibonacci sequence**

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
- Feature 1 User Account (Completed)
- Feature 2 Recomendation System ( Completed)
- Feature 3 Map Functionality (Completed)

#### Expected Functionality
- Feature 1 Location based notifications (Completed)
- Feature 2 User Login (Partially Completed)
- Feature 3 Interactive Graphs (Completed)

#### Bonus Functionality
- Feature 1 name (Completed)
- Feature 2 name (Partially Completed)
- Feature 3 (Not Implemented)

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

[1] "Java (programming language)", En.wikipedia.org, 2018. [Online]. Available: https://en.wikipedia.org/wiki/Java_(programming_language).