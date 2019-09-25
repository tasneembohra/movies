package movies.popular.app.feature.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import movies.popular.app.R;
import movies.popular.app.feature.detail.MovieDetailActivity;
import movies.popular.app.feature.detail.MovieDetailFragment;
import movies.popular.network.model.Movie;

import static movies.popular.app.util.Constant.IMAGE_URL;


/**
 * @author tasneem
 */
public class MovieListRecyclerAdapter extends RecyclerView.Adapter<MovieListRecyclerAdapter.ViewHolder> {

    private final MovieListActivity mParentActivity;
    private final ArrayList<Movie> mMovies;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Movie movie = (Movie) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_ITEM, movie);
            context.startActivity(intent);
        }
    };

    MovieListRecyclerAdapter(MovieListActivity parent,
                                  ArrayList<Movie> movies) {
        mMovies = movies;
        mParentActivity = parent;
    }

    @NonNull
    @Override
    public MovieListRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_content, parent, false);
        return new MovieListRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieListRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieTitleTV) AppCompatTextView mTitle;
        @BindView(R.id.movieImage) AppCompatImageView mImage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(mOnClickListener);
        }

        void bind(int position) {
            Movie movie = mMovies.get(position);
            mTitle.setText(movie.title);
            Glide.with(mParentActivity)
                    .load(String.format("%s%s", IMAGE_URL, movie.posterPath))
                    .into(mImage);

            itemView.setTag(movie);
        }
    }
}