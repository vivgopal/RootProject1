package com.example.vivekgopal.project1;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.ArraySet;
import android.util.Log;
import android.util.Patterns;

import com.example.vivekgopal.project1.adapters.DatabaseAdapter;
import com.example.vivekgopal.project1.data.CertificationItem;
import com.example.vivekgopal.project1.data.CompanyItem;
import com.example.vivekgopal.project1.data.SkillItem;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import android.content.res.Resources;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DbTests {

    public static final String TAG = DbTests.class.getSimpleName();

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void DbOpenTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        DatabaseAdapter mDbAdapter = new DatabaseAdapter(appContext);
        mDbAdapter.createDatabase();
        mDbAdapter.open();
        assertTrue(mDbAdapter.isOpen());
        mDbAdapter.close();
    }

    // Need checks for the following
    // 1) Check if http links are active
    // 2) Check if entries in DATA_TABLE have equivalent corresponding entries in SALARY_TABLE, SKILL_TABLE and CERTIFICATION_TABLE
    // 3) skills/companies/certifications/tips/career ladder are repeated more than once
    // TODO
    // 4) Check if an image exists for all certifications

    @Test
    public void DbHttpLinkTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseAdapter mDbAdapter = new DatabaseAdapter(appContext);
        mDbAdapter.createDatabase();
        mDbAdapter.open();

        List<SkillItem> skillItemList = mDbAdapter.getAllSkillItems();
        List<CertificationItem> certificationItemList = mDbAdapter.getAllCertificationItems();
        List<CompanyItem> companyItemList = mDbAdapter.getAllCompanyItems();

        for (SkillItem item : skillItemList) {
            checkURL(item.getUrl());
        }
        for (CertificationItem item : certificationItemList) {
            checkURL(item.getUrl());
        }
        for (CompanyItem item : companyItemList) {
            checkURL(item.getUrl());
        }

        mDbAdapter.close();
    }

    @Test
    public void DbSkillEntriesTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseAdapter mDbAdapter = new DatabaseAdapter(appContext);
        mDbAdapter.createDatabase();
        mDbAdapter.open();

        Resources res = getInstrumentation().getTargetContext().getResources();

        String stream = res.getStringArray(R.array.streams)[0];
        int id = res.getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", appContext.getPackageName());
        String[] specializationArray = res.getStringArray(id);

        for(String specialization:specializationArray) {
            List<String> skillList = mDbAdapter.getSkills(WordUtils.uncapitalize(stream), WordUtils.uncapitalize(specialization));


            for (String skill : skillList) {
                try {
                    assertTrue(mDbAdapter.checkDataInDB("SKILL_TABLE", "skill", skill));
                    Log.d(TAG, "ASSERTPASS: SKILL '" + skill + "' from specialization '" + specialization +"' present in SKILL_TABLE");
                } catch (Throwable t) {
                    Log.e(TAG, "ASSERTFAIL: SKILL '" + skill + "' from specialization '" + specialization +"' not present in SKILL_TABLE");
                    collector.addError(t);
                }
            }
        }

        mDbAdapter.close();
    }

    @Test
    public void DbSalaryEntriesTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseAdapter mDbAdapter = new DatabaseAdapter(appContext);
        mDbAdapter.createDatabase();
        mDbAdapter.open();

        Resources res = getInstrumentation().getTargetContext().getResources();

        String stream = res.getStringArray(R.array.streams)[0];
        int id = res.getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", appContext.getPackageName());
        String[] specializationArray = res.getStringArray(id);

        for(String specialization:specializationArray) {
            List<String> companyList = mDbAdapter.getCompanies(WordUtils.uncapitalize(stream), WordUtils.uncapitalize(specialization));

            for (String company : companyList) {
                try {
                    assertTrue(mDbAdapter.checkDataInDB("COMPANY_TABLE", "company", company));
                    Log.d(TAG, "ASSERTPASS: COMPANY '" + company + "' from specialization '" + specialization +"' present in COMPANY_TABLE");
                } catch (Throwable t) {
                    Log.e(TAG, "ASSERTFAIL: COMPANY '" + company + "' from specialization '" + specialization +"' not present in COMPANY_TABLE");
                    collector.addError(t);
                }
            }
        }

        mDbAdapter.close();
    }

    @Test
    public void DbCertificationEntriesTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseAdapter mDbAdapter = new DatabaseAdapter(appContext);
        mDbAdapter.createDatabase();
        mDbAdapter.open();

        Resources res = getInstrumentation().getTargetContext().getResources();

        String stream = res.getStringArray(R.array.streams)[0];
        int id = res.getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", appContext.getPackageName());
        String[] specializationArray = res.getStringArray(id);

        for(String specialization:specializationArray) {
            List<String> certificationList = mDbAdapter.getCertifications(WordUtils.uncapitalize(stream), WordUtils.uncapitalize(specialization));
            Log.d(TAG, "Running through specialization '" + specialization);

            for (String certification : certificationList) {
                try {
                    assertTrue(mDbAdapter.checkDataInDB("CERTIFICATION_TABLE", "name", certification));
                    Log.d(TAG, "ASSERTPASS: CERTIFICATION '" + certification + "' from specialization '" + specialization +"' present in CERTIFICATION_TABLE");
                } catch (Throwable t) {
                    Log.e(TAG, "ASSERTFAIL: CERTIFICATION '" + certification + "' from specialization '" + specialization +"' not present in CERTIFICATION_TABLE");
                    collector.addError(t);
                }
            }
        }

        mDbAdapter.close();
    }

    @Test
    public void DbDuplicateEntriesTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DatabaseAdapter mDbAdapter = new DatabaseAdapter(appContext);
        mDbAdapter.createDatabase();
        mDbAdapter.open();

        Resources res = getInstrumentation().getTargetContext().getResources();

        String stream = res.getStringArray(R.array.streams)[0];
        int id = res.getIdentifier("specialization_" + stream.replaceAll(" ", "_"), "array", appContext.getPackageName());
        String[] specializationArray = res.getStringArray(id);

        for(String specialization:specializationArray) {
            List<String> certificationList = mDbAdapter.getCertifications(WordUtils.uncapitalize(stream), WordUtils.uncapitalize(specialization));
            try {
                assertTrue(!hasDuplicate(certificationList));
                Log.d(TAG, "ASSERTPASS: certifications from specialization '" + specialization +"' does not have duplicates");
            } catch (Throwable t) {
                Log.e(TAG, "ASSERTFAIL: certifications from specialization '" + specialization +"' has duplicates");
                collector.addError(t);
            }

            List<String> skillList = mDbAdapter.getSkills(WordUtils.uncapitalize(stream), WordUtils.uncapitalize(specialization));
            try {
                assertTrue(!hasDuplicate(skillList));
                Log.d(TAG, "ASSERTPASS: skills from specialization '" + specialization +"' does not have duplicates");
            } catch (Throwable t) {
                Log.e(TAG, "ASSERTFAIL: skills from specialization '" + specialization +"' has duplicates");
                collector.addError(t);
            }

            List<String> companyList = mDbAdapter.getCompanies(WordUtils.uncapitalize(stream), WordUtils.uncapitalize(specialization));
            try {
                assertTrue(!hasDuplicate(companyList));
                Log.d(TAG, "ASSERTPASS: companies from specialization '" + specialization +"' does not have duplicates");
            } catch (Throwable t) {
                Log.e(TAG, "ASSERTFAIL: companies from specialization '" + specialization +"' has duplicates");
                collector.addError(t);
            }
        }

        mDbAdapter.close();
    }

    public boolean hasDuplicate(final List<String> list)
    {
        Set<String> set = new HashSet<String>();
        for (String str : list)
        {
            if (set.contains(str)) {
                Log.e(TAG, "Duplicate is '" + str + "'");
                return true;
            }
            set.add(str);
        }
        return false;
    }

    public boolean checkURL(String linkURL) {
        assertTrue(Patterns.WEB_URL.matcher(linkURL).matches());

        try {
        HttpURLConnection httpUrlConn = (HttpURLConnection) new URL(linkURL).openConnection();
            Log.d(TAG, "Trying URL (" + linkURL + ")");

        //server returns the response headers only
            httpUrlConn.setRequestMethod("HEAD");


        // Set timeouts in milliseconds
        httpUrlConn.setConnectTimeout(30000);
        httpUrlConn.setReadTimeout(30000);

            try {
                assertTrue(httpUrlConn.getResponseCode() != 404);
                Log.d(TAG, "ASSERTPASS: URL (" + linkURL + ") passed with response code " + httpUrlConn.getResponseCode());
                return true;
            } catch (Throwable t) {
                Log.e(TAG, "ASSERTFAIL: URL (" + linkURL + ") failed with response code " + httpUrlConn.getResponseCode());
                collector.addError(t);
                return false;
            }
        } catch (Throwable t) {
            Log.e(TAG, "ASSERTFAIL: setRequestMethod failed");
            collector.addError(t);
            return false;
        }

    }
}
