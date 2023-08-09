package sg.edu.rp.c346.id22016635.insertmoviesl11;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class modifyMovies extends AppCompatActivity {


    EditText modId, modTitle, modGenre, modYear;
    Spinner modRating;
    Button btnUpdate, btnDelete, btnCancel;

    Movies data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movies);
        modId = findViewById(R.id.modId);
        modTitle = findViewById(R.id.modTitle);
        modGenre = findViewById(R.id.modGenre);
        modYear = findViewById(R.id.modYear);
        modRating = findViewById(R.id.modSpinner);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(modifyMovies.this, R.array.movieRatings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modRating.setAdapter(adapter);
        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");
        modId.setText(String.valueOf(data.getId()));
        modTitle.setText(String.valueOf(data.getTitle()));
        modGenre.setText(String.valueOf(data.getGenre()));
        modYear.setText(String.valueOf(data.getYear()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(modifyMovies.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to update this movie");
                myBuilder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(modifyMovies.this);
                        String title = modTitle.getText().toString();
                        String genre = modGenre.getText().toString();
                        int year = Integer.parseInt(modYear.getText().toString());


                        String rating = modRating.getSelectedItem().toString();

                        data.setTitle(title);
                        data.setGenre(genre);
                        data.setYear(year);
                        data.setRating(rating);

                        dbh.updateMovies(data);
                        dbh.close();
                        //Intent i = new Intent(EditSong.this,songLV.class);
                        //startActivity(i);
                        finish();
                    }
                });

                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(modifyMovies.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to DELETE this movie");

                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(modifyMovies.this);
                        String title = modTitle.getText().toString();
                        String genre = modGenre.getText().toString();
                        int year = Integer.parseInt(modYear.getText().toString());
                        String rating = modRating.toString();

                        data.setTitle(title);
                        data.setGenre(genre);
                        data.setYear(year);
                        data.setRating(rating);

                        dbh.deleteMovies(data);
                        dbh.close();
                        //Intent i = new Intent(EditSong.this,songLV.class);
                        //startActivity(i);
                        finish();
                    }
                });
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
}