package com.example.museum;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class ContributionActivity extends AppCompatActivity {

    private EditText editAuthorName;
    private EditText editPhone;
    private EditText editExhibitName;
    private EditText editDynasty;
    private EditText editSize;
    private EditText editDonor;
    private EditText editIntroduce;
    private EditText editStory;
    private EditText editNotes;

    private Button buttonCancel;
    private Button buttonSend;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        editAuthorName=(EditText)findViewById(R.id.authorName);
        editPhone=(EditText)findViewById(R.id.phone);
        editExhibitName=(EditText)findViewById(R.id.exhibitName);
        editDynasty=(EditText)findViewById(R.id.dynasty);
        editSize=(EditText)findViewById(R.id.size);
        editDonor=(EditText)findViewById(R.id.donor);
        editIntroduce=(EditText)findViewById(R.id.introduce);
        editStory=(EditText)findViewById(R.id.story);
        editNotes=(EditText)findViewById(R.id.notes);
        buttonCancel=(Button)findViewById(R.id.cancel);//返回
        buttonSend=(Button)findViewById(R.id.send);//发送

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                ContributionActivity.this.finish();
            }
        });

        //设置按钮click条件
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContributionActivity.this, MyActivity.class);
                startActivity(intent);
                ContributionActivity.this.finish();
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSend();
            }
        });
    }

    private void attemptSend(){
        View focusView = null;
        boolean cancel = false;

        // Reset errors
        editAuthorName.setError(null);
        editPhone.setError(null);
        editExhibitName.setError(null);
        editIntroduce.setError(null);
        editStory.setError(null);

        final String authorName=editAuthorName.getText().toString();
        final String phone=editPhone.getText().toString();
        final String exhitbitName=editExhibitName.getText().toString();
        final String dynasty=editDynasty.getText().toString();
        final String size=editSize.getText().toString();
        final String donor=editDonor.getText().toString();
        final String introduce=editIntroduce.getText().toString();
        final String story=editStory.getText().toString();
        final String notes=editNotes.getText().toString();

        if (TextUtils.isEmpty(authorName)){
            editAuthorName.setError("不能为空");
            focusView=editAuthorName;
            cancel=true;
        }

        if (TextUtils.isEmpty(phone)){
            editPhone.setError("不能为空");
            focusView=editPhone;
            cancel=true;
        }

        if (TextUtils.isEmpty(exhitbitName)){
            editExhibitName.setError("不能为空");
            focusView=editExhibitName;
            cancel=true;
        }

        if (TextUtils.isEmpty(introduce)){
            editIntroduce.setError("不能为空");
            focusView=editIntroduce;
            cancel=true;
        }

        if (TextUtils.isEmpty(story)){
            editStory.setError("不能为空");
            focusView=editStory;
            cancel=true;
        }

        if (cancel){
            //仍有信息没有完成
//            Toast.makeText(ContributionActivity.this,"1",Toast.LENGTH_LONG).show();
            focusView.requestFocus();
        }else {
            //信息完成进行确认传输
//            Toast.makeText(ContributionActivity.this,"1dsafda",Toast.LENGTH_LONG).show();
            AlertDialog.Builder dialog=new AlertDialog.Builder(ContributionActivity.this);
            dialog.setTitle("确认信息");
            String message="投稿名称："+authorName+"\r\n"+"联系电话："+phone+"\r\n"+"展品名称："+exhitbitName;
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //上传到云端
                    Contribution contribution=new Contribution(BmobUser.getCurrentUser(),authorName,phone,exhitbitName,introduce,story);

                    //判断其他选项是否为空到情况
                    if (!TextUtils.isEmpty(dynasty)){
                        contribution.setDynasty(dynasty);
                    }
                    if (!TextUtils.isEmpty(size)){
                        contribution.setSize(size);
                    }
                    if (!TextUtils.isEmpty(donor)){
                        contribution.setDonor(donor);
                    }
                    if (!TextUtils.isEmpty(notes)){
                        contribution.setNotes(notes);
                    }

                    //进行上传
                    contribution.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                Toast.makeText(ContributionActivity.this,"提交成功",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(ContributionActivity.this,"提交失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //返回
                }
            });
            dialog.show();
        }
    }
}
