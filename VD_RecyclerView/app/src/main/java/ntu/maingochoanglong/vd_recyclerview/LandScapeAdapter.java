package ntu.maingochoanglong.vd_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {

    Context context;
    ArrayList<LandScape> landScapeList;

    public LandScapeAdapter(Context context, ArrayList<LandScape> landScapeList) {
        this.context = context;
        this.landScapeList = landScapeList;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.item_land, parent, false);
        ItemLandHolder holder = new ItemLandHolder(viewItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        LandScape landScapeHienThi = landScapeList.get(position);
        String caption = landScapeHienThi.getLandCaption();
        String tenAnh = landScapeHienThi.getLandImageFileName();
        holder.textViewCaption.setText(caption);
        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(tenAnh,"mipmap",packageName);
        holder.imageViewLandScape.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return landScapeList.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder {
        TextView textViewCaption;
        ImageView imageViewLandScape;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            textViewCaption = itemView.findViewById(R.id.textViewCaption);
            imageViewLandScape = itemView.findViewById(R.id.imageViewLand);
        }
    }
}
