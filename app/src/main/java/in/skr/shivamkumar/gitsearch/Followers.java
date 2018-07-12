package in.skr.shivamkumar.gitsearch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Followers extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<FollowerContent> followers;
    String receivedUrl;
    FollowerAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        followers= new ArrayList<>();
        listView= findViewById(R.id.listView);
        adapter = new FollowerAdapter(this, followers, new FollowersButtonInterface() {
            @Override
            public void onButtonClick(String url) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW
                );
                Uri uri = Uri.parse(url);
                i.setData(uri);
                startActivity(i);
            }
        });
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
        intent = getIntent();
        receivedUrl= intent.getStringExtra("URL")+"/";

        fetchData();
    }

    private void fetchData() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(receivedUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        FollowersService service = retrofit.create(FollowersService.class);
        Call<ArrayList<FollowersResponse>> call = service.getDetails("followers");

        call.enqueue(new Callback<ArrayList<FollowersResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<FollowersResponse>> call, Response<ArrayList<FollowersResponse>> response) {

                ArrayList<FollowersResponse> mainRes= response.body();

                for(int i=0;i<mainRes.size();i++){
                    FollowersResponse mainResopnse = mainRes.get(i);
                    if(mainResopnse==null)
                    {
                        Toast.makeText(Followers.this,"Invalid Url",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String name = mainResopnse.login;
                    Log.d("response","r "+name);

                    FollowerContent followerContent= new FollowerContent();
                    followerContent.id=mainResopnse.id;
                    followerContent.login= mainResopnse.login;
                    followerContent.html_url= mainResopnse.html_url;
                    followerContent.url = mainResopnse.url;

                    followers.add(followerContent);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<FollowersResponse>> call, Throwable t) {
                Toast.makeText(Followers.this,"Invalid Username",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String eUrl = followers.get(i).login;
        Toast.makeText(this,eUrl,Toast.LENGTH_SHORT).show();
        intent.putExtra("URL",eUrl);
        setResult(2,intent);
        finish();
    }
}
