package yunhen.mymvpdemo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;


/**
 * Created by dongqi on 2016/4/14.
 */
public class PermissionsUtil {

    private static final String TAG = "PermissionsUtil";

    // Storage Permissions
    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static final int REQUEST_LOCATION = 2;
    public static final int REQUEST_CAMERA = 3;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static String[] PERMISSIONS_CAMERA = {
            Manifest.permission.CAMERA

    };

    public interface OnPermissionListener {
        void onClick(@IdRes int viewId);
    }

    public interface OnPermissionAllowListener {
        void onClick(View view);
    }


    /**
     * 6.0权限申请target 必须在6.0 以上才有效
     * @param activity 所在的act
     * @param view 点击哪个view触发的权限申请
     * @param PERMISSION 具体的权限 如:Manifest.permission.READ_EXTERNAL_STORAGE
     * @param code
     * @param listener 有此权限然后点击所要执行的操作
     */
    public static void checkPermission(@NonNull final Activity activity, final View view,
                                       @NonNull final String PERMISSION, final int code,
                                       OnPermissionListener listener
    ) {

//        Manifest.permission.READ_EXTERNAL_STORAGE
        if (isAndroidM()) {
//            Manifest.permission.CAMERA
            int permissionState = ContextCompat.checkSelfPermission(activity, PERMISSION);
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{PERMISSION},
                        code);
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        PERMISSION)) {
                    showAlert(activity,PERMISSION,code);
                } else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{PERMISSION},
                            code);
                }
            } else {
                // Permission granted
                if (listener != null){
                    if (view == null){
                        listener.onClick(view.getId());
                    }else {
                        listener.onClick(0);
                    }

                }
            }
        } else {
            if (listener != null){
                if (view == null){
                    listener.onClick(view.getId());
                }else {
                    listener.onClick(0);
                }
            }
        }

    }


    /**
     *
     * @param activity 必填
     * @param fragment
     * @param respCode
     * @param view
     * @param permissName 权限名
     * @param listener
     */
    public static void requestPermissions(@NonNull final Activity activity,final Fragment fragment
            ,final int respCode,View view
            ,final String permissName,OnPermissionAllowListener listener){

        if (!isAndroidM()){
            //判断系统版本,小于6.0
            if (listener!=null){
                listener.onClick(view);
            }
        }else {

            boolean isFrag = isFragment(activity,fragment);
            boolean isShowReqRat = false;

            int permissionState = ContextCompat.checkSelfPermission(activity, permissName);
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                if (isFrag){
                    fragment.requestPermissions(new String[]{permissName},
                            respCode);
                }else {
                    ActivityCompat.requestPermissions(activity,
                            new String[]{permissName},
                            respCode);
                }
                // Should we show an explanation?
                if (isFrag){
                    isShowReqRat = ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            permissName);
                }else {
                    isShowReqRat = fragment.shouldShowRequestPermissionRationale(
                            permissName);
                }
                if (isShowReqRat) {
                    showAlert(activity,permissName,respCode);
                } else {
                    if (isFrag){
                        fragment.requestPermissions(new String[]{permissName},
                                respCode);
                    }else {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{permissName},
                                respCode);
                    }
                }
            } else {
                // Permission granted
                if (listener != null){
                        listener.onClick(view);
                }
            }
        }

    }

    public static String permissisionText(String text) {
        if (text.isEmpty())
            return "";
        if (text.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return "读取储存权限";
        } else if (text.equals(Manifest.permission.CAMERA)) {
            return "是否获得相机权限";
        }
        return "";
    }

    private static void showAlert(final Activity context ,final String PERMISSION,final int code){
        new AlertDialog.Builder(context).setMessage(permissisionText(PERMISSION))//设置显示的内容

                .setPositiveButton("是",new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        ActivityCompat.requestPermissions(context,
                                new String[]{PERMISSION},
                                code);
                    }
                }).setNegativeButton("否",null).show();//在按键响应事件中显示此对话框
    }

    /**
     * fragment用的权限申请
     *
     * @param fragment   所在的fragment
     * @param view       点击哪个view触发权限申请
     * @param listener   正常点击时的操作
     * @param PERMISSION 权限 如:Manifest.permission.READ_EXTERNAL_STORAGE
     */
    public static void checkFragmentPermission(@NonNull final Fragment fragment, final View view, @NonNull final String PERMISSION,
                                               final int code,
                                               OnPermissionListener listener
    ) {
//        Manifest.permission.READ_EXTERNAL_STORAGE
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
//            int permission = ContextCompat.checkSelfPermission(fragment.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
            int permissionState = ActivityCompat.checkSelfPermission(fragment.getActivity(), PERMISSION);
            if (permissionState != PackageManager.PERMISSION_GRANTED) {
                fragment.requestPermissions(new String[]{PERMISSION},
                        code);
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(fragment.getActivity(),
                        PERMISSION)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    showAlert(fragment.getActivity(),PERMISSION,code);
                } else {
                    fragment.requestPermissions(new String[]{PERMISSION},
                            code);
                }
            } else {
                // Permission granted
                if (listener != null){
                    if (view == null){
                        listener.onClick(view.getId());
                    }else {
                        listener.onClick(0);
                    }
                }
            }
        } else {
            if (listener != null){
                if (view == null){
                    listener.onClick(view.getId());
                }else {
                    listener.onClick(0);
                }
            }
        }

    }


    public static boolean isAndroidM() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isFragment(Activity activity,Fragment fragment){
        if (fragment!= null){
            return true;
        }
        if (activity!= null){
            return false;
        }
        return false;
    }


}
