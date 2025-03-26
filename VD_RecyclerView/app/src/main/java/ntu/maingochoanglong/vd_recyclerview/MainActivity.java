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
    ArrayList<LandScape> landScapeArrayList;
    RecyclerView recyclerViewLandScape;

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
        landScapeArrayList = getLandScapeArrayList();
        recyclerViewLandScape = findViewById(R.id.recyclerViewLand);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLandScape.setLayoutManager(layoutManager);
        landScapeAdapter = new LandScapeAdapter(this, landScapeArrayList);
        recyclerViewLandScape.setAdapter(landScapeAdapter);
    }

    ArrayList<LandScape> getLandScapeArrayList() {
        ArrayList<LandScape> landScapes = new ArrayList<>();
        LandScape landScape = new LandScape("img","Hồ Gươm");
        LandScape landScape1 = new LandScape("img_1","Cung Điện Buckingham");
        LandScape landScape2 = new LandScape("img_2","Tháp Effel");
        landScapes.add(landScape);
        landScapes.add(landScape1);
        landScapes.add(landScape2);
        return landScapes;
    }
}