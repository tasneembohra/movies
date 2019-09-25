package movies.popular.app.feature.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import movies.popular.app.R;
import movies.popular.app.databinding.MovieDetailBinding;
import movies.popular.app.feature.BaseFragment;
import movies.popular.app.feature.list.MovieListActivity;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends BaseFragment<MovieDetailBinding, MovieDetailViewModel> {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() { }

    @Override
    protected Integer getLayout() {
        return R.layout.movie_detail;
    }

    @Override
    protected Class<MovieDetailViewModel> getViewModelClass() {
        return MovieDetailViewModel.class;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        mBinding.movieDetail.setText(mViewModel.movie.overview);
        try {
            Locale locale = new Locale("en", "IN");
            Date date = new SimpleDateFormat(getString(R.string.old_date_format), locale).parse(mViewModel.movie.releaseDate);
            mViewModel.movie.releaseDate = new SimpleDateFormat(getString(R.string.new_date_format), locale).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mBinding.releaseDateTV.append("  "+mViewModel.movie.releaseDate);
        mBinding.movieRating.setRating(mViewModel.movie.voteAverage);
        mBinding.ratingTV.append(String.format("%s", mViewModel.movie.voteAverage));
    }
}
