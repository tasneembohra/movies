package movies.popular.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import movies.popular.network.model.Movie;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {

    @BindView(R.id.movie_detail) TextView mOverviewTV;
    @BindView(R.id.releaseDateTV) TextView mReleaseDateTV;
    @BindView(R.id.movieRating) RatingBar mRatingBar;
    @BindView(R.id.ratingTV) TextView mRatingTV;


    /**
     * The fragment argument representing the item ID that this fragment represents.
     */
    public static final String ARG_ITEM = "item";

    private Movie mMovie;
    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mMovie = (Movie) getArguments().getSerializable(ARG_ITEM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        mOverviewTV.setText(mMovie.overview);
        try {
            Locale locale = new Locale("en", "IN");
            Date date = new SimpleDateFormat(getString(R.string.old_date_format), locale).parse(mMovie.releaseDate);
            mMovie.releaseDate = new SimpleDateFormat(getString(R.string.new_date_format), locale).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mReleaseDateTV.append("  "+mMovie.releaseDate);
        mRatingBar.setRating(mMovie.voteAverage);
        mRatingTV.append(String.format("%s", mMovie.voteAverage));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
