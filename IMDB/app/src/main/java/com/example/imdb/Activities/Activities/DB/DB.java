package com.example.imdb.Activities.Activities.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.imdb.Activities.Activities.Entity.Result;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    private final static String databaseName="MovieDB";
    private final static int databaseVersion=1;
    private String MOVIE_TABLE = "movies";
    private static DB dbInstance = null;
    public DB(Context context){
        super(context,databaseName,null,databaseVersion);
    }

    public synchronized static DB getInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new DB(context.getApplicationContext());
        }
        return dbInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + MOVIE_TABLE + " ("
                + " id TEXT PRIMARY KEY,"
                + " resultType TEXT,"
                + " image TEXT,"
                + " title TEXT,"
                + " description TEXT"
                + " )";

        sqLiteDatabase.execSQL(createQuery);
    }
    public void addNewMovies(String id,String resultType,String image,String title,String description)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("id",id);
        contentValues.put("resultType",resultType);
        contentValues.put("image",image);
        contentValues.put("title",title);
        contentValues.put("description",description);
        sqLiteDatabase.insert(MOVIE_TABLE,null,contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Result> getMovieList()
    {
        ArrayList<Result> movieArrayList = new ArrayList<Result>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(MOVIE_TABLE,null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do {
                movieArrayList.add(
                        new Result(
                                cursor.getString(cursor.getColumnIndex("id")),
                                cursor.getString(cursor.getColumnIndex("resultType")),
                                cursor.getString(cursor.getColumnIndex("image")),
                                cursor.getString(cursor.getColumnIndex("title")),
                                cursor.getString(cursor.getColumnIndex("description"))
                        )
                );
            }
            while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return movieArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL("ALTER TABLE "+ PERSON_TABLE + " ADD COLUMN age INTEGER");
    }


}
