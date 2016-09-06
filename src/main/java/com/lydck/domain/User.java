package com.lydck.domain;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 6270342937057798579L;
	private String name;
	private int score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
