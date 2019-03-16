package com.example.museum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private ImageView imageView;
    private List<Integer>list=new ArrayList<>();
    private String today;
    private String lastDay;
    private TextView month;
    private TextView day;
    private ImageView allView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView=(ImageView)findViewById(R.id.image);
        month=(TextView)findViewById(R.id.mouth);
        day=(TextView)findViewById(R.id.day);
        allView=(ImageView)findViewById(R.id.all);

        allView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                HomeActivity.this.finish();
            }
        });

        init();

        lastDay=AbSharedUtil.getString(this,"LASTDAY");
        today= getYearMonthDay();
        if (lastDay!=null && lastDay.length()>0){
            if (lastDay.equals(today)){
                int position=AbSharedUtil.getInt(this,"LASTPOSITION");
//                imageView.setBackgroundResource(list.get(position));
                imageView.setImageResource(list.get(position));
            }else {
                long duration=getTwoDay(today,lastDay);
                int a= (int) (duration%5);
//                imageView.setBackgroundResource(list.get(a));
                imageView.setImageResource(list.get(a));
                AbSharedUtil.putInt(this,"LASTPOSITION",a);
            }

        }else {
            imageView.setBackgroundResource(list.get(0));
            AbSharedUtil.putString(this,"LASTDAY",today);
            AbSharedUtil.putInt(this,"LASTPOSITION",0);
        }
    }

    private long getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        java.util.Date date = null;
        try {
            date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return day ;
    }

    private static String getYearMonthDay() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(new Date());
    }

    private void init(){
        list.add(R.drawable.caihuifengniaowenqierbei);
        list.add(R.drawable.caihuilongwenlidiaorenxiangqierbei);
        list.add(R.drawable.caihuiqierbei);
        list.add(R.drawable.caihuiqizhenmushou);
        list.add(R.drawable.caihuiyunfengwenqiyuanpan);
        list.add(R.drawable.caihuiyunniaowenqidou);
        list.add(R.drawable.caihuiyunniaowenqizun);
        list.add(R.drawable.caoquanbei);
        list.add(R.drawable.chuhangdechuan);
        list.add(R.drawable.ding);
        list.add(R.drawable.heiqilianbanxinglian);
        list.add(R.drawable.huazuo50hao);
        list.add(R.drawable.kesihuaniao);
        list.add(R.drawable.kesitu);
        list.add(R.drawable.lanseyujinsedeyequ);
        list.add(R.drawable.libaizhihou);
        list.add(R.drawable.manzu);
        list.add(R.drawable.niugutouyubaimeigui);
        list.add(R.drawable.qingchunqi);
        list.add(R.drawable.shangyang);
        list.add(R.drawable.shengyi);
        list.add(R.drawable.shuanglong);
        list.add(R.drawable.wutai);
        list.add(R.drawable.wuti1);
        list.add(R.drawable.xiandaizhuyi);
        list.add(R.drawable.xiari);
        list.add(R.drawable.yeyouzhe);
        list.add(R.drawable.yinbi);
        list.add(R.drawable.zhong);
        list.add(R.drawable.zhuqijubanxingpan);
        list.add(R.drawable.zihuaxiang);

        Time time=new Time();
        time.setToNow();

        //年
        int myear=time.year;
        String syear=String.valueOf(myear)+" / ";

        //月
        int mmouth=time.month;
        switch (mmouth){
            case 1:
                month.setText("Jan.");
                break;
            case 2:
                month.setText(syear+"Feb.");
                break;
            case 3:
                month.setText("Mar.");
                break;
            case 4:
                month.setText("Apr.");
                break;
            case 5:
                month.setText("May");
                break;
            case 6:
                month.setText("June");
                break;
            case 7:
                month.setText("July");
                break;
            case 8:
                month.setText("Aug.");
                break;
            case 9:
                month.setText("Sept.");
                break;
            case 10:
                month.setText("Oct.");
                break;
            case 11:
                month.setText("Nov.");
                break;
            case 12:
                month.setText("Dec.");
                break;
                default:
        }

        //日
        int mday=time.monthDay;
        day.setText(String.valueOf(mday));
    }
}
