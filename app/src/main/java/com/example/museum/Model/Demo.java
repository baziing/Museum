package com.example.museum.Model;

import com.example.museum.Controller.Recommend;
import com.example.museum.Model.UserSet;

import java.util.*;

/**
 * Created by ccwant on 2018-12-14.
 */
public class Demo {

    public static void main(String[] args) {

        //输入用户总量
        UserSet userSet = new UserSet();
        userSet.put("admin1")
                .set("大克鼎", 2)
                .set("晋侯酥钟", 3)
                .set("缂丝图", 0)
                .set("王安石", 1)
                .set("曹全碑册", 2)
                .set("商鞅方升", 2)
                .set("银币", 0)
                .create();

        userSet.put("admin2")
                .set("大克鼎", 1)
                .set("晋侯酥钟", 0)
                .set("缂丝图", 0)
                .set("王安石", 3)
                .set("曹全碑册", 1)
                .set("商鞅方升", 2)
                .set("银币", 3)
                .create();


        userSet.put("admin3")
                .set("大克鼎", 0)
                .set("晋侯酥钟", 1)
                .set("缂丝图", 4)
                .set("王安石", 2)
                .set("曹全碑册", 3)
                .set("商鞅方升", 1)
                .set("银币", 4)
                .create();

        userSet.put("admin4")
                .set("大克鼎", 3)
                .set("晋侯酥钟", 1)
                .set("缂丝图", 1)
                .set("王安石", 0)
                .set("曹全碑册", 1)
                .set("商鞅方升", 4)
                .set("银币", 4)
                .create();
        userSet.put("admin5")
                .set("大克鼎", 4)
                .set("晋侯酥钟", 0)
                .set("缂丝图", 4)
                .set("王安石", 2)
                .set("曹全碑册", 3)
                .set("商鞅方升", 0)
                .set("银币", 1)
                .create();
        userSet.put("admin6")
                .set("大克鼎", 4)
                .set("晋侯酥钟", 3)
                .set("缂丝图", 0)
                .set("王安石", 3)
                .set("曹全碑册", 0)
                .set("商鞅方升", 2)
                .set("银币", 1)
                .create();

        userSet.put("admin7")
                .set("大克鼎", 1)
                .set("晋侯酥钟", 1)
                .set("缂丝图", 3)
                .set("王安石", 4)
                .set("曹全碑册", 2)
                .set("商鞅方升", 3)
                .set("银币", 1)
                .create();

        userSet.put("admin8")
                .set("大克鼎", 0)
                .set("晋侯酥钟", 1)
                .set("缂丝图", 3)
                .set("王安石", 2)
                .set("曹全碑册", 4)
                .set("商鞅方升", 0)
                .set("银币", 3)
                .create();


        Recommend recommend = new Recommend();
        List<UserSet.Set> recommendations = recommend.recommend("小明", userSet);
        System.out.println("-----------------------");
        for (UserSet.Set set : recommendations) {
            System.out.println(set.username+" "+set.score);
        }
    }

}