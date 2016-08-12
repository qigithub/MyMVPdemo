package yunhen.mymvpdemo.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;
import yunhen.mymvpdemo.login.LoginReq;
import yunhen.mymvpdemo.login.LoginResp;
import yunhen.mymvpdemo.register.GetSmsCodeReq;
import yunhen.mymvpdemo.register.GetSmsCodeResp;

/**
 * Created by dongqi on 2016/8/10.
 * 服务器地址后缀
 */
public interface ApiService {
    /*
    * 括号里的不要以/开头
    * @FormUrlEncoded
    *
    * @Multipart
    * */
    @POST("/login/")
    Observable<LoginResp> login(@Body LoginReq baseResponse);

    @POST("/getSmsCode")
    Observable<GetSmsCodeResp> getSmsCode(@Body GetSmsCodeReq b);

}
