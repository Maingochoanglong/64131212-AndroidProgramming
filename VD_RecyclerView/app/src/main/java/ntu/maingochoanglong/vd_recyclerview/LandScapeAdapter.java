package ntu.maingochoanglong.vd_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHoloder> {
    Context context;
    ArrayList<LandScape> landScapeArrayList;

    public LandScapeAdapter(Context context, ArrayList<LandScape> landScapeArrayList) {
        this.context = context;
        this.landScapeArrayList = landScapeArrayList;
    }

    @NonNull
    @Override
    public ItemLandHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.item_land, parent, false);
        ItemLandHoloder viewHolderCreated = new ItemLandHoloder(viewItem);
        return viewHolderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHoloder holder, int position) {
        LandScape landScapeHienThi = landScapeArrayList.get(position);
        holder.textViewCaption.setText(landScapeHienThi.getLandCaption());
        holder.imageViewlandScape.setImageResource(holder.itemView.getResources().getIdentifier(landScapeHienThi.getLandImageFileName(), "mipmap",holder.itemView.getContext().getPackageName()));
    }

    @Override
    public int getItemCount() {
        return landScapeArrayList.size();
    }

    class ItemLandHoloder extends RecyclerView.ViewHolder {
        TextView textViewCaption;
        ImageView imageViewlandScape;

        public ItemLandHoloder(@NonNull View itemView) {
            super(itemView);
            textViewCaption = itemView.findViewById(R.id.textViewCaption);
            imageViewlandScape = itemView.findViewById(R.id.imageViewlandScape);
        }
    }
}
