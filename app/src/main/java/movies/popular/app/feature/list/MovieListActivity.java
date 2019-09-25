package movies.popular.app.feature.list;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import movies.popular.app.R;
import movies.popular.app.databinding.ActivityMovieListBinding;
import movies.popular.app.feature.BaseActivity;
import movies.popular.network.model.Movie;

/**
 * @author tasneem
 */
public class MovieListActivity extends BaseActivity<ActivityMovieListBinding, MovieListViewModel> {
    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private MovieListRecyclerAdapter mAdapter;


    @Override
    protected Integer getLayout() {
        return R.layout.activity_movie_list;
    }

    @Override
    protected Class<MovieListViewModel> getViewModelClass() {
        return MovieListViewModel.class;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

        mBinding.setViewModel(mViewModel);

        setSupportActionBar(mBinding.toolbar);

        setupRecyclerView();

        mViewModel.mMovieLD.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies == null) return;
                mMovieList.clear();
                mMovieList.addAll(movies);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerView() {
        mAdapter = new MovieListRecyclerAdapter(mMovieList);
        mBinding.itemLayout.movieList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                mViewModel.getPopularMovie();
                return true;
            case R.id.topRated:
                mViewModel.getTopRatedMovie();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
