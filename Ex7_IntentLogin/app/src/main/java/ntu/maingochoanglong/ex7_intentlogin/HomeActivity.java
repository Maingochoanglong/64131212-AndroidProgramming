package ntu.maingochoanglong.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent iQuiz = getIntent();
        String userName = iQuiz.getStringExtra("user_name");

        TextView tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setText(userName);
        tvUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
    }
}