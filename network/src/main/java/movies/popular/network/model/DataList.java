package movies.popular.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataList<T> {
    @SerializedName("id")
    public Integer id;
    @SerializedName("results")
    public List<T> results;
    @SerializedName("page")
    public Integer page;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("total_results")
    public Integer totalResult;
}
