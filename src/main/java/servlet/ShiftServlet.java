package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import bean.CalendarDay;
import bean.CalendarShift;
import bean.Shift;
import bean.Users;
import dao.ShiftDAO;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShiftServlet")
public class ShiftServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        
        String yearParam = request.getParameter("year");
        String monthParam = request.getParameter("month");
        if (yearParam != null && monthParam != null) {
            year = Integer.parseInt(yearParam);
            month = Integer.parseInt(monthParam);
        }

        List<List<CalendarDay>> calendarWeeks = new ArrayList<>();

        try {
            ShiftDAO shiftDao = new ShiftDAO();
            List<Shift> allShifts = shiftDao.findAll();
            UsersDAO usersDao = new UsersDAO();

            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());

            LocalDate calendarStart = firstDayOfMonth;
            while (calendarStart.getDayOfWeek() != DayOfWeek.SUNDAY) {
                calendarStart = calendarStart.minusDays(1);
            }

            LocalDate calendarEnd = lastDayOfMonth;
            while (calendarEnd.getDayOfWeek() != DayOfWeek.SATURDAY) {
                calendarEnd = calendarEnd.plusDays(1);
            }

            List<CalendarDay> currentWeek = new ArrayList<>();
            LocalDate currentDay = calendarStart;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            while (!currentDay.isAfter(calendarEnd)) {
                CalendarDay dayDto = new CalendarDay();
                dayDto.setDayOfMonth(currentDay.getDayOfMonth());
                
                // 💡 【エラー修正完了】currentMonth から currentDay に戻しました
                dayDto.setCurrentMonth(currentDay.getMonthValue() == month); 

                for (Shift shift : allShifts) {
                    if (shift.getWork_date() != null && shift.getWork_date().equals(currentDay)) {
                        
                        CalendarShift displayShift = new CalendarShift();
                        
                        // 💡 【重要】JSPへ本物のIDを引き継ぐための処理
                        displayShift.setShiftId(shift.getshift_id());
                        
                        if (shift.getstart_time() != null) {
                            displayShift.setStartTime(shift.getstart_time().format(timeFormatter));
                        } else {
                            displayShift.setStartTime("--:--");
                        }
                        
                        if (shift.getend_time() != null) {
                            displayShift.setEndTime(shift.getend_time().format(timeFormatter));
                        } else {
                            displayShift.setEndTime("--:--");
                        }

                        try {
                            Users user = usersDao.selectById(shift.getuser_id());
                            if (user != null && user.getUser_name() != null) {
                                displayShift.setStaffName(user.getUser_name());
                            } else {
                                displayShift.setStaffName("未登録(ID:" + shift.getuser_id() + ")");
                            }
                        } catch (Exception ue) {
                            displayShift.setStaffName("エラー(ID:" + shift.getuser_id() + ")");
                            ue.printStackTrace();
                        }
                        
                        dayDto.getShifts().add(displayShift);
                    }
                }

                currentWeek.add(dayDto);

                if (currentDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    calendarWeeks.add(currentWeek);
                    currentWeek = new ArrayList<>();
                }

                currentDay = currentDay.plusDays(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("currentYear", year);
        request.setAttribute("currentMonth", month);
        request.setAttribute("calendarWeeks", calendarWeeks);

        request.getRequestDispatcher("/main/ShiftList.jsp").forward(request, response);
    }
}
