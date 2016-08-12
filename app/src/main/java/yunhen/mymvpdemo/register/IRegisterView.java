package yunhen.mymvpdemo.register;

import yunhen.mymvpdemo.base.IBaseVIew;

/**
 * Created by dongqi on 2016/8/9.
 * 主要对view操作,比如从view获取text,或者设置view的text,进度条的更新和toast的显示
 */
public interface IRegisterView extends IBaseVIew {
    String getPhone();
    String getCode();
    void setCodeBtnText(String s);
    void setCodeBtnEnabled(boolean enabled);
}
