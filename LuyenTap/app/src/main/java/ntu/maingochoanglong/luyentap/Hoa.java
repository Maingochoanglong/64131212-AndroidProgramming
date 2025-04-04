package ntu.maingochoanglong.luyentap;

public class Hoa {
    String tenHoa, anhHoa;

    public Hoa(String tenHoa, String anhHoa) {
        this.tenHoa = tenHoa;
        this.anhHoa = anhHoa;
    }

    public String getTenHoa() {
        return tenHoa;
    }

    public void setTenHoa(String tenHoa) {
        this.tenHoa = tenHoa;
    }

    public String getAnhHoa() {
        return anhHoa;
    }

    public void setAnhHoa(String anhHoa) {
        this.anhHoa = anhHoa;
    }
}
