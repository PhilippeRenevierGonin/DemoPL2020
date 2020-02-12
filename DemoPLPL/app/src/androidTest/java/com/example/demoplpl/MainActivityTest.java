package com.example.demoplpl;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.demoplpl.reseau.Connexion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.TimeUnit;

import constantes.NET;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    int cpt = 0;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Mock
    Connexion connexion;

    @Before
    public void initConnexion() {



        // Lazily start the Activity from the ActivityTestRule this time to inject the start Intent
        Intent startIntent = new Intent();
        startIntent.putExtra(MainActivity.AUTOCONNECT, false);
        mActivityRule.launchActivity(startIntent);


        MockitoAnnotations.initMocks(this);

        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setConnexion(connexion);
                mActivityRule.getActivity().initVue();
            }
        });



    }



    @Test
    public void mettreÀJourLAffichageDuCompteur() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                cpt++;
                mActivityRule.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mActivityRule.getActivity().mettreÀJourLAffichageDuCompteur(cpt);
                    }
                });
                return null;
            }
        }).when(connexion).envoyer(NET.AJOUTER);

        onView(withId(R.id.text)).check(matches(withText("Hello World!")));

        for(int i = 1; i< 11; i++) {
            onView(withId(R.id.button)).perform(click());
        }

        onView(withId(R.id.text)).check(matches(withText("10")));



    }
}