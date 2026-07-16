package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login_admin")
public class Adminlogin extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String ADMIN_PASSWORD = "admin123";

        if (ADMIN_PASSWORD.equals(request.getParameter("password"))) {

            // 管理者登録画面へ移動
            request.getRequestDispatcher("/login/admin_insert.jsp")
                   .forward(request, response);

        } else {

            // パスワードが違う場合
            request.setAttribute("error", "管理者パスワードが違います");

            request.getRequestDispatcher("/login/admin_check.jsp")
                   .forward(request, response);
        }
    }
}