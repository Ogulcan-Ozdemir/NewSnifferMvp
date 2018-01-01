package com.ogulcan.newsniffermvp.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentCaller {

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment, int frameId){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.add(frameId,fragment).addToBackStack("fragment");
        transaction.commit();

    }
}
