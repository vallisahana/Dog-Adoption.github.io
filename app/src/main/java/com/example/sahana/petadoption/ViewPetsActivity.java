package com.example.sahana.petadoption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ViewPetsActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabasePet dbh;
    Cursor res;
    Pet_ListAdapter la;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pets);


        listView = findViewById(R.id.userdetailistview);

        dbh = new DatabasePet(getApplicationContext());
        sqLiteDatabase = dbh.getReadableDatabase();
        res = dbh.UserData();
        la = new Pet_ListAdapter(getApplicationContext(), R.layout.row);
        listView.setAdapter(la);

        if (res.moveToFirst()) {
            do {
                String name, email, password, confirm, phone, branch;

                name = res.getString(0);
                email = res.getString(1);
                password = res.getString(2);
                confirm=res.getString(3);
                phone = res.getString(4);
                branch=res.getString(5);

                Dataprovider_Pets DPC = new Dataprovider_Pets(name, email, password, confirm,phone,branch);
                la.add(DPC);
            } while (res.moveToNext());
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Dataprovider_Pets dpc=(Dataprovider_Pets) parent.getAdapter().getItem(position);

                dpc.getPhone();

                Intent intent=new Intent(ViewPetsActivity.this,ViewpetdetailActivity.class);
                intent.putExtra("bundles", dpc);
                startActivity(intent);
            }
        });
    }
}
