package ntu.maingochoanglong.luyentap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HoaAdapter extends RecyclerView.Adapter<HoaAdapter.ItemHoaHoler> {
    Context context;
    ArrayList<Hoa> dsHoa;

    public HoaAdapter(Context context, ArrayList<Hoa> dsHoa) {
        this.context = context;
        this.dsHoa = dsHoa;
    }

    @NonNull
    @Override
    public ItemHoaHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHoaHoler(LayoutInflater.from(context).inflate(R.layout.item_hoa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoaHoler holder, int position) {
        Hoa item = dsHoa.get(position);
        holder.editText_TenHoa.setText(item.getTenHoa());
        holder.imageView_AnhHoa.setImageResource(
                holder.itemView.getResources().getIdentifier(
                        item.getAnhHoa(), "mipmap", holder.itemView.getContext().getPackageName()
                )
        );
    }

    @Override
    public int getItemCount() {
        return dsHoa.size();
    }

    class ItemHoaHoler extends RecyclerView.ViewHolder {
        TextView editText_TenHoa;
        ImageView imageView_AnhHoa;

        public ItemHoaHoler(@NonNull View itemView) {
            super(itemView);
            editText_TenHoa = itemView.findViewById(R.id.textView_TenHoa);
            imageView_AnhHoa = itemView.findViewById(R.id.imageView_AnhHoa);
        }
    }
}
