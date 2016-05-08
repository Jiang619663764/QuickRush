package com.example.huhu.util;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/13.
 */
public class OkHttpClientManager {

    private static OkHttpClientManager mInstance;

    private OkHttpClient mOkHttpClient;

    private Handler mHandler;

    private Gson mGson;

    private static final String TAG = "OkHttpClientManager";

    private OkHttpClientManager() {

        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static OkHttpClientManager getInstance() {

        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * get请求
     *
     * @param url
     */
    public void get(String url, OkHttpBaseCallBack callBack) {

        Request request = buildRequest(url, null, HttpMethodType.GET);

        doRequest(request, callBack);
    }

    /**
     * post请求
     *
     * @param url
     * @param params
     */
    public void post(String url, Map<String, String> params, OkHttpBaseCallBack callBack) {

        Request request = buildRequest(url, params, HttpMethodType.POST);

        doRequest(request, callBack);
    }

    /**
     * 发送请求
     *
     * @param request
     * @param callBack
     */
    public void doRequest(final Request request, final OkHttpBaseCallBack callBack) {

        callBack.onRequestBefore(request);

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(request, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {
                    //解析
                    String resultStr = response.body().string();
                    if (callBack.mType == String.class) {
//                        callBack.onSuccess(response, null);
                        callBackSuccess(callBack, response, resultStr);
                    } else {

                        try {
                            Object obj = mGson.fromJson(resultStr, callBack.mType);
//                            callBack.onSuccess(response, null);
                            callBackSuccess(callBack, response, obj);
                        } catch (JsonParseException e) {

//                            callBack.onError(response, response.code(), e);
                            callBackError(callBack, response, null);
                        }

                    }

                } else {
                    callBackError(callBack, response, null);
                }
            }
        });
    }

    /**
     * 构建Request对象
     *
     * @return
     */
    private Request buildRequest(String url, Map<String, String> params, HttpMethodType type) {

        Request.Builder builder = new Request.Builder();
        builder.url(url);

        //判断是那种请求方式
        if (type == HttpMethodType.GET) {

            builder.get();

        } else if (type == HttpMethodType.POST) {

            RequestBody body = buildFromData(params);

            builder.post(body);
        }

        return builder.build();
    }

    /**
     * post请求中，获取post中RequestBody对象
     *
     * @param params
     * @return
     */
    private RequestBody buildFromData(Map<String, String> params) {

        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    /**
     * 在非UI线程执行
     *
     * @param callBack
     * @param response
     * @param object
     */
    private void callBackSuccess(final OkHttpBaseCallBack callBack, final Response response, final Object object) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(response, object);
            }
        });
    }

    /**
     * 在非UI线程执行
     *
     * @param callBack
     * @param response
     * @param object
     */
    private void callBackError(final OkHttpBaseCallBack callBack, final Response response, final Object object) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onError(response, response.code(), null);
            }
        });
    }

    /**
     * 枚举 判断是get还是post请求
     */
    enum HttpMethodType {
        GET,
        POST
    }

}
