package yunhen.mymvpdemo;



import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import yunhen.mymvpdemo.base.BaseActivity;
import yunhen.mymvpdemo.login.ILogin;
import yunhen.mymvpdemo.login.LoginPresenter;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<ILogin,LoginPresenter> implements ILogin {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView mImageView;
    DrawableTypeRequest dtReq;

    @Override
    public void setUsernameErrorText(String s) {
        mEmailView.setError(s);
    }

    @Override
    public void setCodeErrorText(String s) {
        mPasswordView.setError(s);
    }

    @Override
    public void showProgress() {
        this.showProgressDialog(null,false);
    }

    @Override
    public void hideProgress() {
        this.hideProgressDialog();
    }


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        dtReq = Glide.with(LoginActivity.this)
                .load("https://tse1-mm.cn.bing.net/th?id=OIP.Ma10427326d57a9635c749e2b2225187co0&w=231&h=143&c=7&rs=1&qlt=90&o=4&pid=1.1")
        ;
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    mPresenter.startLogin();
                    return true;
                }
                return false;
            }
        });
        mEmailView.setText("13511111111");
        mPasswordView.setText("123456");
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startLogin();
            }
        });

        Button btnToRegister = Utils.$(this,R.id.btnToRegister);
        btnToRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.toRegisterAct();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mImageView = Utils.$(this,R.id.mImageView);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //测试eventbus
                EventBus.getDefault().post(new EventBusMsg.Login());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public String getUsername() {
        return mEmailView.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return mPasswordView.getText().toString().trim();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(LoginActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void eBusMyEvent(EventBusMsg.Login e){
        dtReq.centerCrop().placeholder(R.mipmap.herahera)
                .dontTransform()//禁用马赛克效果
                .override(200,200)
                .thumbnail(0.9f)
                .error(R.mipmap.herahera)
                .listener(new RequestListener() {
                    @Override
                    public boolean onException(Exception e, Object model, Target target,
                                               boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Object resource, Object model, Target target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(mImageView);

        mEmailView.append("");

    }


}

