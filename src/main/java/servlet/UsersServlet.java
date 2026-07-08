package servlet;

import java.io.IOException;
import java.util.List;

import bean.Users;
import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 🔵 GETリクエスト：ユーザー一覧画面を表示する
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1. 他のサーブレットと同様に、新しく作成したDAOをインスタンス化
			UsersDAO dao = new UsersDAO();
			
			// 2. ユーザー一覧を全件取得
			List<Users> userList = dao.selectAll();

			// 3. 取得したリストをリクエストスコープに保存
			request.setAttribute("userList", userList);

			// 4. ユーザー一覧を表示するJSPへ画面遷移（フォワード）
			// ※JSPのファイル名や配置パスは、実際の環境に合わせて変更してください（例: "/main/Users.jsp" など）
			request.getRequestDispatcher("/main/User_management.jsp").forward(request, response);

		} catch (Exception e) {
			// 例外が起きた場合はTomcat（サーブレット）にそのまま投げてエラー表示させる
			throw new ServletException(e);
		}
	}
}
