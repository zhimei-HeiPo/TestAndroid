package com.test.medaiplayer.app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private MediaPlayerFragment mediaPlayerFragment;
    private TextView textView,textLogin;

    public static final String NAME = "android";
    public static final String PWD = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mediaPlayerFragment = new MediaPlayerFragment();
        //FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.replace(R.id.fl_video, mediaPlayerFragment);
        //transaction.commit();
        textLogin = (TextView) findViewById(R.id.tv_login);
        textView = (TextView) findViewById(R.id.tv_content);
        ((Button)findViewById(R.id.bt_click)).setOnClickListener(this);


        String name = getIntent().getStringExtra("name");
        String pwd = getIntent().getStringExtra("pwd");

        if (NAME.equals(name) && PWD.equals(pwd)) {
            textLogin.setText("登录成功");
        } else {
            textLogin.setText("登录失败");
        }
    }

    @Override
    public void onClick(View view) {
        textView.setText("Hello HeiPo!");
    }
}
