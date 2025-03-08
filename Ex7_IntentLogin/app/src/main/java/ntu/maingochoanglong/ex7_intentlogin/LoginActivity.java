package ntu.maingochoanglong.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOK = findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtUserName = findViewById(R.id.edtUserName);
                EditText edtPass = findViewById(R.id.edtPass);
                EditText edtMail = findViewById(R.id.edtMail);

                String userName = edtUserName.getText().toString();
                String pass = edtPass.getText().toString();
                String mail = edtMail.getText().toString();

                if (userName.equals("mai ngoc hoang long") && pass.equals("ABC123") && mail.equals("ABC@gmail.com")) {
                    Intent iQuiz = new Intent(LoginActivity.this, HomeActivity.class);
                    iQuiz.putExtra("user_name",userName);
                    startActivity(iQuiz);
                }
                else {
                    Toast.makeText(LoginActivity.this, "BẠN NHẬP SAI THÔNG TIN!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}