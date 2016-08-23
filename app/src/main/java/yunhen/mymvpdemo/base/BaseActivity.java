package yunhen.mymvpdemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dongqi on 2016/8/9.
 */
public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity
        implements IBaseVIew {
    protected T mPresenter;
    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = createPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 设置setContentView
     * @return layout.xml
     */
    protected abstract int getLayoutId();

    ProgressDialog progressDialog;

    protected void showProgressDialog(String msg,boolean cancel){
        progressDialog = new ProgressDialog(BaseActivity.this);
        if (msg == null)
            msg = "正在加载...";
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(cancel);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
            }
        });

    }

    protected void hideProgressDialog(){
        if (progressDialog!= null && progressDialog.isShowing()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            progressDialog = null;
        }

    }
}
