package com.fh.kame.fh_app3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ((Button) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start1();
            }
        });
        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start2();
            }
        });
        ((Button) findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start3();
            }
        });
        ((Button) findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start4();
            }
        });
        ((Button) findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start5();
            }
        });
        ((Button) findViewById(R.id.button6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start6();
            }
        });
    }

    //Test

    public void start1(){
        Intent intent = new Intent(this, MyActivity2.class);
        startActivity(intent);
    }

    public void start2(){
        Intent intent = new Intent(this, MyActivity2.class);
        intent.putExtra("WHICH_BUTTON","Button 2");
        startActivity(intent);
    }

    public void start3(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01761234567"));
        startActivity(intent);
    }

    public void start4(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hs-niederrhein.de/"));
        startActivity(intent);
    }

    public void start5(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ka.menzel@arcor.de"});
        email.putExtra(Intent.EXTRA_SUBJECT, "subject");
        email.putExtra(Intent.EXTRA_TEXT, "message");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    public static final int REQUEST_PICK_IMAGE=1001;

    public void start6(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageURI = intent.getData();
            String[] projection={MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(imageURI,projection,null,null,null);
            cursor.moveToFirst();
            int columnIndex =cursor.getColumnIndex(projection[0]);
            String path=cursor.getString(columnIndex);
            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(path);
            Log.d("demo", "Image received, width="+image.getWidth());
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
