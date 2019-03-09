package com.example.museum;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExhibitsActivity extends AppCompatActivity{

    private ViewPager mVp;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView[] imageViews;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    private SeekBar main_sb;
    private ImageButton main_ib;
    private MediaPlayer mediaplayer;
    //声明一个变量判断是否为暂停，默认为false
    private boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibits);

        //获取控件id
        main_sb = findViewById(R.id.main_sb);
        main_ib = findViewById(R.id.main_ib);

        //设置按钮初始图标
        main_ib.setImageResource(android.R.drawable.ic_media_play);
        main_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断
                if (mediaplayer==null){
                    Toast.makeText(ExhibitsActivity.this,"dkofksf",Toast.LENGTH_LONG).show();
                    //放入歌曲
                    mediaplayer=MediaPlayer.create(ExhibitsActivity.this,R.raw.bgm);
                    //设置进度条最大长度为音频时长
                    main_sb.setMax(mediaplayer.getDuration());
                    //开始播放
                    mediaplayer.start();
                    //使按钮变为暂停图标
                    main_ib.setImageResource(android.R.drawable.ic_media_pause);
                    //线程开始运行
                    new  myThread().start();
                }
                if (isPaused == false){
//                    Toast.makeText(ExhibitsActivity.this,"暂停",Toast.LENGTH_LONG).show();
                    //如果正在播放 (暂停)
                    mediaplayer.pause();
                    isPaused = true;
                    //改变按钮为播放
                    main_ib.setImageResource(android.R.drawable.ic_media_play);
                }else if(isPaused == true){
                    //如果没有播放 (播放)
//                    Toast.makeText(ExhibitsActivity.this,"开始",Toast.LENGTH_LONG).show();
                    mediaplayer.start();
                    isPaused = false;
                    main_ib.setImageResource(android.R.drawable.ic_media_pause);
                }

            }
        });
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

        //初始化数据
        initView();
        //设置数据
        getview();
        imageViews = new ImageView[]{imageView1,imageView2,imageView3};
    }

    private void getview(){
        Fragment fragment1=IntroductionFragment.newInstance("djsafsiffj");
        Fragment fragment2=IntroductionFragment.newInstance("dnsanls");
        Fragment fragment3=IntroductionFragment.newInstance("dmksdmsdmksmflakmf");
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

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
