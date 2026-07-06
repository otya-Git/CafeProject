package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Users;
import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.PasswordUtil;

@WebServlet("/password-change")
public class PasswordChangeServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();

            // CSRF対策
            String sessionToken =
                (String) session.getAttribute("token");

            String formToken =
                request.getParameter("token");

            if (sessionToken == null ||
                !sessionToken.equals(formToken)) {

                response.sendError(403);
                return;
            }


            // ログインユーザー取得
            Users user =
                (Users) session.getAttribute("user");


            String password =
                request.getParameter("password");


            // パスワードをハッシュ化
            String hashPassword =
                PasswordUtil.hash(password);


            DAO dao = new DAO();

            Connection con =
                dao.getConnection();


            PreparedStatement st =
                con.prepareStatement(
                    "UPDATE users "
                  + "SET password_hash=?, update_at=NOW() "
                  + "WHERE login_id=?"
                );


            st.setString(1, hashPassword);
            st.setString(2, user.getLogin_id());


            st.executeUpdate();


            st.close();
            con.close();


            response.setContentType(
                "text/html; charset=UTF-8"
            );

            response.getWriter().println(
                "<h1>パスワード変更完了</h1>"
            );


        } catch(Exception e) {

            throw new ServletException(e);
        }
    }
}