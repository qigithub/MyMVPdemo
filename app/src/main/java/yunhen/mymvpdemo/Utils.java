package yunhen.mymvpdemo;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
}
