package com.example.niklas.diamaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button neueDiashow;
private Button diaDatenbank;
DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        neueDiashow = (Button)findViewById(R.id.neueDiashow);
        diaDatenbank = (Button) findViewById(R.id.diaDatenbank);
        neueDiashow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openNewDia();
            }
        });

        diaDatenbank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openDataDia();
            }
        });
        myDB = new DBHelper(this);

    }

    public void openNewDia(){
        Intent intent = new Intent(this, NewDiaActivity.class);
        startActivity(intent);
    }

    public void openDataDia(){
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
