package com.duynguyen.cst338.recipeapp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.duynguyen.cst338.recipeapp.db.AppDataBase;
import com.duynguyen.cst338.recipeapp.db.UserDAO;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import java.util.Date;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityRule =
            new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void registerUser() {
        Intents.init();
        Context appContext = ApplicationProvider.getApplicationContext();
        AppDataBase userDatabase = Room.inMemoryDatabaseBuilder(appContext, AppDataBase.class).build();
        UserDAO userDao = userDatabase.UserDAO();

        onView(withId(R.id.username_EditText))
                .perform(typeText(generateRandomUser()), closeSoftKeyboard());
        onView(withId(R.id.email_EditText))
                .perform(typeText(generateRandomUser() + "@example.com"), closeSoftKeyboard());
        onView(withId(R.id.password_EditText))
                .perform(typeText("testPassword"), closeSoftKeyboard());
        onView(withId(R.id.confirm_password_EditText))
                .perform(typeText("testPassword"), closeSoftKeyboard());

        onView(withId(R.id.signUp_button)).perform(click());

        Intents.intended(IntentMatchers.hasComponent(LoginActivity.class.getName()));

        // Clean up the database
        userDao.deleteUserByUsername("testUser");
        userDatabase.close();
        Intents.release();
    }

    /**
     * generateRandomNumber
     *
     * @return String random number from timestamp
     */
    String generateRandomUser() {
        return "test" + Long.toString(new Date().getTime());
    }
}

