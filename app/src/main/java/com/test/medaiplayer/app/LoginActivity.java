package com.test.medaiplayer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HeiPo on 16/2/24.
 */
public class LoginActivity extends Activity implements View.OnClickListener{


    private EditText edtName;
    private EditText edtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        edtName = (EditText)findViewById(R.id.et_name);
        edtPwd = (EditText)findViewById(R.id.et_password);

        ((Button)findViewById(R.id.bt_login)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("name",edtName.getText().toString().trim());
                intent.putExtra("pwd",edtPwd.getText().toString().trim());
                startActivity(intent);
                break;
        }
    }
}
