package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long user_id;
	private String user_name;
	private String password_hash;
	private String login_id;
	private String role;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	public Users() {}

	public long getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public String getLogin_id() {
		return login_id;
	}

	public String getRole() {
		return role;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public LocalDateTime getUpdate_at() {
		return updated_at;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.updated_at = update_at;
	}
	
	public String getFormattedCreatedAt() {
	    if (this.created_at == null) {
	        return "";
	    }
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	    return this.created_at.format(formatter);
	}
	
}

