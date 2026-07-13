package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalendarDay implements Serializable {
    private static final long serialVersionUID = 1L;

    private int dayOfMonth;
    private boolean currentMonth; 
    private List<CalendarShift> shifts = new ArrayList<>();

    public int getDayOfMonth() { 
        return dayOfMonth; 
    }
    public void setDayOfMonth(int dayOfMonth) { 
        this.dayOfMonth = dayOfMonth; 
    }
    
    // publicかつ標準的な命名にすることで、JSPのEL式 ${day.currentMonth} から完全にアクセス可能になります
    public boolean isCurrentMonth() { 
        return currentMonth; 
    }
    public boolean getCurrentMonth() { 
        return currentMonth; 
    }
    public void setCurrentMonth(boolean currentMonth) { 
        this.currentMonth = currentMonth; 
    }
    
    public List<CalendarShift> getShifts() { 
        return shifts; 
    }
    public void setShifts(List<CalendarShift> shifts) { 
        this.shifts = shifts; 
    }
}
