package com.example.niklas.diamaster;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class NewDiaActivity extends AppCompatActivity {
DBHelper myDB;
private Button choosePic;
private static final int PICK_IMAGE = 100;
private static Uri uri;
private static String path;
private ImageView imageView;
private static EditText diaName, text, time;
private Button nextPic;
private static int ton;
private static CheckBox audioTune;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dia);
        myDB = new DBHelper(this);
        choosePic = (Button)findViewById(R.id.choosePic);
        audioTune = (CheckBox) findViewById(R.id.audioTune);
        nextPic = (Button)findViewById(R.id.nextPic);
        imageView = (ImageView) findViewById(R.id.imageView);
        choosePic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGallery();
            }
        });
        diaName = (EditText) findViewById(R.id.diaName);
        text = (EditText) findViewById(R.id.text);
        time = (EditText) findViewById(R.id.time);
        nextPic.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                addData();
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        String input = etText.getText().toString();
        if (input.isEmpty()) {
            return true;
        }else {

            return false;
        }
    }





    public void addData(){
        if (audioTune.isChecked() == true)      {
            ton = 1;
        }else{
            ton = 0;
        }
        if (isEmpty(diaName) == true || isEmpty(time) == true /*|| imageView.getDrawable() == null*/){
            Toast.makeText(NewDiaActivity.this, "please fulfil the needed Information NAME, PICTURE and TIME", Toast.LENGTH_LONG).show();
        }else {
            boolean isInserted = myDB.insertData(diaName.getText().toString(), path, Integer.parseInt(time.getText().toString()), text.getText().toString(), ton);
            if (isInserted == true) {
                Toast.makeText(NewDiaActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(NewDiaActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                diaName.setEnabled(false);
                text.setText("");
                time.setText("");
                imageView.setImageDrawable(null);
            }
       }
    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            uri = data.getData();
            imageView.setImageURI(uri);
            path = uri.toString();
        }
    }
}
