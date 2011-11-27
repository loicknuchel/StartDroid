package com.lknuchel.startdroid.model;

import com.lknuchel.startdroid.persistance.sqlite.SqliteAdapter;

// This class has to implement DbAdapter interface only for the V3 bdd or higher

public class Company implements SqliteAdapter {
	private int id;
	private String name;

	public Company() {
	}

	public Company(String name) {
		this.setName(name);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
