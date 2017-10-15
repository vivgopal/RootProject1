package com.example.vivekgopal.project1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.vivekgopal.project1", appContext.getPackageName());

        // Need checks for the following
        // 1) Check if http links are active
        // 2) Check if entries in DATA_TABLE have equivalent corresponding entries in SALARY_TABLE, SKILL_TABLE and CERTIFICATION_TABLE
    }
}
