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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repositories extends AppCompatActivity implements checkInterface{

    ListView listView;
    ArrayList<RepositoriesContent> repositories;
    String receivedUrl;
    String xyz="sdkjf";
    RepositoriesAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        intent = getIntent();
        receivedUrl = intent.getStringExtra("URL")+"/";
        repositories = new ArrayList<>();
        listView= findViewById(R.id.listView2);
        adapter = new RepositoriesAdapter(this, repositories, new RepositoriesButtonInterface2() {
            @Override
            public void onButtonClicked(String url) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(url);
                i.setData(uri);
                startActivity(i);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                xyz="helkf";
                Toast.makeText(Repositories.this,"Item Clicked",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
        fetchData();
    }

    private void fetchData() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(receivedUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RepositoriesService service = retrofit.create(RepositoriesService.class);
        Call<ArrayList<RepositoriesContent>> call = service.getDetails("repos");

        call.enqueue(new Callback<ArrayList<RepositoriesContent>>() {
            @Override
            public void onResponse(Call<ArrayList<RepositoriesContent>> call, Response<ArrayList<RepositoriesContent>> response) {

                ArrayList<RepositoriesContent> mainRes= response.body();

                for(int i=0;i<mainRes.size();i++){
                    RepositoriesContent mainResopnse = mainRes.get(i);
                    if(mainResopnse==null)
                    {
                        Toast.makeText(Repositories.this,"Invalid Url",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    RepositoriesContent repositoriesContent = new RepositoriesContent();
                    repositoriesContent.id=mainResopnse.id;
                    repositoriesContent.name=mainResopnse.name;
                    repositoriesContent.description=mainResopnse.description;
                    repositoriesContent.type=mainResopnse.type;
                    repositoriesContent.htmlUrl=mainResopnse.htmlUrl;
                    repositoriesContent.createdAt=mainResopnse.createdAt;
                    repositoriesContent.updatedAt=mainResopnse.updatedAt;
                    repositoriesContent.pushedAt=mainResopnse.pushedAt;
                    repositoriesContent.language=mainResopnse.language;

                    repositories.add(repositoriesContent);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RepositoriesContent>> call, Throwable t) {
                Toast.makeText(Repositories.this,"Invalid Username",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheck() {

    }
}
