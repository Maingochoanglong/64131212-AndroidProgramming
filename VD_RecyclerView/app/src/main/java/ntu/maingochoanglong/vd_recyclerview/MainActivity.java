package ntu.maingochoanglong.vd_recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> recyclerViewData;
    RecyclerView recyclerViewLand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerViewData = getRecyclerViewData();
        recyclerViewLand = findViewById(R.id.recyclerLand);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLand.setLayoutManager(layoutManager);
        landScapeAdapter = new LandScapeAdapter(this, recyclerViewData);
        recyclerViewLand.setAdapter(landScapeAdapter);
    }

    ArrayList<LandScape> getRecyclerViewData() {
        ArrayList<LandScape> duLieu = new ArrayList<>();
        LandScape landScape1 = new LandScape("img1","Cột Cờ Hà Nội");
        LandScape landScape2 = new LandScape("img2","Tháp Eiffel");
        LandScape landScape3 = new LandScape("img3","Cung Điện Buckingham");
        duLieu.add(landScape1);
        duLieu.add(landScape2);
        duLieu.add(landScape3);
        duLieu.add(landScape3);
        duLieu.add(landScape3);
        return duLieu;
    }
}