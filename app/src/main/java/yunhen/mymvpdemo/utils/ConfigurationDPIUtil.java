package yunhen.mymvpdemo.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

/**
 * Created by dongqi on 2016/8/17.
 */
public class ConfigurationDPIUtil {
    public static void initDpi(Context context) {
        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        configuration.densityDpi = DisplayMetrics.DENSITY_XHIGH ;//densityDpi 值越大，那显示时 dp对应的pix就越大
        context.getResources().updateConfiguration(configuration,displayMetrics);
    }
}
