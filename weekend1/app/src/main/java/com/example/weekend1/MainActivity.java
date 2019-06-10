package com.example.weekend1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button mCaptureBtn;
    Button goEMIbtn;
    Button Personbtn;
    Toolbar tool;
    ImageView mImageView;
    private static final int PERMISSION_CODE=1000;
    private static final int IMAGE_CAPTURE_CODE=1001;
    Uri image_uri;
    Activity a;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((androidx.appcompat.widget.Toolbar) findViewById(R.id.my_bar));
        Personbtn=findViewById(R.id.newbutton);
        Personbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Send.class);
                startActivity(intent);

            }
        });
        mImageView=findViewById(R.id.image_view);
        goEMIbtn=findViewById(R.id.emi_btn);
        mCaptureBtn=findViewById(R.id.capture_image_btn);
        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                        String[] permission={Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISSION_CODE);
                    }
                    else{
                        openCamera();
                    }
                }
                else{
                    openCamera();
                }

            }
        });

        goEMIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void openCamera(){
        ContentValues values=new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the Camera");
        image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this,"Permission denied...",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){
            mImageView.setImageURI(image_uri);
        }
    }
}
