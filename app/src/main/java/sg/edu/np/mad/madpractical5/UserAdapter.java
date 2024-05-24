package sg.edu.np.mad.madpractical5;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> requser;
    Context context;

    public UserAdapter(ArrayList<User> input, Context context){
        requser = input;
        this.context=context;
    }
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list,parent,false);
        return new UserViewHolder(item);
    }
    public void onBindViewHolder(UserViewHolder holder, int position){
        User s = requser.get(position);
        holder.name.setText(s.getName());
        holder.description.setText(s.getDescription());
        if(s.getName().endsWith("7")){
            holder.bigImage.setVisibility(View.VISIBLE);
        }else{
            holder.bigImage.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(s.getName());
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name = s.getName();
                        String description = s.getDescription();
                        int idd = s.getId();
                        boolean followeds = s.getFollowed();
                        String idstring = String.valueOf(idd);
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("key", name);
                        intent.putExtra("key1", description);
                        intent.putExtra("key2",idstring);
                        intent.putExtra("key3", followeds);
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    public int getItemCount(){
        return requser.size();
    }



}
