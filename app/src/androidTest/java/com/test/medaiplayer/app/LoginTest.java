package com.test.medaiplayer.app;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by HeiPo on 16/2/24.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);


    @Test
    public void loginWithWrongPassword(){
        onView(withId(R.id.et_name)).perform(typeText("android"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("wrong"),closeSoftKeyboard());
        onView(withId(R.id.bt_login)).perform(click());

        onView(withId(R.id.tv_login)).check(matches(withText("登录失败")));

    }


    @Test
    public void loginWithRightPassword(){
        onView(withId(R.id.et_name)).perform(typeText("android"), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(typeText("123456"),closeSoftKeyboard());
        onView(withId(R.id.bt_login)).perform(click());

        onView(withId(R.id.tv_login)).check(matches(withText("登录成功")));
    }

}
