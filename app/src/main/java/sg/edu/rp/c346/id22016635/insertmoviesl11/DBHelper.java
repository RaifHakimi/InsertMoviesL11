package sg.edu.rp.c346.id22016635.insertmoviesl11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "movie.db";

    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TITLE + " TEXT," + COLUMN_GENRE + " TEXT, " + COLUMN_YEAR + " INTEGER," + COLUMN_RATING + " TEXT)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }

    public void insertMovie(String title, String genre, int year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_GENRE, genre);

        values.put(COLUMN_YEAR, year);

        values.put(COLUMN_RATING, rating);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_MOVIE, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Movies> getMovies() {
        ArrayList<Movies> songs = new ArrayList<Movies>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);


        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies obj = new Movies(_id,title,genre,year,rating);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public void updateMovies(Movies data){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put(COLUMN_TITLE,data.getTitle());
        value.put(COLUMN_GENRE,data.getGenre());
        value.put(COLUMN_YEAR,data.getYear());
        value.put(COLUMN_RATING,data.getRating());
        String conditions = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        db.update(TABLE_MOVIE,value,conditions,args);
        db.close();

    }

    public void deleteMovies(Movies data){
        SQLiteDatabase db = this.getReadableDatabase();
        /*ContentValues value = new ContentValues();
        value.put(COLUMN_TITLE,data.getTitle());
        value.put(COLUMN_GENRE,data.getGenre());
        value.put(COLUMN_YEAR, data.getYear());
        value.put(COLUMN_RATING, data.getRating());*/
        String conditions = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        db.delete(TABLE_MOVIE,conditions,args);
        db.close();
    }

    public ArrayList<Movies> filterMovies(String filterWord){
        ArrayList<Movies> movies = new ArrayList<Movies>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_GENRE,COLUMN_YEAR,COLUMN_RATING};
        String filter = COLUMN_RATING + " like ?";
        String[] args = { filterWord };
        Cursor cursor = db.query(TABLE_MOVIE,columns, filter,args,null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                int _id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies obj = new Movies(_id,title,genre,year,rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

}