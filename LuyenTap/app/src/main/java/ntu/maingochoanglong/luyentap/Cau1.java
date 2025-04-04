package ntu.maingochoanglong.luyentap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cau1 extends AppCompatActivity {
    EditText editText_SoA, editText_SoB, editText_KetQua;
    Button button_TinhTong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TimDieuKien();

        button_TinhTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyTong();
            }
        });

    }

    private void XuLyTong() {
        Float soA = Float.parseFloat(editText_SoA.getText().toString());
        Float soB = Float.parseFloat(editText_SoB.getText().toString());
        editText_KetQua.setText(String.valueOf(soA + soB));
    }

    private void TimDieuKien() {
        editText_SoA = findViewById(R.id.editTextNumber_SoA);
        editText_SoB = findViewById(R.id.editTextNumber_SoB);
        editText_KetQua = findViewById(R.id.editTextNumber_KetQua);
        button_TinhTong = findViewById(R.id.button_Tong);
    }
}