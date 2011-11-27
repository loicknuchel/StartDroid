package com.lknuchel.startdroid.model;

import com.lknuchel.startdroid.persistance.sqlite.SqliteAdapter;

// This class has to implement DbAdapter interface only for the V3 bdd or higher

public class People implements SqliteAdapter {
	private int id;
	private String name;
	private int companyId;

	public People() {
	}

	public People(String name, int i) {
		this.setName(name);
		this.setCompanyId(i);
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

	public void setCompanyId(int i) {
		this.companyId = i;
	}

	public int getCompanyId() {
		return companyId;
	}
}
