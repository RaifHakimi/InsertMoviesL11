package sg.edu.rp.c346.id22016635.insertmoviesl11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {

    CustomAdapter adapter;
    ListView lv;

    Spinner filterSpinner;

    Button filterRate;

    ArrayList<Movies> al;
    @Override
    protected void onResume() {
        super.onResume();
        al = new ArrayList<Movies>();
        adapter = new CustomAdapter(this,R.layout.row,al);
        lv.setAdapter(adapter);
        Intent i = getIntent();
        DBHelper db = new DBHelper(ShowMovies.this);
        al.clear();
        al.addAll(db.getMovies());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);
        lv = findViewById(R.id.listViewSHOW);
        filterSpinner = findViewById(R.id.ratingFilter);
        filterRate = findViewById(R.id.filter);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(ShowMovies.this, R.array.movieRatings, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies data = al.get(position);
                Intent i = new Intent(ShowMovies.this,modifyMovies.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

        filterRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowMovies.this);
                al.clear();

                al.addAll(dbh.filterMovies(filterSpinner.getSelectedItem().toString()));
                adapter.notifyDataSetChanged();
            }
        });
    }

}