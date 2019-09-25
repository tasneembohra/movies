package movies.popular.app.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import movies.popular.app.R;
import movies.popular.app.databinding.ActivityMovieDetailBinding;
import movies.popular.app.feature.BaseActivity;
import movies.popular.app.feature.list.MovieListActivity;
import movies.popular.network.model.Movie;

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MovieListActivity}.
 */
public class MovieDetailActivity extends BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>  {

    private static final String EXTRA_MOVIE_DATA = "movie_data";

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE_DATA, movie);
        return intent;
    }

    @Override
    protected Integer getLayout() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected Class<MovieDetailViewModel> getViewModelClass() {
        return MovieDetailViewModel.class;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        mBinding.setViewModel(mViewModel);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE_DATA);

        setSupportActionBar(mBinding.detailToolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(movie.title);
        }

        mViewModel.bannerImageUrl.set(movie.backdropPath);
        mViewModel.movie = movie;

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            MovieDetailFragment fragment = new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
