package com.ogulcan.newsniffermvp.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentCaller {

    public static void showFragment(FragmentManager fragmentManager,Fragment fragment,int frameId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(fragment, "Fragment");

        transaction.commit();

    }
}
