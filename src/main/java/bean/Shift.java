package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Shift implements Serializable {

    private int shift_id;
    private int user_id;
    private LocalDate work_date;
    private LocalTime start_time;
    private LocalTime end_time;

    public Shift() {
    }

    public int getshift_id() {
        return shift_id;
    }

    public void setshift_id(int shift_id) {
        this.shift_id = shift_id;
    }

    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getWork_date() {
        return work_date;
    }

    public void setwork_date(LocalDate work_date) {
        this.work_date = work_date;
    }

    public LocalTime getstart_time() {
        return start_time;
    }

    public void setstart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getend_time() {
        return end_time;
    }

    public void setend_time(LocalTime end_time) {
        this.end_time = end_time;
    }
}