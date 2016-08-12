package yunhen.mymvpdemo.login;

import yunhen.mymvpdemo.base.IBaseVIew;

/**
 * Created by dongqi on 2016/8/9.
 */
public interface ILogin extends IBaseVIew {


    String getUsername();

    String getCode();

    void showProgress();
    void hideProgress();

    void setUsernameErrorText(String s);

    void setCodeErrorText(String s);

}
