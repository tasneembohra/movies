package movies.popular.app.util;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BindingAdapter {

    @android.databinding.BindingAdapter("titleResId")
    public static void setToolbarTitle(Toolbar toolbar, @StringRes Integer resId) {
        if (resId != null) toolbar.setTitle(resId);
    }

    @android.databinding.BindingAdapter("visibility")
    public static void setVisibility(View view, Boolean visible) {
        view.setVisibility(visible != null && visible ? View.VISIBLE : View.GONE);
    }
}
