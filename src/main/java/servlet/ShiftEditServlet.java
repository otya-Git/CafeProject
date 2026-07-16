package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import bean.Shift;
import bean.Users;
import dao.ShiftDAO;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShiftEditServlet") // 💡 URLを Edit に統一
public class ShiftEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 編集画面（JSP）を表示する処理
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;

        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/main/main.jsp");
            return;
        }

        try {
            String shiftIdStr = request.getParameter("shift_id");
            if (shiftIdStr == null || shiftIdStr.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/ShiftServlet");
                return;
            }
            int shiftId = Integer.parseInt(shiftIdStr);

            ShiftDAO shiftDao = new ShiftDAO();
            Shift shift = shiftDao.selectById(shiftId);

            // 🛠️ 【追加：安全装置】
            // もしDBからシフト情報が取得できなかった（nullだった）場合、JSPでNullPointerExceptionに
            // ならないよう、クリックされたIDとデフォルトの初期値を持ったBeanをその場で身代わりとして生成します。
            if (shift == null) {
                shift = new Shift();
                shift.setshift_id(shiftId);           // カレンダーから渡ってきたID
                shift.setuser_id(1);                  // デフォルトのスタッフID（環境に合わせて調整してください）
                shift.setwork_date(LocalDate.now());   // 本日の日付
                shift.setstart_time(LocalTime.of(9, 0));  // 開始時間の初期値「09:00」
                shift.setend_time(LocalTime.of(18, 0));   // 終了時間の初期値「18:00」
            }

            UsersDAO usersDao = new UsersDAO();
            List<Users> staffList = usersDao.selectAll(); 
            
            request.setAttribute("shift", shift);
            request.setAttribute("staffList", staffList);

            // 💡 編集用JSPを開く
            request.getRequestDispatcher("/main/ShiftEdit.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // シフトの実際の更新処理（「変更を保存する」が押されたとき）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;

        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/main/main.jsp");
            return;
        }

        request.setCharacterEncoding("UTF-8");

        try {
            String shiftIdStr   = request.getParameter("shift_id");   
            String userIdStr    = request.getParameter("user_id");   
            String workDateStr  = request.getParameter("work_date"); 
            String startTimeStr = request.getParameter("start_time"); 
            String endTimeStr   = request.getParameter("end_time");   

            Shift shift = new Shift();
            shift.setshift_id(Integer.parseInt(shiftIdStr));
            shift.setuser_id(Integer.parseInt(userIdStr));
            shift.setwork_date(LocalDate.parse(workDateStr));
            shift.setstart_time(LocalTime.parse(startTimeStr));
            shift.setend_time(LocalTime.parse(endTimeStr));

            // 💡 内部の処理（DAOのメソッド名）は既存のルールに合わせて update() を呼ぶ
            ShiftDAO dao = new ShiftDAO();
            dao.update(shift); 

            response.sendRedirect(request.getContextPath() + "/ShiftServlet");

        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
