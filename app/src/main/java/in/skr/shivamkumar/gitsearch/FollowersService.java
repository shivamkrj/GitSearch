package in.skr.shivamkumar.gitsearch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FollowersService {
    @GET("{x}")
    Call<ArrayList<FollowersResponse>> getDetails(@Path("x") String ur);
}
