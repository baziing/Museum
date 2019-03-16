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

public class ProposalActivity extends AppCompatActivity {

    private EditText editAuthorName;
    private EditText editPhone;
    private EditText editContent;

    private Button buttonCancel;
    private Button buttonSend;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);

        editAuthorName=(EditText)findViewById(R.id.authorName);
        editPhone=(EditText)findViewById(R.id.phone);
        editContent=(EditText)findViewById(R.id.content);
        buttonCancel=(Button)findViewById(R.id.cancel);//返回
        buttonSend=(Button)findViewById(R.id.send);//发送

        toolbar.setNavigationIcon(R.mipmap.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                ProposalActivity.this.finish();
            }
        });

        //设置按钮click条件
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProposalActivity.this, MyActivity.class);
                startActivity(intent);
                ProposalActivity.this.finish();
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
        editContent.setError(null);

        final String authorName=editAuthorName.getText().toString();
        final String phone=editPhone.getText().toString();
        final String content=editContent.getText().toString();

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

        if (TextUtils.isEmpty(content)){
            editContent.setError("不能为空");
            focusView=editContent;
            cancel=true;
        }

        if (cancel){
            focusView.requestFocus();
        }else {
            AlertDialog.Builder dialog=new AlertDialog.Builder(ProposalActivity.this);
            dialog.setTitle("确认信息");
            String message="投稿名称："+authorName+"\r\n"+"联系电话："+phone;
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Proposal proposal=new Proposal(BmobUser.getCurrentUser(),content,authorName,phone);

                    proposal.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                Toast.makeText(ProposalActivity.this,"上传成功",Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(ProposalActivity.this,"上传失败",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        }

    }
}
