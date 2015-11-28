package com.shkvarochki.mobilechallenge.ui;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return this;
    }

    public void handleError(Exception e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(e.getMessage())
                .show();
    }
}
