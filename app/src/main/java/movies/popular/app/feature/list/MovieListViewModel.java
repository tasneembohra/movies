package movies.popular.app.feature.list;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

import movies.popular.app.R;
import movies.popular.app.feature.BaseViewModel;
import movies.popular.network.api.MoviesListApi;
import movies.popular.network.model.Movie;

public class MovieListViewModel extends BaseViewModel implements MoviesListApi.MoviesListApiListener  {

    public ObservableField<Integer> titleResId = new ObservableField<>();
    public ObservableBoolean loading = new ObservableBoolean(true);
    public ObservableBoolean error = new ObservableBoolean(false);

    private MoviesListApi mMoviesListApi;
    public MutableLiveData<List<Movie>> mMovieLD = new MutableLiveData<>();

    private int mPage = 1;

    public MovieListViewModel() {
        mMoviesListApi = new MoviesListApi(this);
        getPopularMovie();
    }

    public void getPopularMovie() {
        loading.set(true);
        titleResId.set(R.string.title_popular_movies);
        mMoviesListApi.getPopularMovies(mPage);
    }

    public void getTopRatedMovie() {
        loading.set(true);
        titleResId.set(R.string.title_top_movie);
        mMoviesListApi.getTopRatedMovie(mPage);
    }

    @Override
    public void onSuccess(ArrayList<Movie> list, int page, int totalPages) {
        mMovieLD.setValue(list);
        loading.set(false);
    }

    @Override
    public void onError() {
        // TODO Display Error
        loading.set(false);
    }
}