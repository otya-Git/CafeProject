package servlet;

import java.io.IOException;

import bean.Users;
import dao.UserInsertDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.PasswordUtil;

@WebServlet("/UserInsert")
public class UserCreate extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            request.setCharacterEncoding("UTF-8");


            // 入力値取得
            
            String login_id =
                request.getParameter("login_id");

            String password_hash =
                request.getParameter("password_hash");

            String user_name =
                request.getParameter("user_name");
            String role = 
            	request.getParameter("role");


            // パスワードをハッシュ化
            String hashPassword =
                PasswordUtil.hash(password_hash);


            // Users作成
            Users user = new Users();
            
            user.setLogin_id(login_id);
            user.setPassword_hash(hashPassword);
            user.setUser_name(user_name);

            user.setRole(role);


            // DB登録
            UserInsertDAO dao =
                new UserInsertDAO();

            int result =
                dao.insert(user);


            if (result > 0) {
           
            	response.sendRedirect(
            		    request.getContextPath() + "/login/login.jsp"
            		);
            	
            } else {

                request.setAttribute(
                    "error",
                    "登録に失敗しました"
                );

                request.getRequestDispatcher(
                    "/login/login.jsp"
                ).forward(request, response);



            }


        } catch(Exception e) {

            throw new ServletException(e);
        }
    }
}