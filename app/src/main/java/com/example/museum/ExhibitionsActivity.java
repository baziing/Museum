package com.example.museum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionsActivity extends AppCompatActivity {

    private Exhibition[] exhibitions={new Exhibition("App",R.drawable.back),
            new Exhibition("dfad",R.drawable.underline_account)};
    private List<Exhibition>exhibitionList=new ArrayList<>();
    private ExhibitionAdapter exhibitionAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibitions);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                ExhibitionsActivity.this.finish();
            }
        });

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
                Intent intent=new Intent(getApplicationContext(),ExhibitionActivity.class);
                intent.putExtra("extra_data",position);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ExhibitionActivity.class);
                intent.putExtra("extra_data",position);
                startActivity(intent);
            }
        });
    }

    private void initExhibitions(){
        exhibitionList.clear();
//        for (int i=0;i<10;i++){
//            Random random=new Random();
//            int index=random.nextInt(exhibitions.length);
//            exhibitionList.add(exhibitions[index]);
//        }
        exhibitionList.add(new Exhibition("走向现代主义：美国艺术八十载1865-1945",R.drawable.t1));
        exhibitionList.add(new Exhibition("千文万华：中国历代漆器艺术展",R.drawable.t2));
        exhibitionList.add(new Exhibition("丹青宝筏：董其昌书画艺术大展",R.drawable.t3));
        exhibitionList.add(new Exhibition("猪丰卣满迎新春",R.drawable.t4));
        exhibitionList.add(new Exhibition("白银之路：中国货币史中的白银（暂定名）",R.drawable.t5));
//        exhibitionList.add(new Exhibition("浮槎于海：凯布朗利-雅克•希拉克博物馆藏太平洋艺术珍品展",R.drawable.t6));
        exhibitionList.add(new Exhibition("明十五世纪中期景德镇瓷器大展",R.drawable.t6));
        exhibitionList.add(new Exhibition("法国巴黎国立高等美术学院艺术精品展",R.drawable.t7));
        exhibitionList.add(new Exhibition("金石筼筜：金西厓竹刻艺术特展",R.drawable.t8));
//        exhibitionList.add(new Exhibition("唐招提寺鉴真御影堂文物与艺术展（暂定名）",R.drawable.ding));
    }
}
