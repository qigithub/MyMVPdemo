package yunhen.mymvpdemo.register;

import yunhen.mymvpdemo.http.BaseRequest;

/**
 * Created by dongqi on 2016/8/10.
 */
public class GetSmsCodeReq extends BaseRequest {

    /**
     * phone : 18310648331
     * type : 1
     */

    public String phone;
    public String type;

    public GetSmsCodeReq(String phone){
        this.phone = phone;
        this.type = "2";
    }
}
