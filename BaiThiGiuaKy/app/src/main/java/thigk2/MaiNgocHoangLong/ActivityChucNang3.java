package thigk2.MaiNgocHoangLong;

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

public class ActivityChucNang3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> dsMonHoc = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        dsMonHoc.add("Tin Học đại cương");
        dsMonHoc.add("Lập trình Java");
        dsMonHoc.add("Phát triển Ứng dụng web");
        dsMonHoc.add("Khai phá dữ liệu lớn");
        dsMonHoc.add("Kinh tế chính trị Mác - Lê nin");
        listView.setAdapter(dsMonHoc);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ActivityChucNang3.this, Item3Activity.class).putExtra("ten_mon",dsMonHoc.getItem(position)));
            }
        });
    }
}