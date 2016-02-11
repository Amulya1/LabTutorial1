package com.example.amulya.labassign3;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraDevice;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.AndroidCharacter;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import java.util.PriorityQueue;
//signup screen
public class Signup extends AppCompatActivity {
    Button takePhoto;
    Button galleryButton;
    ImageView displayPhoto;
    Bitmap imageData;
    private static final int CAM_REQUEST = 121;

    public void push(View v)
    {
        Intent sampleIntent=new Intent(this, MapsActivity.class);
        sampleIntent.putExtra("ImageData",imageData);
        startActivity(sampleIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        takePhoto = (Button) findViewById(R.id.photo);

        displayPhoto = (ImageView) findViewById(R.id.image);

            galleryButton=(Button)findViewById(R.id.gallery);
        galleryButton.setOnClickListener(new galleryClick());

        takePhoto.setOnClickListener(new takePhotoClicker());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }


    public void Gallery(Bitmap data)
    {


// Set the Image in ImageView after decoding the String

        displayPhoto.setImageBitmap(data);
        imageData=data;
    }

@Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data )
{
    super.onActivityResult(requestCode,resultCode,data);
    if(requestCode == CAM_REQUEST)
    {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        displayPhoto.setImageBitmap(thumbnail);
        imageData=thumbnail;

    }
    else if(requestCode==1 || resultCode==1)
    {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

// Getting cursor
        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
// Moving to first row
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imgDecodableString = cursor.getString(columnIndex);
        cursor.close();
        Log.d("String", imgDecodableString);
       Bitmap bm= BitmapFactory
                .decodeFile(imgDecodableString);

        Gallery(bm);
    }
}
    class  takePhotoClicker implements Button.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }
    }

    class  galleryClick implements Button.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent galleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent,1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}