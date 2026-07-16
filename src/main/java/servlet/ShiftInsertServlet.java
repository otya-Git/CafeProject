package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import bean.Shift;   // お持ちの Shift クラス
import bean.Users;   // お持ちの Users クラス
import dao.ShiftDAO; // 送っていただいた ShiftDAO クラス
import dao.UsersDAO; // お持ちの UsersDAO クラス
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShiftInsertServlet")
public class ShiftInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 1. シフト登録画面（JSP）を表示する処理
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 【安全対策】画面を開く前にセッションの role をチェックして一般ユーザーを弾く
        HttpSession session = request.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;

        // ログインしていない、または admin（マスター）でなければメニュー画面へ強制送還
        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/main/main.jsp");
            return;
        }

        try {
            // 💡 【エラー修正完了】お持ちのUsersDAOから、全スタッフ一覧を「selectAll()」で取得
            UsersDAO usersDao = new UsersDAO();
            List<Users> staffList = usersDao.selectAll(); 
            
            // JSP側で ${staffList} としてループ処理できるようにリクエストに保存
            request.setAttribute("staffList", staffList);

            // 管理者であれば、スタッフリストを持ってシフト追加用のJSP画面を開く
            request.getRequestDispatcher("/main/ShiftInsert.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // 2. シフトの実際の登録処理（JSPのフォームから送信されたとき）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 【安全対策】データを直接送りつけてきた一般ユーザーを裏側で確実に弾く
        HttpSession session = request.getSession(false);
        Users user = (session != null) ? (Users) session.getAttribute("user") : null;

        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/main/main.jsp");
            return;
        }

        // 文字化け対策
        request.setCharacterEncoding("UTF-8");

        try {
            // ① JSPの入力フォーム（inputやselectのname属性）から値を取得
            String userIdStr    = request.getParameter("user_id");   
            String workDateStr  = request.getParameter("work_date");  // yyyy-MM-dd
            String startTimeStr = request.getParameter("start_time"); // HH:mm
            String endTimeStr   = request.getParameter("end_time");   // HH:mm

            // ② お持ちの Shift Bean に、型を変換してセット（メソッド名もShift.javaに完全一致）
            Shift shift = new Shift();
            shift.setuser_id(Integer.parseInt(userIdStr));
            shift.setwork_date(LocalDate.parse(workDateStr));
            shift.setstart_time(LocalTime.parse(startTimeStr));
            shift.setend_time(LocalTime.parse(endTimeStr));

            // ③ 送っていただいた ShiftDAO を使って、データベースへ登録（INSERT）を実行
            ShiftDAO dao = new ShiftDAO();
            dao.insert(shift); 

            // ④ 登録が成功したら、シフト一覧を表示するサーブレットへリダイレクト
            response.sendRedirect(request.getContextPath() + "/ShiftServlet");

        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
