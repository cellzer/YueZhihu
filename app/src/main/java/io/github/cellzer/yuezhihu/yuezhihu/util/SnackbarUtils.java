package io.github.cellzer.yuezhihu.yuezhihu.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by walmand_ on 2016/1/29 0029.
 */
public class SnackbarUtils {

    public static void show(View view, int resID) {
        Snackbar.make(view, resID, Snackbar.LENGTH_SHORT)
                .show();
    }

    public static void show(Activity activity, int resID) {
        View view = activity.getWindow().getDecorView();
        show(view, resID);
    }
    public static void show(Activity activity, String msg) {
        View view = activity.getWindow().getDecorView();
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .show();
    }

    public static void showAction(View view, int message, int resID, View.OnClickListener listener) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction(resID, listener)
                .show();
    }

    public static void showAction(Activity activity, int message, int resID, View.OnClickListener listener) {
        View view = activity.getWindow().getDecorView();
        showAction(view, message, resID, listener);
    }

    public static void showAction(Activity activity, int message, String actionName, View.OnClickListener listener) {
        View view = activity.getWindow().getDecorView();
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction(actionName, listener)
                .show();
    }
}
