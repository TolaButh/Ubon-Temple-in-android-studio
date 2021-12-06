package tolabuth.ubontemple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tolabuth.ubontemple.R;
import tolabuth.ubontemple.model.Temple;

public class TempleAdapter extends RecyclerView.Adapter<TempleAdapter.TempleViewHolder> {
    private Context context;
    private List<Temple>temples;
    private OnItemClickListener listener;

    public TempleAdapter(Context context, List<Temple> temples) {
        this.context = context;
        this.temples = temples;
    }

    @NonNull
    @Override
    public TempleAdapter.TempleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.temple_item, parent, false);
        return new TempleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempleAdapter.TempleViewHolder holder, int position) {
        Temple temple = temples.get(position);
        holder.tvName.setText(temple.getName());
        holder.tvCity.setText(temple.getCity());
        String imgName =temple.getImage();
        String imgUrl = "http://s62122420127.cs.ubru.ac.th/ubontemple/images/"+imgName;

        Picasso.get().load(imgUrl).fit().centerCrop().into(holder.imgTemple);



    }

    @Override
    public int getItemCount() {
        return temples.size();
    }

    public class TempleViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCity;
        ImageView imgTemple;
        public TempleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =itemView.findViewById(R.id.tv_temple_name);
            tvCity = itemView.findViewById(R.id.tv_temple_city);
            imgTemple = itemView.findViewById(R.id.img_temple);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener !=  null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);

                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
