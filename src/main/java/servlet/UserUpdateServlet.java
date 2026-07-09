package servlet;

import java.io.IOException;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 🔵 GETリクエスト：現在の情報を入力欄にセットして「編集画面」を表示する
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 一覧の「編集」リンクから送られてきた user_id を取得
			long userId = Long.parseLong(request.getParameter("user_id"));

			// DAOを使って、そのIDのユーザー情報を1件だけ抜き出す
			UsersDAO dao = new UsersDAO();
			Users user = dao.selectById(userId);

			// 抜き出したデータをリクエストスコープに保存してJSPへ渡す
			request.setAttribute("user", user);
			
			// 編集画面（User_edit.jsp）へ遷移
			request.getRequestDispatcher("/main/UserEdit.jsp").forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * 🔴 POSTリクエスト：編集画面から送信されたデータを受け取り、PasswordUtilでハッシュ化して更新する
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		try {
			long userId = Long.parseLong(request.getParameter("user_id"));
			String userName = request.getParameter("user_name");
			String loginId = request.getParameter("login_id");
			String passwordRaw = request.getParameter("password");
			String role = request.getParameter("role");

			// 💡 プロジェクト内の共通ツール「PasswordUtil」を呼び出して1行でハッシュ化！
			// ※もしメソッド名が「hash」などの場合は、お使いのメソッド名に合わせて書き換えてください。
			String passwordHash = tool.PasswordUtil.hash(passwordRaw); 

			// 更新用データをBeanに詰め込む
			Users user = new Users();
			user.setUser_id(userId);
			user.setUser_name(userName);
			user.setLogin_id(loginId);
			user.setPassword_hash(passwordHash);
			user.setRole(role);

			// DAOを呼び出してデータベースを書き換える
			UsersDAO dao = new UsersDAO();
			boolean success = dao.update(user);

			if (success) {
				// 更新が成功したら、ユーザー一覧（サーブレット）へ自動的に戻る
				response.sendRedirect(request.getContextPath() + "/UsersServlet");
			} else {
				throw new Exception("ユーザー情報の更新に失敗しました。");
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
