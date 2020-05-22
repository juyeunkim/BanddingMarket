package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="reputation")
public class Reputation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user_id;
	
	@ManyToOne
	@JoinColumn(name = "rater_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User rater_id;
	
	private double rate;
	
	public int getId() {
		return id;
	}
}
