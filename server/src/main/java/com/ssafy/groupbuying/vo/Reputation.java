package com.ssafy.groupbuying.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="user")
public class Reputation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reputation_id;
	@OneToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user_id;
	@OneToOne
	@JoinColumn(name = "rater_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User rater_id;
	private double rate;
}
