package yunhen.mymvpdemo.register;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import yunhen.mymvpdemo.R;
import yunhen.mymvpdemo.base.BasePresenter;
import yunhen.mymvpdemo.http.ApiService;
import yunhen.mymvpdemo.http.BaseRequest;
import yunhen.mymvpdemo.http.BaseResponse;
import yunhen.mymvpdemo.http.HttpClient;
import yunhen.mymvpdemo.http.MyConstants;
import yunhen.mymvpdemo.login.LoginReq;
import yunhen.mymvpdemo.login.LoginResp;

/**
 * Created by dongqi on 2016/8/9.
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    private final int CODE_TIME_MAX_LENGTH=15;
    IRegisterView iVIew;
    public RegisterPresenter(IRegisterView iView){
        this.attchView(iView);
        iVIew = getIView();
    }

    public void updateCodeText(){
        iVIew.showToast("已发送");
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())//指Observable.interval
                .observeOn(AndroidSchedulers.mainThread());//指回调Subscriber<Long> subscriber
        final Subscriber<Long> subscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Long aLong) {
                long l = aLong + 1;
                iVIew.setCodeBtnText(l+" S");
                if (aLong >= CODE_TIME_MAX_LENGTH ){
                    onCompleted();
                    unsubscribe();
                    iVIew.setCodeBtnText(iVIew.getContext().getString(R.string.text_code_btn_def));
                    iVIew.setCodeBtnEnabled(true);
                }else {
                    iVIew.setCodeBtnEnabled(false);
                }
            }
        };
        observable.subscribe(subscriber);

        HttpClient.getInstance(MyConstants.HTTP_IP,iVIew.getContext()).createService(ApiService.class)
                .getSmsCode(new GetSmsCodeReq(iVIew.getPhone()))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<GetSmsCodeResp>() {
            @Override
            public void call(GetSmsCodeResp getSmsCodeResp) {

            }
        });

    }



}
