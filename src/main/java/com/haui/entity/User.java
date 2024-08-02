package com.haui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int id;
	private String email;
	private String username;
	private String password;
	private String avatar;
	private boolean isAdmin;
	private boolean isActive;
	
	public User(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
