package web.jpa.todo;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import web.jpa.User;

@Entity @Table(name = "todos")
public class Todo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String subject;
	private Timestamp deadline;
	private int priority;
	
	@ManyToOne
	private User user;
	
	public Todo() {}
	public Todo(String subject, Timestamp deadline,int priority,User user) {
		this.subject = subject;
		this.deadline = deadline;
		this.priority = priority;
		this.user = user;
	}
	public String getSubject(){
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority){
		this.priority = priority;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User creator) {
		this.user = creator;
	}
}
