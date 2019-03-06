package com.example.museum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExhibitionsActivity extends AppCompatActivity {

    private Exhibition[] exhibitions={new Exhibition("App",R.drawable.back),
            new Exhibition("dfad",R.drawable.underline_account)};
    private List<Exhibition>exhibitionList=new ArrayList<>();
    private ExhibitionAdapter exhibitionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitions);

        initExhibitions();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        exhibitionAdapter=new ExhibitionAdapter(exhibitionList);
        recyclerView.setAdapter(exhibitionAdapter);

//        点击事件
        exhibitionAdapter.setOnItemClickListener(new ExhibitionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),"dklfk",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initExhibitions(){
        exhibitionList.clear();
        for (int i=0;i<10;i++){
            Random random=new Random();
            int index=random.nextInt(exhibitions.length);
            exhibitionList.add(exhibitions[index]);
        }
    }
}
