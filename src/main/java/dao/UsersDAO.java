package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Users;

public class UsersDAO extends DAO {

	/**
	 * ユーザー一覧を全件取得する
	 */
	public List<Users> selectAll() throws Exception {
		List<Users> userList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection();

			String sql = "SELECT * FROM users ORDER BY user_id ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Users user = new Users();
				user.setUser_id(rs.getLong("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setLogin_id(rs.getString("login_id"));
				user.setPassword_hash(rs.getString("password_hash"));
				user.setRole(rs.getString("role"));
				
				Timestamp createdAt = rs.getTimestamp("created_at");
				if (createdAt != null) {
					user.setCreated_at(createdAt.toLocalDateTime());
				}
				
				Timestamp updatedAt = rs.getTimestamp("updated_at");
				if (updatedAt != null) {
					user.setUpdate_at(updatedAt.toLocalDateTime());
				}

				userList.add(user);
			}
		} finally {
			if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) {} }
			if (con != null) { try { con.close(); } catch (SQLException e) {} }
		}
		
		return userList;
	}

	/**
	 * 💡 新規追加：指定したIDのユーザー1件を取得する（編集画面の初期表示用）
	 */
	public Users selectById(long userId) throws Exception {
		Users user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = this.getConnection();

			String sql = "SELECT * FROM users WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new Users();
				user.setUser_id(rs.getLong("user_id"));
				user.setUser_name(rs.getString("user_name"));
				user.setLogin_id(rs.getString("login_id"));
				user.setPassword_hash(rs.getString("password_hash"));
				user.setRole(rs.getString("role"));
				
				Timestamp createdAt = rs.getTimestamp("created_at");
				if (createdAt != null) {
					user.setCreated_at(createdAt.toLocalDateTime());
				}
				
				Timestamp updatedAt = rs.getTimestamp("updated_at");
				if (updatedAt != null) {
					user.setUpdate_at(updatedAt.toLocalDateTime());
				}
			}
		} finally {
			if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) {} }
			if (con != null) { try { con.close(); } catch (SQLException e) {} }
		}
		
		return user;
	}

	/**
	 * 💡 新規追加：ユーザー情報を更新する（編集の実行用）
	 */
	public boolean update(Users user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int line = 0;

		try {
			con = this.getConnection();

			// 💡 名前、ログインID、パスワード、権限を更新。updated_atを現在時刻に塗り替える！
			String sql = "UPDATE users SET user_name = ?, login_id = ?, password_hash = ?, role = ?, updated_at = CURRENT_TIMESTAMP WHERE user_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUser_name());
			pstmt.setString(2, user.getLogin_id());
			pstmt.setString(3, user.getPassword_hash());
			pstmt.setString(4, user.getRole());
			pstmt.setLong(5, user.getUser_id());

			line = pstmt.executeUpdate();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) {} }
			if (con != null) { try { con.close(); } catch (SQLException e) {} }
		}
		
		return line > 0;
	}
}
