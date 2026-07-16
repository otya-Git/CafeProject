package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login_admin")
public class Adminlogin extends HttpServlet {

    private static final String ADMIN_PASSWORD = "admin123";

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        String password = request.getParameter("password");


        if (ADMIN_PASSWORD.equals(password)) {

            HttpSession session = request.getSession();

            // 管理者ログイン状態を保存
            session.setAttribute("user", "admin");


            // 管理者登録画面へ
            request.getRequestDispatcher("/login/admin_insert.jsp")
                   .forward(request, response);

            return;

        } else {

            // パスワード違い
            request.setAttribute(
                "error",
                "管理者パスワードが違います"
            );

            request.getRequestDispatcher("/login/admin_check.jsp")
                   .forward(request, response);
        }
    }
}