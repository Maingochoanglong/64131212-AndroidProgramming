package thigk2.MaiNgocHoangLong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ItemHoatDongHolder> {
    Context context;
    ArrayList<HoatDong> dsHD;

    public HoatDongAdapter(Context context, ArrayList<HoatDong> dsHD) {
        this.context = context;
        this.dsHD = dsHD;
    }

    @NonNull
    @Override
    public ItemHoatDongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewItem = inflater.inflate(R.layout.item_hoat_dong, parent, false);
        ItemHoatDongHolder holder = new ItemHoatDongHolder(viewItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoatDongHolder holder, int position) {
        HoatDong hoatDong = dsHD.get(position);
        holder.textView_TDHD.setText(hoatDong.getTieuDeHoatDong());
        holder.textView_TG.setText(hoatDong.getThoiGian());
        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(hoatDong.getTenAnh(),"mipmap",packageName);
        holder.imageView_HD.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return dsHD.size();
    }

    class ItemHoatDongHolder extends RecyclerView.ViewHolder {
        TextView textView_TDHD, textView_TG;
        ImageView imageView_HD;

        public ItemHoatDongHolder(@NonNull View itemView) {
            super(itemView);
            textView_TDHD = itemView.findViewById(R.id.textView_TDHD);
            textView_TG = itemView.findViewById(R.id.textView_TG);
            imageView_HD = itemView.findViewById(R.id.imageView_HD);
        }
    }
}
