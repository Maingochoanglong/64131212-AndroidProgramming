package ntu.maingochoanglong.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
        //1. Tạo DB
        SQLiteDatabase csdl = openOrCreateDatabase("qlSach.db", MODE_PRIVATE,null);
        //2. Tạo bảng: CHE ĐOẠN CODE SAU LẠI ĐỂ TRÁNH VIỆC TẠO LẠI BẢNG MỚI
        //2.1. Lệnh SQL tạo bảng
        String sqlXoaBangSach ="DROP TABLE IF EXISTS BOOKS";
        String sqlTaoBangSach ="CREATE TABLE BOOKS(BookID integer PRIMARY KEY " +
                ", BookName text" +
                ", Page integer"+
                ", Price Float " +
                ", Description text)";
        //2.2. Thực hiện lệnh SQL
        csdl.execSQL(sqlXoaBangSach);
        // sau bước này ta có thể save as ra máy tính để thao tác
        csdl.execSQL(sqlTaoBangSach);

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'sách về java');\n")
                .append("INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.00, 'Android cơ bản');\n")
                .append("INSERT INTO BOOKS VALUES(3, 'Học làm giàu', 120, 0.99, 'sách đọc cho vui');\n")
                .append("INSERT INTO BOOKS VALUES(4, 'Tử điển Anh-Việt', 1000, 29.50, 'Từ điển 100.000 từ');\n")
                .append("INSERT INTO BOOKS VALUES(5, 'CNXH', 1, 1, 'chuyện cổ tích');");
        for (String sql : query.toString().split("\n"))
            csdl.execSQL(sql);
        //Demo Đọc dữ liệu
        String sqlSelect ="Select * from BOOKS";
        // Thực thi lệnh, tập kết quả được quản lý bởi biến RS
        Cursor rs= csdl.rawQuery(sqlSelect,null);
        // Đổ dữ liệu này vào một ArrayList
        // Cần có 1 Lớp tương đương với Bảng
        // ví dụ: Lớp SACH
        // ArrayList<SACH> dsSach;
        // Thực hiện đổ dữ liệu:
        // cho handle (rs) di chuyển lần lượt từng dòng
        // lấy dữ liệu từng cột, đưa vào đối tượng sách
        // thêm vào Array
        ArrayList<Book> dsSach = new ArrayList<Book>();  //// Dùng RecyvcerView để hiện
        ArrayList<String> dsTenSach = new ArrayList<String>(); // Dùng ListView để hiện
        while (true) {
            //Dòng cuối rồi, không next được nữa thì dừng
            if (rs.moveToNext() ==false )  break; //Hết dòng
            // Nếu không dừng, ta xử lý dòng hiện tại ở đây
            // Lấy dữ liệu ở cột 0  (BookID)
            int ma_sach = rs.getInt(0);  // cột 0, kiểu Interget
            String ten_sach = rs.getString(1);
            int sotrang = rs.getInt(2);
            float giaban = rs.getFloat(3);
            String mota =rs.getString(4);
            //Gói vào đối tượng sách
            Book b = new Book(ma_sach, ten_sach, sotrang, giaban,mota);
            // Thêm vào danh sách
            dsSach.add(b);            //
            dsTenSach.add(ten_sach);

            ListView listViewSACH = findViewById(R.id.lvSach);
            ArrayAdapter<String> adapterTenSach = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    dsTenSach);
            listViewSACH.setAdapter(adapterTenSach);

        }
    }
}