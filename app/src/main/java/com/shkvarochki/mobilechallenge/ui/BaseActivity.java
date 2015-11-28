package com.shkvarochki.mobilechallenge.ui;

import android.content.Context;

import com.trello.rxlifecycle.components.RxActivity;

public class BaseActivity extends RxActivity {

    public Context getContext() {
        return this;
    }

}
