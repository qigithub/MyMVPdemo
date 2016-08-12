package yunhen.mymvpdemo.http;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yunhen.mymvpdemo.Utils;

/**
 * Created by dongqi on 2016/8/10.
 */
public class HttpClient {
    private static final String TAG = "HttpClient";
    Retrofit retrofit;

    private static HttpClient httpClient;

    /**
     * @param baseUrl
     * @return
     */
    public static HttpClient getInstance(String baseUrl, Context context) {
        if (httpClient == null) {
            synchronized (HttpClient.class) {
                if (httpClient == null) {
                    httpClient = new HttpClient(baseUrl, context);
                }
            }
        }
        return httpClient;
    }

    public HttpClient(String baseUrl, final Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor mInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Utils.isNetworkReachable(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                return chain.proceed(request);

            }
        };


        Cache cache = new Cache(context.getCacheDir(), 10 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(8000, TimeUnit.MILLISECONDS)
                .writeTimeout(8000, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(mInterceptor)
                .addNetworkInterceptor(getNetWorkInterceptor(context))
                .cache(cache)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }


    public <T> T createService(Class<T> cls) {
        return retrofit.create(cls);
    }

    /**
     * 设置连接器  设置缓存
     */
    private Interceptor getNetWorkInterceptor (final Context context){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if (Utils.isNetworkReachable(context)) {
                    int maxAge = 0 * 60;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
    }


}
