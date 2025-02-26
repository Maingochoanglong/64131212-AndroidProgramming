package ntu.maingochoanglong.ex4_addsubmuldiv_onclick;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText_soThuNhat;
    EditText editText_soThuHai;
    EditText editText_ketQua;

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
    }

    public void XuLyCong(View v) {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat + soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    public void XuLyTru(View v) {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat - soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    public void XuLyNhan(View v) {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat * soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    public void XuLyChia(View v) {
        float soThuNhat = Float.parseFloat(editText_soThuNhat.getText().toString());
        float soThuHai = Float.parseFloat(editText_soThuHai.getText().toString());
        float ketQua = soThuNhat / soThuHai;
        editText_ketQua.setText(String.valueOf(ketQua));
    }

    void TimDieuKhien() {
        editText_soThuNhat = findViewById(R.id.editText_soThuNhat);
        editText_soThuHai = findViewById(R.id.editText_soThuHai);
        editText_ketQua = findViewById(R.id.editText_ketQua);
    }
}