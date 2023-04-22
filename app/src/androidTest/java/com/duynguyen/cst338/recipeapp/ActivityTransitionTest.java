package com.duynguyen.cst338.recipeapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.room.Room;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.User;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ActivityTransitionTest {
    static UserDAO userDao;
    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testMainActivityToRegisterActivity() {
        onView(withId(R.id.create_account_button)).perform(click());
        onView(withId(R.id.register_page_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testMainActivityToLoginActivity() {
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.login_page_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginActivityToLandingPageActivity() {
        onView(withId(R.id.login_button)).perform(click());

        // Assuming a user "testuser" with password "testuser" already exists
        onView(withId(R.id.username_EditText)).perform(typeText("testuser"), closeSoftKeyboard());
        onView(withId(R.id.password_EditText)).perform(typeText("testuser"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.landing_page_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginActivityToAdminActivity() {
        onView(withId(R.id.login_button)).perform(click());

        // Assuming an admin user "admin" with password "admin" already exists
        onView(withId(R.id.username_EditText)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password_EditText)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.landing_page_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testLandingPageToManageUsersActivity() {
        onView(withId(R.id.login_button)).perform(click());

        // Assuming an admin user "admin2" with password "admin2" already exists
        onView(withId(R.id.username_EditText)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password_EditText)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.manage_users_button)).perform(click());
        onView(withId(R.id.user_list_layout)).check(matches(isDisplayed()));
    }
}