package in.skr.shivamkumar.gitsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesAdapter extends ArrayAdapter {

    ArrayList<RepositoriesContent> repositories;
    RepositoriesButtonInterface2 buttonInterface;
    LayoutInflater inflater;


    public RepositoriesAdapter(@NonNull Context context, ArrayList<RepositoriesContent> repositories,RepositoriesButtonInterface2 buttonInterface) {
        super(context,0,repositories);
        this.repositories=repositories;
        this.buttonInterface = buttonInterface;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return repositories.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View output, @NonNull ViewGroup parent) {
        if(output==null){
            output = inflater.inflate(R.layout.row_repositories,parent,false);
            TextView label2Id = output.findViewById(R.id.label2_id);
            TextView content2Id = output.findViewById(R.id.content2_id);
            TextView label2Name = output.findViewById(R.id.label2_name);
            TextView content2Name = output.findViewById(R.id.content2_name);
            TextView label2Description = output.findViewById(R.id.label2_description);
            TextView content2Description = output.findViewById(R.id.content2_description);
            TextView label2Type = output.findViewById(R.id.label2_type);
            TextView content2Type = output.findViewById(R.id.content2_type);
            TextView label2Created = output.findViewById(R.id.label2_created);
            TextView content2Created = output.findViewById(R.id.content2_created);
            TextView label2Updated = output.findViewById(R.id.label2_updated);
            TextView content2Updated = output.findViewById(R.id.content2_updated);
            Button button2 = output.findViewById(R.id.button2_openBrowser);

            RepositoriesViewHolder viewHolder = new RepositoriesViewHolder();
            viewHolder.textViewlabel2id= label2Id;
            viewHolder.textViewcontent2id=content2Id;
            viewHolder.textViewcontent2name=content2Name;
            viewHolder.textViewlabel2name=label2Name;
            viewHolder.textViewcontent2Description=content2Description;
            viewHolder.textViewlabel2Description=label2Description;
            viewHolder.textViewlabel2type=label2Type;
            viewHolder.textViewcontent2type= content2Type;
            viewHolder.textViewlabel2created=label2Created;
            viewHolder.textViewcontent2Created=content2Created;
            viewHolder.textViewlabel2updated=label2Updated;
            viewHolder.textViewcontent2Updated=content2Updated;
            viewHolder.button2 = button2;

            output.setTag(viewHolder);
        }
        RepositoriesViewHolder viewHolder = (RepositoriesViewHolder) output.getTag();
        viewHolder.textViewlabel2id.setText("ID          ");
        viewHolder.textViewcontent2id.setText(repositories.get(position).id+"");
        viewHolder.textViewlabel2name.setText("Name  ");
        viewHolder.textViewcontent2name.setText(repositories.get(position).name+"");
        viewHolder.textViewlabel2Description.setText("Description ");
        viewHolder.textViewcontent2Description.setText(repositories.get(position).description+"");
        viewHolder.textViewlabel2type.setText("Type ");
        if(repositories.get(position).type)
            viewHolder.textViewcontent2type.setText("Private");
        else
            viewHolder.textViewcontent2type.setText("Public");
        viewHolder.textViewlabel2created.setText("Created At ");
        viewHolder.textViewcontent2Created.setText(repositories.get(position).createdAt+"");
        viewHolder.textViewlabel2id.setText("Updated At ");
        viewHolder.textViewcontent2id.setText(repositories.get(position).updatedAt+"");

        viewHolder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonInterface.onButtonClicked(repositories.get(position).htmlUrl);
            }
        });

        return output;
    }
}
