package bean;

import java.io.Serializable;

public class CalendarShift implements Serializable {
    private static final long serialVersionUID = 1L;

    // 💡 シフトIDを格納する変数を追加
    private int shiftId;
    private String staffName;
    private String startTime;
    private String endTime;

    // 💡 シフトIDのゲッター・セッターを追加
    public int getShiftId() {
        return shiftId;
    }
    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getStaffName() { 
        return staffName; 
    }
    public void setStaffName(String staffName) { 
        this.staffName = staffName; 
    }
    public String getStartTime() { 
        return startTime; 
    }
    public void setStartTime(String startTime) { 
        this.startTime = startTime; 
    }
    public String getEndTime() { 
        return endTime; 
    }
    public void setEndTime(String endTime) { 
        this.endTime = endTime; 
    }
}
