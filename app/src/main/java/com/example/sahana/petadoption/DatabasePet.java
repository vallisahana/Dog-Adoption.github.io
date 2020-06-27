package com.example.sahana.petadoption;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasePet extends SQLiteOpenHelper {

    public static final String databasename = "petdb";

    public static final String Pet_table = "Pet_table";
    public static final int versioncode = 1;

    public static final String pet_name = "name";
    public static final String pet_des = "des";
    public static final String breed = "breed";
    public static final String gender = "gender";
    public static final String phone = "phone";
    public static final String weight = "weight";


    public DatabasePet(Context context) {
        super(
                context,
                databasename,
                null,
                versioncode);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String event_query;
        event_query = "CREATE TABLE IF NOT EXISTS " + Pet_table + "(name TEXT,des TEXT PRIMARY KEY,breed TEXT ," +
                "gender TEXT,phone TEXT,weight TEXT)";
        database.execSQL(event_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String user_query;
        user_query = "DROP TABLE IF EXISTS " + Pet_table;
        db.execSQL(user_query);
    }

    public boolean Pet_Data(String cust, String eventname, String eventdate, String selectevent, String selectcategory, String eventpeople) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(pet_name, cust);
        cv.put(pet_des, eventname);
        cv.put(breed, eventdate);
        cv.put(gender, selectevent);
        cv.put(phone, selectcategory);
        cv.put(weight, eventpeople);


        long result = db1.insert(Pet_table, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor UserData() {
        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from " + Pet_table, null);
        return res;
    }

}
