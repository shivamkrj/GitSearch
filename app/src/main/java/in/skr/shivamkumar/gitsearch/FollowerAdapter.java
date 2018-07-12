package in.skr.shivamkumar.gitsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FollowerAdapter extends ArrayAdapter {

    ArrayList<FollowerContent> followers;
    LayoutInflater inflater;
    Context context;
    FollowersButtonInterface buttonInterface;
    String imgUrl="https://avatars1.githubusercontent.com/u/33348791?v=4";

    public FollowerAdapter(@NonNull Context context,ArrayList<FollowerContent> followers,FollowersButtonInterface buttonInterface) {
        super(context,0, followers);
        this.followers= followers;
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.buttonInterface = buttonInterface;
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View output, @NonNull ViewGroup parent) {
        if(output==null){
            output = inflater.inflate(R.layout.row_followers_layout,parent,false);
            TextView textViewLabelId = output.findViewById(R.id.label_id);
            TextView textViewId = output.findViewById(R.id.id_content);
            TextView textViewLogin = output.findViewById(R.id.login_content);
            TextView textViewLabelName = output.findViewById(R.id.label_login);
            ImageView imageView = output.findViewById(R.id.imageView);
            Button button = output.findViewById(R.id.button_openBrowser);

            FollowersViewHolder viewHolder = new FollowersViewHolder();
            viewHolder.labelName = textViewLabelName;
            viewHolder.idContent = textViewId;
            viewHolder.labelId = textViewLabelId;
            viewHolder.loginName = textViewLogin;
            viewHolder.button = button;
            viewHolder.imageView = imageView;

            output.setTag(viewHolder);
        }
        FollowersViewHolder viewHolder = (FollowersViewHolder)output.getTag();
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonInterface.onButtonClick(followers.get(position).html_url);
            }
        });
        viewHolder.labelName.setText("Login Name ");
        viewHolder.labelId.setText("ID       ");
        String string = followers.get(position).login;
        viewHolder.loginName.setText(string);
        int i = followers.get(position).id;
        viewHolder.idContent.setText(i+"");
        ImageView imageView = viewHolder.imageView;

//        Picasso.with(context).load("https://i.imgur.com/tGbaZCY.jpg").into(imageView);

        return output;
    }
}
