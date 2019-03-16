package com.example.museum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ExhibitionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        id=intent.getIntExtra("extra_data",-1);
        int pos=id+1;

        switch (pos){
            case 1:
                setContentView(R.layout.activity_exhibition1);
                break;
            case 2:
                setContentView(R.layout.activity_exhibition2);
                break;
            case 3:
                setContentView(R.layout.activity_exhibition3);
                break;
                default:
                    setContentView(R.layout.activity_exhibit_failure);
        }

//        Toast.makeText(getApplicationContext(),String.valueOf(id),Toast.LENGTH_LONG).show();

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ExhibitionActivity.this.finish();
            }
        });

    }
}
