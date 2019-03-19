package com.example.museum.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.museum.Model.Collection;
import com.example.museum.Controller.DBManager;
import com.example.museum.R;
import com.example.museum.Model.dbexhibit;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ExhibitActivity extends AppCompatActivity{

    private ViewPager mVp;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView[] imageViews;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imagePlayer;
    private ImageView imageLike;

    private SeekBar main_sb;
//    private ImageButton main_ib;
    private MediaPlayer mediaplayer;
    //声明一个变量判断是否为暂停，默认为false
    private boolean isPaused = false;
    private boolean initFlag = false;

    private int id;
    private String title1;
    private String title2;
    private String introduce;
    private String story;
    private String content1;
    private String content2;
    private String name;

    private Toolbar toolbar;

    private Boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit);

        Intent intent=getIntent();
        id=intent.getIntExtra("extra_data",-1);

        //数据库获取相关信息
        DBManager dbManager=new DBManager(this);
        SQLiteDatabase sqLiteDatabase=dbManager.DBManager(getPackageName());
        String[]columns=new String[]{"exhibitID","name","exhibitionID","imageUrl","dynasty","size","donor","introduce","story","title1","content1","title2","content2"};
        String selection="exhibitID="+String.valueOf(id);
        dbexhibit dbexhibit=dbManager.dbexhibitQuery(sqLiteDatabase,columns,selection,null);
        title1=dbexhibit.getTitle1();
        title2=dbexhibit.getTitle2();
        introduce=dbexhibit.getIntroduce();
        story=dbexhibit.getStory();
        content1=dbexhibit.getContent1();
        content2=dbexhibit.getContent2();
        name=dbexhibit.getName();

        //设置导航
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ExhibitActivity.this.finish();
            }
        });

        //初始化数据
        initView();
        //设置数据
        getview();
        imageViews = new ImageView[]{imageView1,imageView2,imageView3,imageView4};

        //获取控件id
        main_sb = findViewById(R.id.main_sb);

        switch (id){
            case 1:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.shangyangfangsheng);
                break;
            case 2:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.kesitu);
                break;
            case 3:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.dakeding);
                break;
            case 4:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.zhong);
                break;
            case 5:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.jing);
                break;
            case 6:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.bei);
                break;
            case 7:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.bi);
                break;
            case 8:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.gaoyiitu);
                break;
            case 9:
                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.manzu);
                break;
//            case 10:
//                mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.manzu);
//                break;
            default:
                ExhibitActivity.this.finish();
                break;
        }
//        mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.bgm);
        main_sb.setMax(mediaplayer.getDuration());
//        mediaplayer.pause();
        isPaused = true;

        imagePlayer=(ImageView)findViewById(R.id.image);
        imagePlayer.setImageResource(android.R.drawable.ic_media_play);

        imagePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断
                if (initFlag == false){

                   //放入歌曲
//                    mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.bgm);
                    //设置进度条最大长度为音频时长
//                    main_sb.setMax(mediaplayer.getDuration());
                    initFlag = true;
                    //开始播放
                    mediaplayer.start();
                    //使按钮变为暂停图标
                    imagePlayer.setImageResource(android.R.drawable.ic_media_pause);
                    //线程开始运行
                    new  myThread().start();
                }else if (isPaused == false){
                    //如果正在播放 (暂停)
                    mediaplayer.pause();
                    isPaused = true;
                    //改变按钮为播放
                    imagePlayer.setImageResource(android.R.drawable.ic_media_play);
                }else if(isPaused == true){
                    //如果没有播放 (播放)
                    mediaplayer.start();
                    isPaused = false;
                    imagePlayer.setImageResource(android.R.drawable.ic_media_pause);
                }
            }
        });

        imageLike=(ImageView)findViewById(R.id.like);
//        imageLike.setImageResource(R.mipmap.ic_account_group_outline_white_24dp);

        imageLike.setOnClickListener(new View.OnClickListener() {
            Collection collection=new Collection(BmobUser.getCurrentUser(),id,name);
            @Override
            public void onClick(View v) {
                //进行查询是否存在
                BmobQuery<Collection> collectionBmobQuery=new BmobQuery<>();
//                collectionBmobQuery.order("-createdAt");
                collectionBmobQuery.addWhereEqualTo("user", BmobUser.getCurrentUser());
                collectionBmobQuery.addWhereEqualTo("id",id);
                collectionBmobQuery.findObjects(new FindListener<Collection>() {
                    @Override
                    public void done(List<Collection> list, BmobException e) {
                        if (e==null){

                            if (list.size()==0){
                                Toast.makeText(getApplicationContext(),"添加收藏",Toast.LENGTH_LONG).show();
                                collection.addCollection();
                            }else {
                                Toast.makeText(getApplicationContext(),"取消收藏",Toast.LENGTH_LONG).show();
                                collection.deleteCollection();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"加载失败 ",Toast.LENGTH_LONG).show();
                            collection.addCollection();
                        }
                    }
                });
            }
        });


//        //获取控件id
//        main_sb = findViewById(R.id.main_sb);
//        main_ib = findViewById(R.id.main_ib);

        //设置按钮初始图标
//        main_ib.setImageResource(android.R.drawable.ic_media_play);
//        main_ib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //判断
//                if (mediaplayer==null){
//                    Toast.makeText(ExhibitActivity.this,"dkofksf",Toast.LENGTH_LONG).show();
//                    //放入歌曲
//                    mediaplayer=MediaPlayer.create(ExhibitActivity.this,R.raw.bgm);
//                    //设置进度条最大长度为音频时长
//                    main_sb.setMax(mediaplayer.getDuration());
//                    //开始播放
//                    mediaplayer.start();
//                    //使按钮变为暂停图标
//                    main_ib.setImageResource(android.R.drawable.ic_media_pause);
//                    //线程开始运行
//                    new  myThread().start();
//                }
//                if (isPaused == false){
//                    //如果正在播放 (暂停)
//                    mediaplayer.pause();
//                    isPaused = true;
//                    //改变按钮为播放
//                    main_ib.setImageResource(android.R.drawable.ic_media_play);
//                }else if(isPaused == true){
//                    //如果没有播放 (播放)
//                    mediaplayer.start();
//                    isPaused = false;
//                    main_ib.setImageResource(android.R.drawable.ic_media_pause);
//                }
//
//            }
//        });
        //设置进度条快进效果
        main_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //值改变
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            //值改变前
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //值改变后
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaplayer.seekTo(main_sb.getProgress());
            }
        });

//        //初始化数据
//        initView();
//        //设置数据
//        getview();
//        imageViews = new ImageView[]{imageView1,imageView2,imageView3,imageView4};
    }

    @Override
    public void finish(){
        mediaplayer.pause();
        super.finish();
    }
    private void getview(){
        Fragment fragment1= IntroductionFragment.newInstance("简要说明",introduce);
        Fragment fragment2=IntroductionFragment.newInstance("相关故事",story);
        Fragment fragment3=IntroductionFragment.newInstance(title1,content1);
        Fragment fragment4=IntroductionFragment.newInstance(title2,content2);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);

        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getpoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getpoint(int index) {
        //这里判断哪个是红点
        for (int i = 0; i < imageViews.length; i++) {
            if(i==index){
                imageViews[i].setImageResource(R.drawable.point_selected);
            }else{
                imageViews[i].setImageResource(R.drawable.point_mormal);
            }
        }
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        imageView1 = (ImageView) findViewById(R.id.img1);
        imageView2 = (ImageView) findViewById(R.id.img2);
        imageView3=(ImageView)findViewById(R.id.img3);
        imageView4=(ImageView)findViewById(R.id.img4);

    }

    //设置一个线程运行进度条
    class  myThread extends Thread{
        @Override
        public void run() {
            super.run();
            //判断当前播放位置是否小于总时长
            while (main_sb.getProgress()<=main_sb.getMax()) {
                //设置进度条当前位置为音频播放位置
                main_sb.setProgress(mediaplayer.getCurrentPosition());
            }
        }
    }


}
