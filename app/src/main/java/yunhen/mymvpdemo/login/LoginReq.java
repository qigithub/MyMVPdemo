package yunhen.mymvpdemo.login;

import yunhen.mymvpdemo.http.BaseRequest;

/**
 * Created by dongqi on 2016/8/10.
 */
public class LoginReq extends BaseRequest {

    /**
     * phone : 18310648331
     * smsCode : 123456
     */

    public String phone;
    public String smsCode;

    public LoginReq(String phone,String smsCode){
        this.phone = phone ;
        this.smsCode = smsCode;
    }
}
