package com.nepal.andoriddemo.helpers;

import android.widget.Toast;

public class ShowToast {

    public static Toast withMessage(String messageResourceId) {
        Toast toast = Toast.makeText(MyApplication.getAppContext(), messageResourceId, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static Toast withLongMessage(String messageResourceId) {
        Toast toast = Toast.makeText(MyApplication.getAppContext(), messageResourceId, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

}
