package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Customer;
import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.PasswordUtil;

@WebServlet("/password-change")
public class PasswordChangeServlet
        extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            HttpSession session =
                request.getSession();

            String sessionToken =
                (String)session.getAttribute("token");

            String formToken =
                request.getParameter("token");

            if (
                sessionToken == null ||
                !sessionToken.equals(formToken)
            ) {
                response.sendError(403);
                return;
            }

            Customer user =
                (Customer)session.getAttribute("user");

            String password =
                request.getParameter("password");

            // 新しいパスワードをハッシュ化
            String hashPassword =
                PasswordUtil.hash(password);

            DAO dao = new DAO();

            Connection con =
                dao.getConnection();

            PreparedStatement st =
                con.prepareStatement(
                    "update customer "
                  + "set password=? "
                  + "where id=?"
                );

            // ここが重要、passwordではなくhashPasswordを保存する
            st.setString(1, hashPassword);
            st.setInt(2, user.getId());

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