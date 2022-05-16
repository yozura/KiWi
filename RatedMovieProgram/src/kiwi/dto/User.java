package kiwi.dto;

import java.sql.Date;

public class User {
	private String id;
	private String password;
	private String nickname;
	private Date birthday;
	private String email; 
	private String tel;
	
	public User(String id, String password, String nickname, Date birthday, String email, String tel) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.email = email;
		this.tel = tel;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthDate() {
		return birthday;
	}
	public void setBirthDate(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}