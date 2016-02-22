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
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mediaPlayerFragment = new MediaPlayerFragment();
        //FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //transaction.replace(R.id.fl_video, mediaPlayerFragment);
        //transaction.commit();
        textView = (TextView) findViewById(R.id.tv_content);
        ((Button)findViewById(R.id.bt_click)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        textView.setText("Hello HeiPo!");
    }
}
