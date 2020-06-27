package com.example.sahana.petadoption;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class AddpetsActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;


    private Button uploadBtn;
    private EditText petname,des,breed,gender,phone,weight;
    DatabasePet DH = new DatabasePet(this);
    ImageView imageView;
    Uri imageuri;

    final int REQUEST_CODE_GALLERY=999;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpets);


        uploadBtn = findViewById(R.id.buttonupload);
        petname = findViewById(R.id.petname);
        des = findViewById(R.id.petdesc);
        breed = findViewById(R.id.breed);
        gender = findViewById(R.id.gender);
        phone = findViewById(R.id.phone);
        weight = findViewById(R.id.weight);
        imageView=findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddpetsActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
              openFileChooser();

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(petname.getText().toString()) ||
                        TextUtils.isEmpty(des.getText().toString()) ||
                        TextUtils.isEmpty(breed.getText().toString())||
                        TextUtils.isEmpty(gender.getText().toString())||
                        TextUtils.isEmpty(phone.getText().toString())) {

                    Toast.makeText(AddpetsActivity.this, "Pls fill the fields",
                            Toast.LENGTH_LONG).show();


                }
                        boolean res = DH.Pet_Data(petname.getText().toString(),des.getText().toString(),breed.getText().toString(),
                                gender.getText().toString(),phone.getText().toString(),
                                weight.getText().toString());
                        if (res) {

                            Toast.makeText(AddpetsActivity.this, "Submitted Successfully",
                                    Toast.LENGTH_LONG).show();
                        }

                        petname.setText("");
                        des.setText("");
                        breed.setText("");
                        gender.setText("");
                        phone.setText("");
                        weight.setText("");

                    }

        });

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryintent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,REQUEST_CODE_GALLERY);

            }else {
                Toast.makeText(this, "Dont have persmission", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageuri = data.getData();

            Picasso.with(this).load(imageuri).into(imageView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

