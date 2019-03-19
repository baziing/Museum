package com.example.museum.View;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.museum.Model.Collection;
import com.example.museum.Model.Option;
import com.example.museum.Controller.OptionAdapter;
import com.example.museum.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CollectionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private List<Option> optionList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MyActivity.class);
                startActivity(intent);
                CollectionActivity.this.finish();
            }
        });

        initOptions();
        final OptionAdapter adapter=new OptionAdapter(getApplicationContext(),R.layout.option_item,optionList);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Option option=optionList.get(position);

                int exhibitId=option.getId();
                Intent intent=new Intent(getApplicationContext(),ExhibitActivity.class);
                intent.putExtra("extra_data",exhibitId);
                startActivity(intent);

//                switch (position){
//                    case 0:
//                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_SHORT).show();
//                        Intent intent0=new Intent(getApplicationContext(),CollectionActivity.class);
//                        startActivity(intent0);
//                        CollectionActivity.this.finish();
//                        break;
//                    case 1:
//                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_SHORT).show();
//                        Intent intent1=new Intent(getApplicationContext(),ContributionActivity.class);
//                        startActivity(intent1);
//                        CollectionActivity.this.finish();
//                        break;
//                    case 2:
//                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_SHORT).show();
//                        Intent intent2=new Intent(getApplicationContext(),ProposalActivity.class);
//                        startActivity(intent2);
//                        CollectionActivity.this.finish();
////                        //退出
////                        BmobUser.logOut();
//                        break;
//                    case 3:
//                        Toast.makeText(getApplicationContext(),option.getName(),Toast.LENGTH_LONG).show();
//                    default:
//                }
            }
        });
    }

    //添加对应的选项
    private void initOptions(){
//        Option option=new Option("修改背景",R.mipmap.ic_account_box_grey600_36dp);

        BmobQuery<Collection> collectionBmobQuery=new BmobQuery<>();
        collectionBmobQuery.order("-createdAt");
        collectionBmobQuery.findObjects(new FindListener<Collection>() {
            @Override
            public void done(List<Collection> list, BmobException e) {
                if (e==null){
                    for (int i=0;i<list.size();i++){
                        optionList.add(new Option(list.get(i).getName(),R.mipmap.ic_label_white_24dp,list.get(i).getId()));
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"加载失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
