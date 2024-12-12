package web.jpa.cashbook;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import web.jpa.User;


@Entity @Table(name = "datas")
public class Data {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String subject;
	private Timestamp recordTime;
	private int category;
	private int price;
	
	@ManyToOne
	private User user;
	
	public Data() {}
	public Data(String subject, Timestamp recordTime, int category,int price, User user) {
		this.subject = subject;
		this.recordTime = recordTime;
		this.category = category;
		this.price = price;
		this.user = user;
	}
	public String getSubject(){
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category){
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User creator) {
		this.user = creator;
	}
	public int getId() {
		return id;
	}
}
