package com.myprog.zoop.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.myprog.zoop.AppConstant;
import com.myprog.zoop.R;


public class Utility {

    private static final String TAG = Utility.class.getSimpleName();
    private Utility(){}

    /**
     * This method is used to show error dialog.
     *
     * @param context view context
     * @param msg dialog message
     */
    public static void showDialog(Context context, String msg) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(msg);
        alertDialog.setButton(AppConstant.DIALOG_OK_BUTTON, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    /**
     * This function is used to product image id.
     *
     * @param id image id.
     * @return res id.
     */
    public static int getImageResId(int id) {
        int resId = R.drawable.na;
        switch (id) {
            case 3141:
                resId = R.drawable.img3141;
                break;
            case 7526:
                resId = R.drawable.img7526;
                break;
            case 11887:
                resId = R.drawable.img11887;
                break;
            case 12248:
                resId = R.drawable.img12248;
                break;
            case 12249:
                resId = R.drawable.img12249;
                break;
            case 28041:
                resId = R.drawable.img28041;
                break;
            case 31085:
                resId = R.drawable.img31085;
                break;
            case 51197:
                resId = R.drawable.img51197;
                break;
            case 28015:
                resId = R.drawable.img28015;
                break;
        }
        return resId;
    }
}
