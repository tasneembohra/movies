package movies.popular.app.feature;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity<B extends ViewDataBinding , V extends BaseViewModel> extends AppCompatActivity {

    protected B mBinding;
    protected V mViewModel;

    protected abstract Integer getLayout();
    protected abstract Class<V> getViewModelClass();
    protected abstract void init();

    @SuppressWarnings("unchecked")
    private B bindView() {
        return (B) DataBindingUtil.setContentView(this, getLayout());
    }

    private V getViewModel() {
        return ViewModelProviders.of(this).get(getViewModelClass());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = bindView();
        mViewModel = getViewModel();
        init();
    }
}
