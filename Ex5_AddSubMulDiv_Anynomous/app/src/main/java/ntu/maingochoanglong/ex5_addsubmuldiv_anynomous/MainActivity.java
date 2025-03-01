package ntu.maingochoanglong.ex5_addsubmuldiv_anynomous;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText_soThuNhat, editText_soThuHai, editText_ketQua;
    Button button_Cong, button_Tru, button_Nhan, button_Chia;

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

        button_Cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyCong();
            }
        });

        button_Tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyTru();
            }
        });

        button_Nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyNhan();
            }
        });

        button_Chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyChia();
            }
        });
    }

    void XuLyCong() {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat + soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    void XuLyTru() {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat - soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    void XuLyNhan() {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat * soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    void XuLyChia() {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat / soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    void TimDieuKhien() {
        editText_soThuNhat = findViewById(R.id.editText_soThuNhat);
        editText_soThuHai = findViewById(R.id.editText_soThuHai);
        editText_ketQua = findViewById(R.id.editText_ketQua);
        button_Cong = findViewById(R.id.button_Cong);
        button_Tru = findViewById(R.id.button_Tru);
        button_Nhan = findViewById(R.id.button_Nhan);
        button_Chia = findViewById(R.id.button_Chia);
    }
}