package thigk2.MaiNgocHoangLong;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityChucNang4 extends AppCompatActivity {
    HoatDongAdapter hoatDongAdapter;
    ArrayList<HoatDong> recyclerViewData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerViewData = getRecyclerViewData();
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        hoatDongAdapter = new HoatDongAdapter(this, recyclerViewData);
        recyclerView.setAdapter(hoatDongAdapter);
    }
    ArrayList<HoatDong> getRecyclerViewData() {
        ArrayList<HoatDong> duLieu = new ArrayList<>();
        HoatDong hd1 = new HoatDong("Hoạt Động 1","10 Giờ","hoatdong");
        duLieu.add(hd1);
        duLieu.add(hd1);
        duLieu.add(hd1);
        duLieu.add(hd1);
        return duLieu;
    }
}