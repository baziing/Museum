package com.example.museum.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.museum.Model.Option;
import com.example.museum.Controller.OptionAdapter;
import com.example.museum.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private List<Option> optionList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                MyActivity.this.finish();
            }
        });

        initOptions();
        OptionAdapter adapter=new OptionAdapter(getApplicationContext(),R.layout.option_item,optionList);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Option option=optionList.get(position);
                switch (position){
                    case 0:
                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_SHORT).show();
                        Intent intent0=new Intent(getApplicationContext(),CollectionActivity.class);
                        startActivity(intent0);
                        MyActivity.this.finish();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(getApplicationContext(),ContributionActivity.class);
                        startActivity(intent1);
                        MyActivity.this.finish();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(getApplicationContext(),ProposalActivity.class);
                        startActivity(intent2);
                        MyActivity.this.finish();
//                        //退出
//                        BmobUser.logOut();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_LONG).show();
                        BmobUser.logOut();
                        Intent intent3=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent3);
                        MyActivity.this.finish();
                        break;
                    default:
                }
            }
        });
    }

    //添加对应的选项
    private void initOptions(){
//        Option option=new Option("修改背景",R.mipmap.ic_account_box_grey600_36dp);
        optionList.add(new Option("我的收藏",R.mipmap.ic_heart_white_24dp));
        optionList.add(new Option("提交投稿",R.mipmap.ic_grease_pencil_white_24dp));
        optionList.add(new Option("提交反馈",R.mipmap.ic_headset_white_24dp));
        optionList.add(new Option("退出账号",R.mipmap.ic_logout_white_24dp));
    }
}
