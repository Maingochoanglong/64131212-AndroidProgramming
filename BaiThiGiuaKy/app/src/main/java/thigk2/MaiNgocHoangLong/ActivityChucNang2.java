package thigk2.MaiNgocHoangLong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityChucNang2 extends AppCompatActivity {
    EditText editText_GK, editText_CK, editText_KQ, editText_QT;
    Button button_TinhDTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText_QT = findViewById(R.id.editText_QT);
        editText_GK = findViewById(R.id.editText_GK);
        editText_CK = findViewById(R.id.editText_CK);
        editText_KQ = findViewById(R.id.editText_KQ);
        button_TinhDTB = findViewById(R.id.button_TinhDTB);
        button_TinhDTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float gk = Float.parseFloat(editText_GK.getText().toString());
                Float ck = Float.parseFloat(editText_CK.getText().toString());
                Float qt = Float.parseFloat(editText_QT.getText().toString());
                editText_KQ.setText(String.valueOf(0.2*qt+0.3*gk+0.5*ck));
            }
        });
    }
}