package io.github.cellzer.yuezhihu.yuezhihu.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;


/**
 * Created by walmand_ on 2016/1/29 0029.
 */
public class HttpUtils {
    private static  AsyncHttpClient client  = new AsyncHttpClient();

    public static void get(String url, ResponseHandlerInterface responseHandler){
        client.get(url,  responseHandler);
    }


    public static boolean checkNetwork(Context context){
        if(context==null){
            throw new IllegalArgumentException();
        }else{
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }

        }
        return false;
    }

}
