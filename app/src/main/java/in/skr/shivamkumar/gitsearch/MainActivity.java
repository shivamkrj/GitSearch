package in.skr.shivamkumar.gitsearch;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewName;
    ImageView imageViewPhoto;
    EditText editText;
    String follower_URL;
    String repositariesUrl;
    String url;
    String cURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = findViewById(R.id.textView4);
        imageViewPhoto = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        url = "https://api.github.com/users/";
        cURL="";
    }

    public void button(View view){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        GitMainServices service = retrofit.create(GitMainServices.class);
        if(cURL==null||cURL.length()==0)
            cURL = editText.getText().toString();
        else
            editText.setText(cURL);
        Call<GitMainResopnse> call = service.getDetails(cURL);

        call.enqueue(new Callback<GitMainResopnse>() {
            @Override
            public void onResponse(Call<GitMainResopnse> call, Response<GitMainResopnse> response) {
                GitMainResopnse mainResopnse = response.body();
                if(mainResopnse==null)
                {
                    Toast.makeText(MainActivity.this,"Invalid Url",Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = mainResopnse.id;
               // Toast.makeText(MainActivity.this,"reached"+id,Toast.LENGTH_SHORT).show();
                String name = mainResopnse.name;
                if(name!=null){
                    textViewName.setText(name);
                    textViewName.setVisibility(View.VISIBLE);
                }
                follower_URL = mainResopnse.followers_url;
                repositariesUrl = mainResopnse.repos_url;
                String imageUrl = mainResopnse.avatar_url;
                Picasso.get().load(imageUrl).into(imageViewPhoto);

            }
            @Override
            public void onFailure(Call<GitMainResopnse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Invalid Username",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void fetchData(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        GitMainServices service = retrofit.create(GitMainServices.class);
        if(cURL==null||cURL.length()==0)
            cURL = editText.getText().toString();
        else
            editText.setText(cURL);
        Call<GitMainResopnse> call = service.getDetails(cURL);

        call.enqueue(new Callback<GitMainResopnse>() {
            @Override
            public void onResponse(Call<GitMainResopnse> call, Response<GitMainResopnse> response) {
                GitMainResopnse mainResopnse = response.body();
                if(mainResopnse==null)
                {
                    Toast.makeText(MainActivity.this,"Invalid Url",Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = mainResopnse.id;
                // Toast.makeText(MainActivity.this,"reached"+id,Toast.LENGTH_SHORT).show();
                String name = mainResopnse.name;
                if(name!=null){
                    textViewName.setText(name);
                    textViewName.setVisibility(View.VISIBLE);
                }
                follower_URL = mainResopnse.followers_url;
                repositariesUrl = mainResopnse.repos_url;
                String imageUrl = mainResopnse.avatar_url;
                Picasso.get().load(imageUrl).into(imageViewPhoto);

            }
            @Override
            public void onFailure(Call<GitMainResopnse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Invalid Username",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void repositories(View view){

    }
    public void followers(View view){
        Intent intent = new Intent(this,Followers.class);
        String sendUrl = url+cURL;
        intent.putExtra("URL",sendUrl);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==2){
                String k = data.getStringExtra("URL");
                cURL=k;
                fetchData();
            }
        }
    }
}
