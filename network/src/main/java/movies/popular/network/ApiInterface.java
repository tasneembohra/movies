package movies.popular.network;


import movies.popular.network.model.DataList;
import movies.popular.network.model.MovieList;
import movies.popular.network.model.Review;
import movies.popular.network.model.Trailer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/movie/{id}/videos")
    Call<DataList<Trailer>> getTrailer(@Query("api_key") String apiKey, @Path("id") Integer id);

    @GET("/movie/{id}/reviews")
    Call<DataList<Review>> getReviews(@Query("api_key") String apiKey, @Path("id") Integer id);
}
