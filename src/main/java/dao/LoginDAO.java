package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Users;

public class LoginDAO extends DAO {

    public Users search(String login_id, String password_hash) throws Exception {
    	
    	Connection con = getConnection();

    
        String sql =
            "SELECT * FROM users "
          + "WHERE login_id=? AND password_hash=?";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(1, login_id);
        st.setString(2, password_hash);

        ResultSet rs =
            st.executeQuery();

        Users user = null;

        if (rs.next()) {

            user = new Users();

            user.setUser_id(
                rs.getString("user_id")
            );

            user.setUser_name(
                rs.getString("user_name")
            );

            user.setLogin_id(
                rs.getString("login_id")
            );

            user.setPassword_hash(
                rs.getString("password_hash")
            );

            // 権限
            user.setRole(
                rs.getString("role")
            );

            // 作成日
            if (rs.getTimestamp("created_at") != null) {
                user.setCreated_at(
                    rs.getTimestamp("created_at")
                    .toLocalDateTime()
                );
            }

            // 更新日
            if (rs.getTimestamp("update_at") != null) {
                user.setUpdate_at(
                    rs.getTimestamp("update_at")
                    .toLocalDateTime()
                );
            }
        }

        rs.close();
        st.close();
        con.close();

        return user;
    }
}