package thigk2.MaiNgocHoangLong;

public class HoatDong {
    String tieuDeHoatDong, thoiGian, tenAnh;

    public HoatDong(String tieuDeHoatDong, String thoiGian, String tenAnh) {
        this.tieuDeHoatDong = tieuDeHoatDong;
        this.thoiGian = thoiGian;
        this.tenAnh = tenAnh;
    }

    public String getTieuDeHoatDong() {
        return tieuDeHoatDong;
    }

    public void setTieuDeHoatDong(String tieuDeHoatDong) {
        this.tieuDeHoatDong = tieuDeHoatDong;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }
}
