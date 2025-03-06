package ntu.maingochoanglong.ex6_intentdongian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button_ManHinh2, button_ManHinh3;

    void TimDieuKhien() {
        button_ManHinh2 = findViewById(R.id.button_ManHinh2);
        button_ManHinh3 = findViewById(R.id.button_ManHinh3);
    }

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

        TimDieuKhien();

        button_ManHinh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ManhHinh2 = new Intent(MainActivity.this, ManHinh2Activity.class);
                startActivity(intent_ManhHinh2);
            }
        });

        button_ManHinh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ManHinh3 = new Intent(MainActivity.this, ManHinh3Activity.class);
                startActivity(intent_ManHinh3);
            }
        });
    }
}