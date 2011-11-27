package com.lknuchel.startdroid.persistance.sqlite;

public final class SqliteConstants {
	public static final int VERSION_BDD = 1;
	public static final String NOM_BDD = "startdroidsample.db";

	public static final String PEOPLE_TABLE = "peopleTable";
	public static final String PEOPLE_COL_ID = "id";
	public static final String PEOPLE_COL_NAME = "name";
	public static final String PEOPLE_COL_COMPANYID = "companyid";
	public static final int PEOPLE_NUM_COL_ID = 0;
	public static final int PEOPLE_NUM_COL_NAME = 1;
	public static final int PEOPLE_NUM_COL_COMPANYID = 2;

	public static final String COMPANY_TABLE = "companyTable";
	public static final String COMPANY_COL_ID = "id";
	public static final String COMPANY_COL_NAME = "name";
	public static final int COMPANY_NUM_COL_ID = 0;
	public static final int COMPANY_NUM_COL_NAME = 1;
}
