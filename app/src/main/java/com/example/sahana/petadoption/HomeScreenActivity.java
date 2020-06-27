package com.example.sahana.petadoption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreenActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    FloatingActionButton floatingActionButton;

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabasePet dbh;
    Cursor res;
    Pets_ListAdapter la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        listView = findViewById(R.id.userdetailistview);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        floatingActionButton = findViewById(R.id.addPetButton);


        dbh = new DatabasePet(getApplicationContext());
        sqLiteDatabase = dbh.getReadableDatabase();
        res = dbh.UserData();
        la = new Pets_ListAdapter(getApplicationContext(), R.layout.rows);
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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeScreenActivity.this, AddpetsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.pets:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PetsFragment()).commit();
                break;
            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                break;
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingFragment()).commit();
                break;
            case R.id.logout:
                logout();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();

        new AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Do you want to exit app?")
                .setNegativeButton("NO", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomeScreenActivity.super.onBackPressed();
                    }
                }).create().show();

    }
}