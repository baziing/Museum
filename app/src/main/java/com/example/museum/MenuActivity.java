package com.example.museum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button1 = (Button) findViewById(R.id.wenwuButton);
        Button button2 = (Button) findViewById(R.id.zhanlanButton);
        Button button3 = (Button) findViewById(R.id.wodeButton);
        Button button4 = (Button) findViewById(R.id.shezhiButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MenuActivity.this,"文物",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ExhibitsActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MenuActivity.this,"展览",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ExhibitionsActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MenuActivity.this,"我的",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),MyActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MenuActivity.this,"关于",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });
    }
}
