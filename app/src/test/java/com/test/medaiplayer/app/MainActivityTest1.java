package com.test.medaiplayer.app;

import android.os.Build;
import android.widget.Button;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by HeiPo on 16/2/22.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest1 {

    // 引用待测Activity
    private MainActivity mActivity;

    // 引用待测Activity中的TextView和Button
    private TextView textView;
    private Button button;


    @Before
    public void setUp() throws Exception {
        // 获取待测Activity
        mActivity = Robolectric.setupActivity(MainActivity.class);

        // 初始化textView和button
        textView = (TextView) mActivity.findViewById(R.id.tv_content);
        button = (Button) mActivity.findViewById(R.id.bt_click);
    }


    @Test
    public void testInit() throws Exception {
        assertNotNull(mActivity);
        assertNotNull(textView);
        assertNotNull(button);

        assertEquals("com.test.medaiplayer.app", mActivity.getPackageName());

        assertEquals("Hello world!", textView.getText().toString());
    }

    @Test
    public void testButton() throws Exception {
        button.performClick();

        assertEquals("Hello HeiPo!", textView.getText().toString());
    }

    @Test
    public void testFail() throws Exception {
        fail("This case failed!");
    }

}