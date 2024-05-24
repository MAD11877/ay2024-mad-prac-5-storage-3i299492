package sg.edu.np.mad.madpractical5;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage;
    TextView name;
    TextView description;
    ImageView bigImage;
    public UserViewHolder(View itemView){
        super(itemView);
        smallImage = itemView.findViewById(R.id.smallim);
        name = itemView.findViewById(R.id.namey);
        description = itemView.findViewById(R.id.descd1);
        bigImage = itemView.findViewById(R.id.thebigbigimage);
    }
}
