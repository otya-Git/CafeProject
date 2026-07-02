package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Users;

public class UserInsertDAO extends DAO {

    public int insert(Users user) throws Exception {
    	//
    	Connection con = getConnection();
    	
        String sql =
            "INSERT INTO users "
          + "(user_name, login_id, password_hash, role, created_at, updated_at) "
          + "VALUES (?,?, ?, ?, ?, NOW(), NOW())";
        
        PreparedStatement st =
                con.prepareStatement(sql);
        st.setString(2, user.getUser_name());
        st.setString(3, user.getLogin_id());
        st.setString(4, user.getPassword_hash());
        st.setString(5, user.getRole() );      
        int result = st.executeUpdate();

        st.close();
        con.close();

        return result;
    }
}