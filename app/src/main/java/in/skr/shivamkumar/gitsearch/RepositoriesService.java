package in.skr.shivamkumar.gitsearch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepositoriesService {
    @GET("{x}")
    Call<ArrayList<RepositoriesContent>> getDetails(@Path("x") String url);
}
