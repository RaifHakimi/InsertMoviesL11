package sg.edu.rp.c346.id22016635.insertmoviesl11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

// INSERT MOVIE
public class MainActivity extends AppCompatActivity {

    EditText title,genre,year;
    Spinner rating;
    Button insert,showlist;
    String ratings = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.etTitle);
        genre = findViewById(R.id.etGenre);
        year = findViewById(R.id.etYear);
        rating = findViewById(R.id.spinner);
        insert = findViewById(R.id.btnInsert);
        showlist = findViewById(R.id.btnShowlist);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.movieRatings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating.setAdapter(adapter);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertMovie(title.getText().toString(),genre.getText().toString(),Integer.parseInt(year.getText().toString()),ratings);
                db.close();
                Toast.makeText(MainActivity.this,"Insert Successful!",Toast.LENGTH_SHORT).show();
            }
        });

        showlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondPage = new Intent(MainActivity.this,ShowMovies.class);
                startActivity(secondPage);
            }
        });




        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        ratings = "G";
                        break;
                    case 1:
                        ratings = "PG";
                        break;
                    case 2:
                        ratings = "PG13";
                        break;
                    case 3:
                        ratings = "NC16";
                        break;
                    case 4:
                        ratings = "M18";
                        break;
                    case 5:
                        ratings = "R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}