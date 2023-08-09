package sg.edu.rp.c346.id22016635.insertmoviesl11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movies> movieList;



    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects){
        super(context,resource,objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.movieList = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id,parent,false);

        TextView tvTitle = rowView.findViewById(R.id.showTitle);
        TextView tvGenre = rowView.findViewById(R.id.showGenre);
        TextView tvYear = rowView.findViewById(R.id.showYear);
        ImageView ivRating = rowView.findViewById(R.id.imageView);

        Movies currentItem =  movieList.get(position);

        tvTitle.setText(currentItem.getTitle());
        tvGenre.setText("Genre: " + currentItem.getGenre());
        tvYear.setText(currentItem.getYear() + "");

        if(currentItem.getRating().equalsIgnoreCase("G")){
            ivRating.setImageResource(R.drawable.rating_g);
        }else if(currentItem.getRating().equalsIgnoreCase("PG")){
            ivRating.setImageResource(R.drawable.rating_pg);
        }else if(currentItem.getRating().equalsIgnoreCase("PG13")){
            ivRating.setImageResource(R.drawable.rating_pg13);
        }else if(currentItem.getRating().equalsIgnoreCase("NC16")){
            ivRating.setImageResource(R.drawable.rating_nc16);
        }else if(currentItem.getRating().equalsIgnoreCase("M18")){
            ivRating.setImageResource(R.drawable.rating_m18);
        }else{
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        //ivRating.setImageResource(R.drawable.rating_g);


        //Code to Insert Image from Web (Requires permission for Wifi)
        String imageUrl = "https://i.imgur.com/tGbaZCY.jpg";
        //Picasso.with(parent_context).load(imageUrl).into(ivGender);

        /*if(currentItem.getGender() == 'M'){
            ivGender.setImageResource(R.drawable.male);
        } else {
            ivGender.setImageResource(R.drawable.female);
        }*/

        return rowView;
    }
}

