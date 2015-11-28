package com.shkvarochki.mobilechallenge.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.components.RxActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return this;
    }

}
