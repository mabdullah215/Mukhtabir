package com.mobileapp.mukhtabir.network;

import static com.mobileapp.mukhtabir.network.APIList.BASE_URL;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.mobileapp.mukhtabir.data.DataSource;
import com.mobileapp.mukhtabir.interactions.IResultData;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class NetworkManager
{
    private static NetworkManager instance = null;
    OkHttpClient client;
    public boolean allowDashboardRefresh=false;

    Context mContext;
    Handler mainHandler;

    public OkHttpClient getClient() {
        return client;
    }


    public void setAllowDashboardRefresh(boolean allowDashboardRefresh) {
        this.allowDashboardRefresh = allowDashboardRefresh;
    }

    public boolean isAllowDashboardRefresh() {
        return allowDashboardRefresh;
    }



    private NetworkManager(Context context)
    {
        client=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).cache(null).build();
        mContext=context;
        mainHandler = new Handler(context.getMainLooper());
    }

    public void postRequest(String url, HashMap<String,Object> params, IResultData data)
    {
        DataSource source=DataSource.getInstance(mContext);
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        for ( Map.Entry<String, Object> entry : params.entrySet())
        {
            builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
        }
        RequestBody body = builder.build();
        Request.Builder requestBuilder = new Request.Builder().url(BASE_URL+url).post(body).cacheControl(CacheControl.FORCE_NETWORK);
        if(!source.getUserToken().isEmpty())
        {
            requestBuilder.addHeader("Authorization", "Bearer "+source.getUserToken());
        }
        Call call = client.newCall(requestBuilder.build());
        call.enqueue(new Callback()
        {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                String jsonData = response.body().string();
                Log.i("responseAPI",jsonData);
                if(data!=null)
                {
                    mainHandler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            data.notifyResult(jsonData);
                        }
                    });
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {

            }
        });
    }


    public void signupRequest(String url, HashMap<String,Object> params, IResultData result)
    {
        OkHttpClient client=new OkHttpClient();
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        for ( Map.Entry<String, Object> entry : params.entrySet())
        {
            builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(BASE_URL+url).post(body)
                .cacheControl(CacheControl.FORCE_NETWORK).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                String jsonData = response.body().string();

                Log.i("responseAPI",jsonData);
                mainHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        result.notifyResult(jsonData);
                    }
                });
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {

            }
        });
    }


    public void tokenRequest(String url,IResultData data)
    {
        RequestBody body = RequestBody.create(null, new byte[]{});
        DataSource source=DataSource.getInstance(mContext);
        Request request = new Request.Builder().addHeader("Authorization", "Bearer "+source.getUserToken()).url(BASE_URL+url).post(body)
                .cacheControl(CacheControl.FORCE_NETWORK).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                String jsonData = response.body().string();
                Log.i("responseAPI",jsonData);
                mainHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        data.notifyResult(jsonData);
                    }
                });

            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {

            }
        });
    }
    public void getRequest(String url,IResultData data)
    {
        DataSource source=DataSource.getInstance(mContext);
        Request request = new Request.Builder().addHeader("Authorization", "Bearer "+source.getUserToken()).url(BASE_URL+url)
                .cacheControl(CacheControl.FORCE_NETWORK).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException
            {
                String jsonData = response.body().string();
                Log.i("responseAPI",jsonData);
                mainHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        data.notifyResult(jsonData);
                    }
                });

            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e)
            {

            }
        });
    }

    public static synchronized NetworkManager getInstance(Context context)
    {
        if (instance == null) {
            instance = new NetworkManager(context);
        }
        return instance;
    }
}
