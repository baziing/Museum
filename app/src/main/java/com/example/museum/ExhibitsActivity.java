package com.example.museum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ExhibitsActivity extends AppCompatActivity {

    private List<Exhibit>exhibitList=new ArrayList<>();
    private ExhibitsAdapter exhibitsAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibits);

        init();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
//        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        exhibitsAdapter=new ExhibitsAdapter(exhibitList);
        recyclerView.setAdapter(exhibitsAdapter);
        recyclerView.setPadding(20, 6, 20, 6);
        recyclerView.addItemDecoration(new ItemDecoration(6));

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                ExhibitsActivity.this.finish();
            }
        });

//        点击事件
        exhibitsAdapter.setOnItemClickListener(new ExhibitsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ExhibitsActivity.this,String.valueOf(position),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),ExhibitActivity.class);
                intent.putExtra("extra_data",position+1);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(ExhibitsActivity.this,"dklfk",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(){
        exhibitList.clear();
        exhibitList.add(new Exhibit(1,R.drawable.shangyang,"商鞅方升","秦始皇年间\n重量:690g"));
        exhibitList.add(new Exhibit(2,R.drawable.kesitu,"朱克柔缂丝莲塘乳鸭图","宋·南宋"));
        exhibitList.add(new Exhibit(3,R.drawable.ding,"大克鼎","西周 中期 孝王\n"+"重量:201500g\n"+"捐赠人:潘达于"));
        exhibitList.add(new Exhibit(4,R.drawable.zhong,"晋侯稣钟","西周 晚期 厉王\n"+"重量:6150-22700g"));
        exhibitList.add(new Exhibit(5,R.drawable.wanganshi,"王安石\n行书楞严经旨要卷","宋·北宋\n元丰八年(公元1085)"));
        exhibitList.add(new Exhibit(6,R.drawable.caoquanbei,"曹全碑册","东汉\n捐赠人:顾公雄"));
        exhibitList.add(new Exhibit(7,R.drawable.yinbi,"广东寿字一两银币","公元1904-1905年\n尺寸:径4.2cm\n捐赠人:王亢元"));
        exhibitList.add(new Exhibit(8,R.drawable.kesihuaniao,"缂丝花鸟图","清 乾隆"));
        exhibitList.add(new Exhibit(9,R.drawable.manzu,"满族平金绣云龙纹朝袍","清\n尺寸:长131.5cm 宽191cm"));
        exhibitList.add(new Exhibit(10,R.drawable.shuanglong,"双龙首玉璜","春秋\n尺寸:长10.6cm 宽3cm"));
    }
}
