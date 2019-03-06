package com.example.museum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ExhibitsActivity extends AppCompatActivity{

    private ViewPager mVp;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView[] imageViews;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibits);

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

}
