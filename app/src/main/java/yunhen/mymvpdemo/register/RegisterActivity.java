package yunhen.mymvpdemo.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import yunhen.mymvpdemo.R;
import yunhen.mymvpdemo.Utils;
import yunhen.mymvpdemo.base.BaseActivity;

/**
 * Created by dongqi on 2016/8/9.
 */
public class RegisterActivity extends BaseActivity<IRegisterView,RegisterPresenter> implements IRegisterView {

    private TextInputEditText tietPhone,tietCode;

    @Override
    public void showToast(String msg) {
        Toast.makeText(RegisterActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    private Button btnCode,btnSub;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tietPhone = Utils.$(this,R.id.tietPhone);
        tietCode = Utils.$(this,R.id.tietCode);
        btnCode = Utils.$(this,R.id.btnCode);
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.updateCodeText();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public String getPhone() {
        return tietPhone.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return tietCode.getText().toString().trim();
    }

    @Override
    public void setCodeBtnText(String s) {
        btnCode.setText(s);
    }

    @Override
    public void setCodeBtnEnabled(boolean enabled) {
        btnCode.setEnabled(enabled);
    }
}
