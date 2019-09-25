package movies.popular.network.model;

import com.google.gson.annotations.SerializedName;

public class Trailer {
    @SerializedName("id")
    public String id;
    @SerializedName("key")
    public String key;
    @SerializedName("name")
    public String name;
    @SerializedName("site")
    public String site;
    @SerializedName("size")
    public Long size;
    @SerializedName("type")
    public String type;
}
