package ntu.maingochoanglong.ex_bmi_calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText_Weight, editText_Height, editText_BMI_Score, editText_Classification;
    Button button_Calculate_BMI;
    RadioGroup radioGroup;
    RadioButton radioButton_WHO_BMI, radioButton_IDI_WPRO_BMI;

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

        radioButton_WHO_BMI.setChecked(true);

        button_Calculate_BMI.setOnClickListener(v -> CalculateBMI());

        // Khi thay đổi RadioButton, tính lại BMI nếu đã có kết quả
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (!editText_BMI_Score.getText().toString().isEmpty()) {
                updateClassification();
            }
        });
    }

    void TimDieuKhien() {
        editText_Weight = findViewById(R.id.editText_Weight);
        editText_Height = findViewById(R.id.editText_Height);
        editText_BMI_Score = findViewById(R.id.editText_BMI_Score);
        editText_Classification = findViewById(R.id.editText_Classification);
        button_Calculate_BMI = findViewById(R.id.button_Calculate_BMI);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton_WHO_BMI = findViewById(R.id.radioButton_WHO_BMI);
        radioButton_IDI_WPRO_BMI = findViewById(R.id.radioButton_IDI_WPRO_BMI);
    }

    void CalculateBMI() {
        String weightStr = editText_Weight.getText().toString().trim();
        String heightStr = editText_Height.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            editText_BMI_Score.setText(R.string.error);
            editText_Classification.setHint(R.string.classification);
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr) / 100;
        double bmi = weight / (height * height);

        editText_BMI_Score.setText(String.format(Locale.ENGLISH, "%.1f", bmi));
        updateClassification();
    }

    void updateClassification() {
        double bmi = Double.parseDouble(editText_BMI_Score.getText().toString());
        int selectedId = radioGroup.getCheckedRadioButtonId();

        String classification;
        if (selectedId == radioButton_WHO_BMI.getId()) {
            classification = classifyBMI_WHO(bmi);
        } else {
            classification = classifyBMI_IDI_WPRO(bmi);
        }

        editText_Classification.setText(classification);
    }

    String classifyBMI_WHO(double bmi) {
        if (bmi < 18.5) return getString(R.string.underweight);
        else if (bmi < 25) return getString(R.string.normal_weight);
        else if (bmi < 30) return getString(R.string.overweight);
        else if (bmi < 35) return getString(R.string.obesity_class_1);
        else if (bmi < 40) return getString(R.string.obesity_class_2);
        else return getString(R.string.obesity_class_3);
    }

    String classifyBMI_IDI_WPRO(double bmi) {
        if (bmi < 18.5) return getString(R.string.underweight);
        else if (bmi < 23) return getString(R.string.normal_weight);
        else if (bmi < 25) return getString(R.string.overweight);
        else if (bmi < 30) return getString(R.string.obesity_class_1);
        else return getString(R.string.obesity_class_2);
    }
}
