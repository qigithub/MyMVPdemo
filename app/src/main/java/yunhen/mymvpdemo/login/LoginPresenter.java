package yunhen.mymvpdemo.login;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import yunhen.mymvpdemo.Utils;
import yunhen.mymvpdemo.base.BasePresenter;
import yunhen.mymvpdemo.http.ApiService;
import yunhen.mymvpdemo.http.HttpClient;
import yunhen.mymvpdemo.http.MyConstants;
import yunhen.mymvpdemo.register.RegisterActivity;

/**
 * Created by dongqi on 2016/8/9.
 */
public class LoginPresenter extends BasePresenter<ILogin> {
    private final String TAG=LoginPresenter.class.getSimpleName();
    protected ILogin iView;
    public LoginPresenter(ILogin iView){
        this.attchView(iView);
        this.iView = getIView();
    }

    public void toRegisterAct(){
        Intent intent = new Intent(iView.getContext(), RegisterActivity.class);
        iView.getContext().startActivity(intent);
    }


    public void startLogin(){
        if (TextUtils.isEmpty(iView.getUsername())){
            iView.setUsernameErrorText("请输入用户名/手机号");
            return;
        }
        if (TextUtils.isEmpty(iView.getCode())){
            iView.setCodeErrorText("请输入验证码");
            return;
        }

        iView.showProgress();
        HttpClient.getInstance(MyConstants.HTTP_IP,iView.getContext())
                .createService(ApiService.class)
                .login(new LoginReq(iView.getUsername(),iView.getCode()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<LoginResp>() {
                @Override
                public void onCompleted() {
                    iView.hideProgress();
                }

                @Override
                public void onError(Throwable e) {
                    try {
                        iView.showToast("登陆异常 -> " +e.getMessage());
                        iView.hideProgress();
                    } catch (Exception e1) {
                          e1.printStackTrace();
                    }
                }

                @Override
                public void onNext(LoginResp loginResp) {
                    iView.showToast("登陆成功");
                    iView.hideProgress();
                }
            });

    }

    public void testFlatMap(){
        LoginReq[] arr = {new LoginReq("1","1"),new LoginReq("2","2"),new LoginReq("2","2")};
        Observable.from(arr).flatMap(new Func1<LoginReq, Observable<String>>() {
            @Override
            public Observable<String> call(LoginReq loginReq) {
                return Observable.just(loginReq.phone);
            }
        });
    }

    public void setScreenInfo(){
        Activity activity = (Activity) iView.getContext();
        iView.setTvInfo("width : "+Utils.SCREEN_WIDTH+ "\n" +"height : "+Utils.SCREEN_HEIGHT);
    }

}
