package com.shkvarochki.mobilechallenge.ui;

import android.content.Context;

import com.trello.rxlifecycle.components.support.RxFragment;

public class BaseFragment extends RxFragment {

    public Context getContext() {
        return getActivity();
    }
}
