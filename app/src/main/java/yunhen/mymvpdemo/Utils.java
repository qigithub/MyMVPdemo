package yunhen.mymvpdemo;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by dongqi on 2016/8/9.
 */
public class Utils {
    public static <T> T $(Context context,int id){
        return (T)((Activity) context).findViewById(id);
    }
    public static <T> T $(View context, int id){
        return (T)( context).findViewById(id);
    }

    public static <T> T getView(View view , int id ){
        SparseArray<View> arr =(SparseArray)view.getTag();
        if (null == arr) {
            arr = new SparseArray<View>();
            view.setTag(arr);
        }
        View childView = arr.get(id);
        if (null == childView) {
            childView = view.findViewById(id);
            arr.put(id, childView);

        }
        return (T) childView;

    }

    /**
     * 判断网络是否可用
     *
     * @param context Context对象
     */
    public static Boolean isNetworkReachable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        if (current == null) {
            return false;
        }
        return (current.isAvailable());
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    public static int px2sp(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue / fontScale + 0.5f);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int SCREEN_WIDTH = 0;
    public static int getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_WIDTH = displayMetrics.widthPixels;
        return displayMetrics.widthPixels;
    }
    public static int SCREEN_HEIGHT = 0;
    public static int getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_HEIGHT = displayMetrics.heightPixels;
        return displayMetrics.heightPixels;
    }
}
