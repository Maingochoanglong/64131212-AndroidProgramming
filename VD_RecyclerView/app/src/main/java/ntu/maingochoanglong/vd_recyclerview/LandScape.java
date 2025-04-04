package ntu.maingochoanglong.vd_recyclerview;

public class LandScape {
    String landImageFileName;
    String landCaption;

    public LandScape(String landImageFileName, String landCaption) {
        this.landImageFileName = landImageFileName;
        this.landCaption = landCaption;
    }

    public String getLandImageFileName() {
        return landImageFileName;
    }

    public String getLandCaption() {
        return landCaption;
    }

    public void setLandImageFileName(String landImageFileName) {
        this.landImageFileName = landImageFileName;
    }

    public void setLandCaption(String landCaption) {
        this.landCaption = landCaption;
    }
}
