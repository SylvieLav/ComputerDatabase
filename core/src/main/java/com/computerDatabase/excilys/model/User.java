package com.computerDatabase.excilys.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "loginName")
	private String name;
	@Column(name = "passwordHash")
	private String password;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public User() {}

	public User(long id, String name, String password, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setUsername(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}