package com.shkvarochki.mobilechallenge.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import com.shkvarochki.mobilechallenge.R;

import org.androidannotations.annotations.EBean;

@EBean
public class ProgressDialogHelper {

    private final Context context;

    public boolean isCanceled;

    public String title;
    public String message;

    private ProgressDialog dialog;

    public ProgressDialogHelper(Context context) {
        this.context = context;

        Resources resources = context.getResources();

        title = resources.getString(R.string.Processing);
        message = resources.getString(R.string.WaitC_pleaseD3);
    }

    public void showProgressDialog(@Nullable Integer titleResId, @Nullable Integer messageResId, boolean isCancelable) {
        dismissDialogWithCancel();

        dialog = new ProgressDialog(context);
        if (isCancelable) {
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    isCanceled = true;
                }
            });
        }
        dialog.setTitle(titleResId != null ? context.getString(titleResId) : title);
        dialog.setMessage(messageResId != null ? context.getString(messageResId) : message);
        dialog.setCancelable(isCancelable);
        dialog.show();
    }

    public void showProgressDialog(String title, String message) {
        dismissDialogWithCancel();

        dialog = ProgressDialog.show(context, title, message, true);
    }

    public void showProgressDialog() {
        showProgressDialog(title, message);
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    private void dismissDialogWithCancel() {
        isCanceled = false;
        dismissDialog();
    }
}
