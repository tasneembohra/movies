package movies.popular.app.util;

import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static movies.popular.app.util.Constant.IMAGE_URL;

public class BindingAdapter {

    @android.databinding.BindingAdapter("titleResId")
    public static void setToolbarTitle(Toolbar toolbar, @StringRes Integer resId) {
        if (resId != null) toolbar.setTitle(resId);
    }

    @android.databinding.BindingAdapter("visibility")
    public static void setVisibility(View view, Boolean visible) {
        view.setVisibility(visible != null && visible ? View.VISIBLE : View.GONE);
    }

    @android.databinding.BindingAdapter("src")
    public static void setImage(ImageView imageView, String url) {
        if (url == null) return;
        Glide.with(imageView.getContext())
                .load(String.format("%s%s", IMAGE_URL, url))
                .into(imageView);
    }
}
