package in.skr.shivamkumar.gitsearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitMainServices {
    @GET("{x}")
    Call<GitMainResopnse> getDetails(@Path("x") String ur);
}
