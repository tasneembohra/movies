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
import movies.popular.app.databinding.MovieListBinding;
import movies.popular.app.databinding.MovieListContentBinding;
import movies.popular.app.feature.detail.MovieDetailActivity;
import movies.popular.app.feature.detail.MovieDetailFragment;
import movies.popular.network.model.Movie;

import static movies.popular.app.util.Constant.IMAGE_URL;


/**
 * @author tasneem
 */
public class MovieListRecyclerAdapter extends RecyclerView.Adapter<MovieListRecyclerAdapter.ViewHolder> {
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

    MovieListRecyclerAdapter(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @NonNull
    @Override
    public MovieListRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieListContentBinding binding = MovieListContentBinding.inflate(inflater, parent, false);
        return new MovieListRecyclerAdapter.ViewHolder(binding);
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
        private final MovieListContentBinding binding;

        ViewHolder(MovieListContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(mOnClickListener);
        }

        void bind(int position) {
            Movie movie = mMovies.get(position);
            binding.setImageUrl(movie.posterPath);
            binding.setTitle(movie.title);
            itemView.setTag(movie);
        }
    }
}