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

            String login_id =
                request.getParameter("login");

            String password =
                request.getParameter("password");

            // パスワードをハッシュ化
            String hashPassword =
                PasswordUtil.hash(password);

            LoginDAO dao = new LoginDAO();

            // Usersで受け取る
            Users user =
                dao.search(login_id, hashPassword);

            if (user != null) {

                HttpSession session =
                    request.getSession();

                session.setAttribute("user", user);

                // ★ここが重要：権限で遷移を分ける
                if ("ADMIN".equals(user.getRole())) {

                    response.sendRedirect("main/menu.jsp");

                } else {

                    response.sendRedirect("main/menu.jsp");
                }

            } else {

                response.sendRedirect("login-error.jsp");
            }

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}