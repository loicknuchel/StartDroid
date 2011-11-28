package com.lknuchel.startdroid.model;

import com.lknuchel.startdroid.persistance.sqlite.SqliteAdapter;

// This class has to implement DbAdapter interface only for the V3 bdd or higher

public class People implements SqliteAdapter {
	private int id;
	private String name;
	private String companyName;

	public People() {
	}

	public People(String name, String companyName) {
		this.setName(name);
		this.setCompanyName(companyName);
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

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}
}
