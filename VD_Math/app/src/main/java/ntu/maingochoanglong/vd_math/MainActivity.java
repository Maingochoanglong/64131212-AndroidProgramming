package ntu.maingochoanglong.vd_math;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Button> button_num = new ArrayList<Button>();
    Button button_Check, button_Delete, button_Restart;
    EditText editText_NumA, editText_NumB, editText_KetQua;

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

        TimDieuKien();
        XuLyNutSo();
        XuLyXoaSo();
        getAB();
        XuLyNutRestart();
        XuLyNutCheck();
    }

    void getAB() {
        editText_NumA.setText(String.valueOf((int)(Math.random() * 20)));
        editText_NumB.setText(String.valueOf((int)(Math.random() * 20)));
    }

    void XuLyNutRestart() {
        button_Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAB();
                editText_KetQua.setBackgroundResource(R.drawable.custom_edt);
                editText_KetQua.setText("");
            }
        });
    }

    void XuLyNutSo() {
        for (var button : button_num) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText_KetQua.getText().toString().isEmpty()) {
                        editText_KetQua.setText(String.valueOf(button_num.indexOf(button)));
                    } else {
                        editText_KetQua.setText(editText_KetQua.getText().append(String.valueOf(button_num.indexOf(button))));
                    }
                }
            });
        }
    }

    void XuLyNutCheck() {
        button_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(Integer.parseInt(editText_NumA.getText().toString()) + Integer.parseInt(editText_NumB.getText().toString())).equals(editText_KetQua.getText().toString())) {
                    editText_KetQua.setBackgroundResource(R.drawable.cuttom_edt_success);
                } else {
                    editText_KetQua.setBackgroundResource(R.drawable.custom_edt_error);
                }
            }
        });
    }

    void XuLyXoaSo() {
        button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_KetQua.getText().toString().isEmpty()) {
                    editText_KetQua.setText(
                            editText_KetQua.getText().delete(editText_KetQua.getText().length() - 1, editText_KetQua.getText().length())
                    );
                    editText_KetQua.setBackgroundResource(R.drawable.custom_edt);
                }
            }
        });
    }

    void TimDieuKien() {
        button_num.add(findViewById(R.id.button_0));
        button_num.add(findViewById(R.id.button_1));
        button_num.add(findViewById(R.id.button_2));
        button_num.add(findViewById(R.id.button_3));
        button_num.add(findViewById(R.id.button_4));
        button_num.add(findViewById(R.id.button_5));
        button_num.add(findViewById(R.id.button_6));
        button_num.add(findViewById(R.id.button_7));
        button_num.add(findViewById(R.id.button_8));
        button_num.add(findViewById(R.id.button_9));
        button_Check = findViewById(R.id.button_Check);
        button_Delete = findViewById(R.id.button_Delete);
        button_Restart = findViewById(R.id.button_Restart);
        editText_NumA = findViewById(R.id.editText_NumA);
        editText_NumB = findViewById(R.id.editText_NumB);
        editText_KetQua = findViewById(R.id.editText_KetQua);
    }
}