package web.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import web.jpa.cashbook.Data;
import web.jpa.todo.Todo;

@Entity
@Table(name="users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String userId;
	private String password;
	private String name;
	
	@OneToMany(mappedBy = "user")
	private List<Todo> todos = new ArrayList<>();
	public List<Todo> getTodos(){
		return todos;
	}
	
	@OneToMany(mappedBy = "user")
	private List<Data> datas = new ArrayList<>();
	public List<Data> getDatas(){
		return datas;
	}

	public User() {
	}

	public User(String userId, String password, String name) {
		this.userId = userId;
		this.password = password;
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
