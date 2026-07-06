package servlet;

import java.io.IOException;

import bean.Users;
import dao.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.PasswordUtil;

@WebServlet(urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            // 入力値取得（JSPと完全一致させる）
            String login_id = request.getParameter("login_id");
            String password = request.getParameter("password_hash");

            // null・空チェック（重要）
            if (login_id == null || password == null ||
                login_id.isEmpty() || password.isEmpty()) {

                response.sendRedirect(
                    request.getContextPath() + "/login/login.jsp"
                );
                return;
            }

            // パスワードをハッシュ化
            String hashPassword = PasswordUtil.hash(password);

            // DB検索
            LoginDAO dao = new LoginDAO();
            Users user = dao.search(login_id, hashPassword);

            if (user != null) {

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // 権限で遷移分岐
                if ("ADMIN".equals(user.getRole())) {
                    response.sendRedirect(
                        request.getContextPath() + "/main/mein.jsp"
                    );
                } else {
                    response.sendRedirect(
                        request.getContextPath() + "/main/mein.jsp"
                    );
                }

            } else {
                response.sendRedirect(
                    request.getContextPath() + "/login/login-error.jsp"
                );
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}