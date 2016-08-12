package yunhen.mymvpdemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dongqi on 2016/8/9.
 */
public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment
        implements IBaseVIew {
    protected T mPresenter;
    protected abstract T createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),null);
        createView(view);
        return view;
    }

    @Override
    public Context getContext() {
        return getContext();
    }

    protected abstract void createView(View view);

    protected abstract int getLayoutId();

    ProgressDialog progressDialog;

    protected void showProgressDialog(String msg,boolean cancel){
        progressDialog = new ProgressDialog(getActivity());
        if (msg == null)
            msg = "正在加载...";
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(cancel);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.show();
            }
        });

    }

    protected void hideProgressDialog(){
        if (progressDialog!= null && progressDialog.isShowing()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
            progressDialog = null;
        }

    }
}
