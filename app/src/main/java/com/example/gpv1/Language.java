package com.example.gpv1;

import android.os.LocaleList;
import android.util.Log;

import java.util.Locale;

public class Language {

    public String language="en";

    public Language(){

        // get the current display language.
        String locale =  Locale.getDefault().toString();

        if(locale.contains("Hans")) {
            language="sc";
        } else if (locale.contains("Hant")){
            language="tc";
        }

    }

    public void getLocales(){

        Locale[] locales =  Locale.getAvailableLocales();

        for(Locale locale: locales){
            Log.e("abc",locale.toString());
        }
    }

}
