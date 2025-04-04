package ntu.maingochoanglong.luyentap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cau2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> dsHoa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        dsHoa.add("Hoa Hồng");
        dsHoa.add("Hoa Lan");
        dsHoa.add("Hoa Cúc");
        dsHoa.add("Hoa Mai");
        dsHoa.add("Hoa Sen");
        listView.setAdapter(dsHoa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Cau2.this, ItemActivity.class).putExtra("ten_hoa",dsHoa.getItem(position)));
            }
        });
    }
}