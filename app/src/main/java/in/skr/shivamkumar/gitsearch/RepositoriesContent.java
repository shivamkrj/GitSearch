package in.skr.shivamkumar.gitsearch;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Target;

import retrofit2.http.Query;

public class RepositoriesContent {
    String name;
    String description;
    @SerializedName("private")
    boolean type;
    @SerializedName("html_url")
    String htmlUrl;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("updated_at")
    String updatedAt;
    @SerializedName("pushed_at")
    String pushedAt;

}
