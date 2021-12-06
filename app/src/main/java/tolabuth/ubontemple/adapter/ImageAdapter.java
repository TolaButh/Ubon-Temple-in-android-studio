package tolabuth.ubontemple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.util.List;


import tolabuth.ubontemple.R;
import tolabuth.ubontemple.model.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
   private Context context;
   private List<Image>images;

    public ImageAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image  = images.get(position);
        String description = image.getDescription();
        String imgName = image.getImageName();

        String imgUrl = "http://s62122420127.cs.ubru.ac.th/ubontemple/images/"+imgName;
        holder.tvDescription.setText(description);
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.imgTempleName);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgTempleName;
        public TextView tvDescription;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTempleName = itemView.findViewById(R.id.img_temple_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
