package movies.popular.app.feature;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<B extends ViewDataBinding, V extends BaseViewModel> extends Fragment {
    protected B mBinding;
    protected V mViewModel;

    protected abstract Integer getLayout();
    protected abstract Class<V> getViewModelClass();
    protected abstract void init(@Nullable Bundle savedInstanceState);

    @SuppressWarnings("unchecked")
    private B bindView(LayoutInflater inflater, ViewGroup parent) {
        return (B) DataBindingUtil.inflate(inflater, getLayout(), parent, false);
    }

    private V getViewModel() {
        return ViewModelProviders.of(requireActivity()).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = bindView(inflater, container);
        mViewModel = getViewModel();
        init(savedInstanceState);
        return mBinding.getRoot();
    }
}
