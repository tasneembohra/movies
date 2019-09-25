package movies.popular.app.feature.detail;

import android.databinding.ObservableField;

import movies.popular.app.feature.BaseViewModel;
import movies.popular.network.model.Movie;

public class MovieDetailViewModel extends BaseViewModel {
    public ObservableField<String> bannerImageUrl = new ObservableField<>();
    public Movie movie;
}
